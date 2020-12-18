// Copyright (c) 2020, devgo.club
// All rights reserved.

package club.devgo.algo.sort;

import java.util.Random;
import java.util.Stack;

public class QuickSortNR<E extends Comparable<E>> implements Sorter<E> {
    public QuickSortNR() {
        return;
    }

    public void sort(E[] array) {
        if (array.length <= 1) {
            return;
        }
        Random random = new Random(System.currentTimeMillis());
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        stack.push(array.length);
        for (; !stack.isEmpty();) {
            int j = stack.pop();
            int i = stack.pop();
            if (i + 1 >= j) {
                continue;
            }
            int m = this.partition(array, i, j, random);
            stack.push(i);
            stack.push(m - 1);
            stack.push(m);
            stack.push(j);
        }
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