// Copyright (c) 2020, devgo.club
// All rights reserved.

package club.devgo.ds.stack;

public interface Stack<E> {
    int getSize();

    boolean isEmpty();

    void push(E e);

    E pop();

    E peek();
}