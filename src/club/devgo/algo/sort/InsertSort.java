// Copyright (c) 2020, devgo.club
// All rights reserved.

package club.devgo.algo.sort;

public class InsertSort<E extends Comparable<E>> implements Sorter<E> {
    public InsertSort() {
        return;
    }

    public void sort(E[] array) {
        if (array.length <= 1) {
            return;
        }
        for (int i = 1; i < array.length; ++i) {
            E tmp = array[i];
            int j;
            for (j = i; j > 0; --j) {
                if (array[j - 1].compareTo(tmp) > 0) {
                    array[j] = array[j - 1];
                } else {
                    break;
                }
            }
            array[j] = tmp;
        }
        return;
    }
}