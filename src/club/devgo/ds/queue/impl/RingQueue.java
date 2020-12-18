// Copyright (c) 2020, devgo.club
// All rights reserved.

package club.devgo.ds.queue.impl;

import club.devgo.ds.queue.Queue;

public class RingQueue<E> implements Queue<E> {
    private E[] data;
    private int head;
    private int tail;

    @SuppressWarnings({ "unchecked" })
    public RingQueue(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity is invalid");
        }
        this.data = (E[]) new Object[capacity];
        this.head = 0;
        this.tail = 0;
    }

    public RingQueue() {
        this(1 << 4);
    }

    @Override
    public int getSize() {
        return this.tail - this.head;
    }

    public int getCapacity() {
        return this.data.length;
    }

    @Override
    public boolean isEmpty() {
        return this.head == this.tail;
    }

    @SuppressWarnings({ "unchecked" })
    private void resize(int newCapacity) {
        if (newCapacity < this.getSize()) {
            throw new IllegalArgumentException("newCapacity is invalid");
        }
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < this.getSize(); i++) {
            newData[i] = this.data[(i + this.head) % this.data.length];
        }
        this.data = newData;
        this.tail = this.tail - this.head;
        this.head = 0;
        return;
    }

    @Override
    public void enqueue(E e) {
        if (this.getSize() == this.data.length) {
            this.resize(this.data.length * 2);
        }
        this.data[this.tail % this.data.length] = e;
        this.tail++;
        return;
    }

    @Override
    public E dequeue() {
        if (this.isEmpty()) {
            throw new IllegalArgumentException("queue is empty");
        }
        E ret = this.data[this.head % this.data.length];
        this.data[this.head % this.data.length] = null;
        this.head++;
        if (this.getSize() == this.data.length / 4) {
            this.resize(this.data.length / 2);
        }
        return ret;
    }

    @Override
    public E getFront() {
        return this.data[this.head % this.data.length];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("RingQueue(size=%d, capacity=%d): [", this.getSize(), this.data.length));
        for (int i = this.head; i < this.tail; i++) {
            sb.append(this.data[i % this.data.length]);
            if (i != this.tail - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}