// Copyright (c) 2020, devgo.club
// All rights reserved.

package club.devgo.ds.map.impl;

import club.devgo.ds.avl_tree.AvlTree;
import club.devgo.ds.map.Map;

public class AvlMap<K extends Comparable<K>, V> implements Map<K, V> {
    private AvlTree<K, V> avlTree;

    public AvlMap() {
        this.avlTree = new AvlTree<>();
        return;
    }

    @Override
    public int getSize() {
        return this.avlTree.getSize();
    }

    @Override
    public boolean isEmpty() {
        return this.avlTree.isEmpty();
    }

    @Override
    public void add(K key, V value) {
        this.avlTree.put(key, value);
        return;
    }

    @Override
    public V remove(K key) {
        return this.avlTree.remove(key);
    }

    @Override
    public void set(K key, V value) {
        this.avlTree.set(key, value);
        return;
    }

    @Override
    public V get(K key) {
        return this.avlTree.get(key);
    }

    @Override
    public boolean contains(K key) {
        return this.avlTree.contains(key);
    }

}