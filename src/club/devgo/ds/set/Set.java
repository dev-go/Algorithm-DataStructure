// Copyright (c) 2020, devgo.club
// All rights reserved.

package club.devgo.ds.set;

public interface Set<E> {
    int getSize();

    boolean isEmpty();

    void add(E e);

    boolean contains(E e);

    void remove(E e);
}