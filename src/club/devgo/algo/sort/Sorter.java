// Copyright (c) 2020, devgo.club
// All rights reserved.

package club.devgo.algo.sort;

public interface Sorter<E extends Comparable<E>> {
    void sort(E[] array);
}