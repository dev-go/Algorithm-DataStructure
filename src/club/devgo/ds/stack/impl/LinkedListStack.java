// Copyright (c) 2020, devgo.club
// All rights reserved.

package club.devgo.ds.stack.impl;

import club.devgo.ds.linked_list.LinkedList;
import club.devgo.ds.stack.Stack;

public class LinkedListStack<E> implements Stack<E> {
    private LinkedList<E> linkedList;

    public LinkedListStack() {
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
    public void push(E e) {
        this.linkedList.addFirst(e);
        return;
    }

    @Override
    public E pop() {
        return this.linkedList.removeFirst();
    }

    @Override
    public E peek() {
        return this.linkedList.getFirst();
    }

    @Override
    public String toString() {
        return this.linkedList.toString().replaceFirst("LinkedList", "LinkedListStack");
    }
}