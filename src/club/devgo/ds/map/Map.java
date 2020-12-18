// Copyright (c) 2020, devgo.club
// All rights reserved.

package club.devgo.ds.map;

public interface Map<K, V> {
    int getSize();

    boolean isEmpty();

    void add(K key, V value);

    V remove(K key);

    void set(K key, V value);

    V get(K key);

    boolean contains(K key);

}