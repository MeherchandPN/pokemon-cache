package com.weave.assignment.backendtakehomehvnuif.service.cache;

import com.weave.assignment.backendtakehomehvnuif.model.CacheElement;
import com.weave.assignment.backendtakehomehvnuif.model.DoublyLinkedList;
import com.weave.assignment.backendtakehomehvnuif.model.Pokemon;
import com.weave.assignment.backendtakehomehvnuif.model.node.LinkedListNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Service
public class PokemonCache implements Cache<String, Pokemon> {
    @Value("${cache.size}")
    private int cacheSize;
    private final Map<String, LinkedListNode<CacheElement<String, Pokemon>>> cacheMap;
    private final DoublyLinkedList<CacheElement<String, Pokemon>> cacheList;
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public PokemonCache() {
        this.cacheMap = new ConcurrentHashMap<>(cacheSize);
        this.cacheList = new DoublyLinkedList<>();
    }

    @Override
    public Optional<Pokemon> get(String key) {
        this.lock.readLock().lock();
        try {
            LinkedListNode<CacheElement<String, Pokemon>> node = this.cacheMap.get(key);
            if (node != null && !node.isEmpty()) {
                cacheMap.put(key, this.cacheList.moveToFront(node));
                return Optional.of(node.getElement().getValue());
            }
            return Optional.empty();
        } finally {
            this.lock.readLock().unlock();
        }
    }

    @Override
    public boolean set(String key, Pokemon value) {
        this.lock.writeLock().lock();
        try {
            CacheElement<String, Pokemon> item = new CacheElement<>(key, value);
            LinkedListNode<CacheElement<String, Pokemon>> newNode;

            if (cacheMap.containsKey(key)) {
                LinkedListNode<CacheElement<String, Pokemon>> node = cacheMap.get(key);
                newNode = cacheList.updateAndMoveToFront(node, item);
            } else {
                if (size() >= cacheSize) {
                    evictElement();
                }
                newNode = cacheList.add(item);
            }

            // check if newNode is not null before accessing methods
            if (newNode != null && newNode.hasElement()) {
                cacheMap.put(key, newNode);
                return true;
            }
            return false;
        } finally {
            this.lock.writeLock().unlock();
        }
    }

    @Override
    public Optional<Pokemon> delete(String key) {
        this.lock.writeLock().lock();
        try {
            LinkedListNode<CacheElement<String, Pokemon>> node = this.cacheMap.get(key);
            if (node != null && !node.isEmpty()) {
                Pokemon value = node.getElement().getValue();
                cacheList.remove(node.getElement());
                cacheMap.remove(node.getElement().getKey());
                return Optional.of(value);
            } else {
                return Optional.empty();
            }
        } finally {
            this.lock.writeLock().unlock();
        }
    }

    @Override
    public long size() {
        this.lock.readLock().lock();
        try {
            return cacheList.size();
        } finally {
            this.lock.readLock().unlock();
        }
    }

    @Override
    public void clear() {
        this.lock.writeLock().lock();
        try {
            cacheMap.clear();
            cacheList.clear();
        } finally {
            this.lock.writeLock().unlock();
        }
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    private void evictElement() {
        this.lock.writeLock().lock();
        try {
            LinkedListNode<CacheElement<String, Pokemon>> linkedListNode = cacheList.removeTail();
            if (!linkedListNode.isEmpty()) {
                cacheMap.remove(linkedListNode.getElement().getKey());
            }
        } finally {
            this.lock.writeLock().unlock();
        }
    }

    @Override
    public List<Pokemon> getAllValues() {
        this.lock.readLock().lock();
        try {
            List<Pokemon> allValues = new ArrayList<>();
            LinkedListNode<CacheElement<String, Pokemon>> currentNode = cacheList.getHead();
            while (currentNode != null && currentNode.hasElement()) {
                CacheElement<String, Pokemon> element = currentNode.getElement();
                Pokemon value = element.getValue();
                allValues.add(value);
                currentNode = currentNode.getNext();
            }
            return allValues;
        } finally {
            this.lock.readLock().unlock();
        }
    }
}
