// Copyright (c) 2020, devgo.club
// All rights reserved.

package club.devgo.algo.sort;

import java.util.Random;

public class SortUtil {

    public static boolean isIntArraySorted(Integer[] array) {
        for (int i = 0; i < array.length - 1; ++i) {
            if (array[i].compareTo(array[i + 1]) > 0) {
                return false;
            }
        }
        return true;
    }

    public static Integer[] generateRandomIntArray(int length, int rangeL, int rangeR) {
        Integer[] array = new Integer[length];
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < length; i++) {
            array[i] = random.nextInt(rangeR - rangeL) % (rangeR - rangeL + 1) + rangeL;
        }
        return array;
    }

    public static Integer[] generateAlmostSortedIntArray(int length, int swap) {
        Integer[] array = new Integer[length];
        for (int i = 0; i < length; ++i) {
            array[i] = i;
        }
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < swap; i++) {
            int m = random.nextInt(length);
            int n = random.nextInt(length);
            Integer tmp = array[m];
            array[m] = array[n];
            array[n] = tmp;
        }
        return array;
    }

    public static Integer[] copyIntArray(Integer[] array) {
        Integer[] ret = new Integer[array.length];
        for (int i = 0; i < array.length; ++i) {
            ret[i] = array[i];
        }
        return ret;
    }

    public static double countTime(Integer[] array, Sorter<Integer> sorter) {
        long start = System.currentTimeMillis();
        sorter.sort(array);
        long end = System.currentTimeMillis();
        return (end - start) * 1.0 / 1000;
    }
}