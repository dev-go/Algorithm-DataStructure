// Copyright (c) 2020, devgo.club
// All rights reserved.

package club.devgo.ds.queue;

public interface Queue<E> {
    int getSize();

    boolean isEmpty();

    void enqueue(E e);

    E dequeue();

    E getFront();
}