// Copyright (c) 2020, devgo.club
// All rights reserved.

package club.devgo.ds.set.impl;

import club.devgo.ds.binary_search_tree.BST;
import club.devgo.ds.set.Set;

public class BstSet<E extends Comparable<E>> implements Set<E> {
    private BST<E> bst;

    public BstSet() {
        this.bst = new BST<>();
        return;
    }

    @Override
    public int getSize() {
        return this.bst.getSize();
    }

    @Override
    public boolean isEmpty() {
        return this.bst.isEmpty();
    }

    @Override
    public void add(E e) {
        this.bst.add(e);
        return;
    }

    @Override
    public boolean contains(E e) {
        return this.bst.contains(e);
    }

    @Override
    public void remove(E e) {
        this.bst.remove(e);
        return;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("BstSet(size=%d): ", this.bst.getSize()));
        sb.append(this.bst.inIter());
        return sb.toString();
    }
}