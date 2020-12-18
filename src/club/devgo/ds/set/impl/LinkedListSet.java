// Copyright (c) 2020, devgo.club
// All rights reserved.

package club.devgo.ds.set.impl;

import club.devgo.ds.linked_list.LinkedList;
import club.devgo.ds.set.Set;

public class LinkedListSet<E> implements Set<E> {
    private LinkedList<E> linkedList;

    public LinkedListSet() {
        this.linkedList = new LinkedList<>();
        return;
    }

    @Override
    public int getSize() {
        return this.linkedList.getSize();
    }

    @Override
    public boolean isEmpty() {
        return this.linkedList.isEmpty();
    }

    @Override
    public void add(E e) {
        if (!this.linkedList.contains(e)) {
            this.linkedList.addFirst(e);
        }
        return;
    }

    @Override
    public boolean contains(E e) {
        return this.linkedList.contains(e);
    }

    @Override
    public void remove(E e) {
        this.linkedList.removeElements(e);
        return;
    }

    @Override
    public String toString() {
        return this.linkedList.toString().replaceFirst("LinkedList", "LinkedListSet");
    }
}