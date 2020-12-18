// Copyright (c) 2020, devgo.club
// All rights reserved.

package club.devgo.algo.sort;

public class MergeSort<E extends Comparable<E>> implements Sorter<E> {
    public MergeSort() {
        return;
    }

    @SuppressWarnings({ "unchecked" })
    public void sort(E[] array) {
        if (array.length <= 1) {
            return;
        }
        E[] tmp = (E[]) (new Comparable[array.length]);
        this.mergeSort(array, 0, array.length, tmp);
        return;
    }

    private void mergeSort(E[] array, int i, int j, E[] tmp) {
        if (i + 1 == j) {
            return;
        }
        int mid = i + (j - i) / 2;
        this.mergeSort(array, i, mid, tmp);
        this.mergeSort(array, mid, j, tmp);
        this.merge(array, i, mid, j, tmp);
        return;
    }

    private void merge(E[] array, int i, int mid, int j, E[] tmp) {
        if (array[mid - 1].compareTo(array[mid]) <= 0) {
            return;
        }
        for (int k = i; k < j; k++) {
            tmp[k] = array[k];
        }
        int m = i;
        int n = mid;
        for (int k = i; k < j; k++) {
            if (m >= mid) {
                array[k] = tmp[n];
                n++;
            } else if (n >= j) {
                array[k] = tmp[m];
                m++;
            } else if (tmp[m].compareTo(tmp[n]) <= 0) {
                array[k] = tmp[m];
                m++;
            } else {
                array[k] = tmp[n];
                n++;
            }
        }
        return;
    }
}