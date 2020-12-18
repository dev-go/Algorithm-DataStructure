// Copyright (c) 2020, devgo.club
// All rights reserved.

package club.devgo.ds.heap;

import club.devgo.ds.array.Array;

public class MaxHeap<E extends Comparable<E>> {
    private Array<E> array;

    public MaxHeap() {
        this.array = new Array<>();
        return;
    }

    public MaxHeap(int capacity) {
        this.array = new Array<>(capacity);
        return;
    }

    public int getSize() {
        return this.array.getSize();
    }

    public boolean isEmpty() {
        return this.array.isEmpty();
    }

    public void add(E e) {
        this.array.addLast(e);
        this.siftUp(this.array.getSize() - 1);
        return;
    }

    private void siftUp(int startIndex) {
        for (int childIndex = startIndex; childIndex > 0;) {
            int parentIndex = this.getParentIndex(childIndex);
            if (this.array.get(childIndex).compareTo(this.array.get(parentIndex)) > 0) {
                this.swap(childIndex, parentIndex);
                childIndex = parentIndex;
            } else {
                break;
            }
        }
        return;
    }

    private int getParentIndex(int childIndex) {
        return (childIndex - 1) / 2;
    }

    private void swap(int oneIndex, int anotherIndex) {
        E tmp = this.array.get(oneIndex);
        this.array.set(oneIndex, this.array.get(anotherIndex));
        this.array.set(anotherIndex, tmp);
        return;
    }

    public E getMax() {
        if (this.array.getSize() <= 0) {
            throw new IllegalArgumentException("MaxHeap is empty");
        }
        return this.array.get(0);
    }

    public E removeMax() {
        if (this.array.getSize() <= 0) {
            throw new IllegalArgumentException("MaxHeap is empty");
        }
        this.swap(0, this.array.getSize() - 1);
        E ret = this.array.removeLast();
        this.siftDown(0);
        return ret;
    }

    private void siftDown(int startIndex) {
        for (int parentIndex = startIndex; this.getLeftChildIndex(parentIndex) < this.array.getSize();) {
            E parent = this.array.get(parentIndex);
            int leftChildIndex = this.getLeftChildIndex(parentIndex);
            E leftChild = this.array.get(leftChildIndex);
            int rightChildIndex = this.getRightChildIndex(parentIndex);
            E rightChild = rightChildIndex >= this.array.getSize() ? null : this.array.get(rightChildIndex);
            int biggerChildIndex = rightChild == null ? leftChildIndex
                    : (leftChild.compareTo(rightChild) > 0 ? leftChildIndex : rightChildIndex);
            E biggerChild = this.array.get(biggerChildIndex);
            if (parent.compareTo(biggerChild) >= 0) {
                break;
            } else {
                this.swap(biggerChildIndex, parentIndex);
                parentIndex = biggerChildIndex;
            }
        }
        return;
    }

    private int getLeftChildIndex(int parentIndex) {
        return 2 * parentIndex + 1;
    }

    private int getRightChildIndex(int parentIndex) {
        return 2 * parentIndex + 2;
    }

    public E replaceMax(E e) {
        E ret = this.getMax();
        this.array.set(0, e);
        this.siftDown(0);
        return ret;
    }

    public MaxHeap(E[] array) {
        this.array = new Array<>(array.length);
        for (E e : array) {
            this.array.addLast(e);
        }
        if (this.array.getSize() > 1) {
            for (int i = this.getParentIndex(this.array.getSize() - 1); i >= 0; i--) {
                this.siftDown(i);
            }
        }
        return;
    }

    @Override
    public String toString() {
        return this.array.toString().replaceFirst("Array", "MaxHeap");
    }
}