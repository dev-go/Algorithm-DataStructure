// Copyright (c) 2020, devgo.club
// All rights reserved.

package club.devgo.ds.priority_queue;

import club.devgo.ds.heap.MaxHeap;
import club.devgo.ds.queue.Queue;

public class PriorityQueue<E extends Comparable<E>> implements Queue<E> {
    private MaxHeap<E> maxHeap;

    public PriorityQueue() {
        this.maxHeap = new MaxHeap<>();
        return;
    }

    public PriorityQueue(int capacity) {
        this.maxHeap = new MaxHeap<>(capacity);
        return;
    }

    @Override
    public int getSize() {
        return this.maxHeap.getSize();
    }

    @Override
    public boolean isEmpty() {
        return this.maxHeap.isEmpty();
    }

    @Override
    public void enqueue(E e) {
        this.maxHeap.add(e);
        return;
    }

    @Override
    public E dequeue() {
        return this.maxHeap.removeMax();
    }

    @Override
    public E getFront() {
        return this.maxHeap.getMax();
    }

    @Override
    public String toString() {
        return this.maxHeap.toString().replaceFirst("MaxHeap", "PriorityQueue");
    }
}