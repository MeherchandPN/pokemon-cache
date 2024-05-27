package com.weave.assignment.backendtakehomehvnuif.model.node;

import com.weave.assignment.backendtakehomehvnuif.model.DoublyLinkedList;

public interface LinkedListNode<T> {
    boolean hasElement();

    boolean isEmpty();

    T getElement() throws NullPointerException;

    void detach();

    DoublyLinkedList<T> getListReference();

    LinkedListNode<T> setPrev(LinkedListNode<T> prev);

    LinkedListNode<T> setNext(LinkedListNode<T> next);

    LinkedListNode<T> getPrev();

    LinkedListNode<T> getNext();

    LinkedListNode<T> search(T value);
}
