// Copyright (c) 2020, devgo.club
// All rights reserved.

package club.devgo.ds.stack.impl;

import club.devgo.ds.array.Array;
import club.devgo.ds.stack.Stack;

public class ArrayStack<E> implements Stack<E> {
    private Array<E> array;

    public ArrayStack() {
        this.array = new Array<>();
        return;
    }

    public ArrayStack(int capacity) {
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
    public void push(E e) {
        this.array.addLast(e);
        return;
    }

    @Override
    public E pop() {
        return this.array.removeLast();
    }

    @Override
    public E peek() {
        return this.array.get(this.array.getSize() - 1);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("ArrayStack(size=%d, capacity=%d): [", this.array.getSize(), this.array.getCapacity()));
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