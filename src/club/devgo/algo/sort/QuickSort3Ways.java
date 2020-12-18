// Copyright (c) 2020, devgo.club
// All rights reserved.

package club.devgo.algo.sort;

import java.util.Random;

public class QuickSort3Ways<E extends Comparable<E>> implements Sorter<E> {
    public QuickSort3Ways() {
        return;
    }

    public void sort(E[] array) {
        if (array.length <= 1) {
            return;
        }
        Random random = new Random(System.currentTimeMillis());
        this.quickSort3Ways(array, 0, array.length, random);
        return;
    }

    private void quickSort3Ways(E[] array, int i, int j, Random random) {
        if (i + 1 >= j) {
            return;
        }
        int rand = random.nextInt(j - i) + i;
        E pivot = array[rand];
        array[rand] = array[i];
        array[i] = pivot;
        // <pivot : [i+1, m)
        // >pivot : [n, j)
        int m = i + 1;
        int n = j;
        for (int k = i + 1; k < n; k++) {
            if (array[k].compareTo(pivot) < 0) {
                E tmp = array[m];
                array[m] = array[k];
                array[k] = tmp;
                m++;
            } else if (array[k].compareTo(pivot) > 0) {
                E tmp = array[n - 1];
                array[n - 1] = array[k];
                array[k] = tmp;
                n--;
                k--;
            }
        }
        array[i] = array[m - 1];
        array[m - 1] = pivot;

        this.quickSort3Ways(array, i, m - 1, random);
        this.quickSort3Ways(array, n, j, random);
        return;
    }
}