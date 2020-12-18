// Copyright (c) 2020, devgo.club
// All rights reserved.

package club.devgo.algo.sort;

public class MergeSortNR<E extends Comparable<E>> implements Sorter<E> {
    public MergeSortNR() {
        return;
    }

    @SuppressWarnings({ "unchecked" })
    public void sort(E[] array) {
        if (array.length <= 1) {
            return;
        }
        E[] tmp = (E[]) (new Comparable[array.length]);
        for (int size = 1; size <= array.length; size += size) {
            for (int i = 0; i + size < array.length; i += size + size) {
                this.merge(array, i, i + size, Math.min(i + size + size, array.length), tmp);
            }
        }
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