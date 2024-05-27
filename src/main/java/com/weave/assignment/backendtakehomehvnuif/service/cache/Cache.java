package com.weave.assignment.backendtakehomehvnuif.service.cache;

import java.util.List;
import java.util.Optional;

public interface Cache<K, V> {
    Optional<V> get(K k);

    boolean set(K k, V v);

    Optional<V> delete(K k);

    long size();

    void clear();

    boolean isEmpty();

    List<V> getAllValues();
}
