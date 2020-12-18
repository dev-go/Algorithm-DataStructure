// Copyright (c) 2020, devgo.club
// All rights reserved.

package club.devgo.algo.sort;

public class SelectSort<E extends Comparable<E>> implements Sorter<E> {
    public SelectSort() {
        return;
    }

    public void sort(E[] array) {
        if (array.length <= 1) {
            return;
        }
        for (int i = 0; i < array.length - 1; ++i) {
            int min = i;
            for (int j = i + 1; j < array.length; ++j) {
                if (array[j].compareTo(array[min]) < 0) {
                    min = j;
                }
            }
            if (min != i) {
                E tmp = array[i];
                array[i] = array[min];
                array[min] = tmp;
            }
        }
        return;
    }
}