// Copyright (c) 2020, devgo.club
// All rights reserved.

package club.devgo.ds.queue.impl;

import club.devgo.ds.array.Array;
import club.devgo.ds.queue.Queue;

public class ArrayQueue<E> implements Queue<E> {
    private Array<E> array;

    public ArrayQueue() {
        this.array = new Array<>();
        return;
    }

    public ArrayQueue(int capacity) {
        this.array = new Array<>(capacity);
        return;
    }

    @Override
    public int getSize() {
        return this.array.getSize();
    }

    public int getCapacity() {
        return this.array.getCapacity();
    }

    @Override
    public boolean isEmpty() {
        return this.array.isEmpty();
    }

    @Override
    public void enqueue(E e) {
        this.array.addLast(e);
        return;
    }

    @Override
    public E dequeue() {
        return this.array.removeFirst();
    }

    @Override
    public E getFront() {
        return this.array.get(0);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("ArrayQueue(size=%d, capacity=%d): [", this.array.getSize(), this.array.getCapacity()));
        for (int i = 0; i < this.array.getSize(); i++) {
            sb.append(this.array.get(i));
            if (i != this.array.getSize() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}