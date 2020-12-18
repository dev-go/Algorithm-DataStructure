// Copyright (c) 2020, devgo.club
// All rights reserved.

package club.devgo.ds.queue.impl;

import club.devgo.ds.linked_list.LinkedList;
import club.devgo.ds.queue.Queue;

public class LinkedListQueue<E> implements Queue<E> {
    private LinkedList<E> linkedList;

    public LinkedListQueue() {
        this.linkedList = new LinkedList<>();
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
    public void enqueue(E e) {
        this.linkedList.addLast(e);
        return;
    }

    @Override
    public E dequeue() {
        return this.linkedList.removeFirst();
    }

    @Override
    public E getFront() {
        return this.linkedList.getFirst();
    }

    @Override
    public String toString() {
        return this.linkedList.toString().replaceFirst("LinkedList", "LinkedListQueue");
    }
}