package com.shennong.sp.commom.cache;

import com.hazelcast.core.MapStore;

import java.util.Collection;
import java.util.Map;

/**
 * hazelcast持久化实现
 * @param <K>
 * @param <V>
 */
public class Persistence<K, V> implements MapStore<K, V> {

    @Override
    public void store(K k, V v) {

    }

    @Override
    public void storeAll(Map<K, V> map) {

    }

    @Override
    public void delete(K k) {

    }

    @Override
    public void deleteAll(Collection<K> collection) {

    }

    @Override
    public V load(K k) {
        return null;
    }

    @Override
    public Map<K, V> loadAll(Collection<K> collection) {
        return null;
    }

    @Override
    public Iterable<K> loadAllKeys() {
        return null;
    }
}
