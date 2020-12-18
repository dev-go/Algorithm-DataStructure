// Copyright (c) 2020, devgo.club
// All rights reserved.

package club.devgo.ds.set.impl;

import club.devgo.ds.avl_tree.AvlTree;
import club.devgo.ds.set.Set;

public class AvlSet<E extends Comparable<E>> implements Set<E> {
    private AvlTree<E, Object> avlTree;

    public AvlSet() {
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
    public void add(E e) {
        this.avlTree.put(e, null);
        return;
    }

    @Override
    public boolean contains(E e) {
        return this.avlTree.contains(e);
    }

    @Override
    public void remove(E e) {
        this.avlTree.remove(e);
        return;
    }

}