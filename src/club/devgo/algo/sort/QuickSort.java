// Copyright (c) 2020, devgo.club
// All rights reserved.

package club.devgo.algo.sort;

import java.util.Random;

public class QuickSort<E extends Comparable<E>> implements Sorter<E> {
    public QuickSort() {
        return;
    }

    public void sort(E[] array) {
        if (array.length <= 1) {
            return;
        }
        Random random = new Random(System.currentTimeMillis());
        this.quickSort(array, 0, array.length, random);
        return;
    }

    private void quickSort(E[] array, int i, int j, Random random) {
        if (i + 1 >= j) {
            return;
        }
        int m = this.partition(array, i, j, random);
        this.quickSort(array, i, m - 1, random);
        this.quickSort(array, m, j, random);
        return;
    }

    private int partition(E[] array, int i, int j, Random random) {
        int rand = random.nextInt(j - i) + i;
        E pivot = array[rand];
        array[rand] = array[i];
        array[i] = pivot;
        int m = i + 1;
        for (int k = i + 1; k < j; k++) {
            if (array[k].compareTo(pivot) < 0) {
                E tmp = array[k];
                array[k] = array[m];
                array[m] = tmp;
                m++;
            }
        }
        array[i] = array[m - 1];
        array[m - 1] = pivot;
        return m;
    }
}