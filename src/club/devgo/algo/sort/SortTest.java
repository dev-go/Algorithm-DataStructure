// Copyright (c) 2020, devgo.club
// All rights reserved.

package club.devgo.algo.sort;

import java.util.Arrays;

public class SortTest {
    public static void main(String[] args) {
        Integer[] array = { 6, 5, 2, 8, 7, 0, 3, 1, 4, 9 };
        System.out.println(Arrays.toString(array));
        new QuickSort3Ways<Integer>().sort(array);
        System.out.println(Arrays.toString(array));
        System.out.println(SortUtil.isIntArraySorted(array));

        System.out.println("==============================");

        int n = 100000;

        System.out.println("=============== Random (big range) ===============");

        Integer[] array11 = SortUtil.generateRandomIntArray(n, 0, n);
        Integer[] array12 = SortUtil.copyIntArray(array11);
        Integer[] array13 = SortUtil.copyIntArray(array11);
        Integer[] array14 = SortUtil.copyIntArray(array11);
        Integer[] array15 = SortUtil.copyIntArray(array11);
        Integer[] array16 = SortUtil.copyIntArray(array11);
        Integer[] array17 = SortUtil.copyIntArray(array11);
        double time11 = SortUtil.countTime(array11, new SelectSort<Integer>());
        double time12 = SortUtil.countTime(array12, new InsertSort<Integer>());
        double time13 = SortUtil.countTime(array13, new MergeSort<Integer>());
        double time14 = SortUtil.countTime(array14, new MergeSortNR<Integer>());
        double time15 = SortUtil.countTime(array15, new QuickSort<Integer>());
        double time16 = SortUtil.countTime(array16, new QuickSortNR<Integer>());
        double time17 = SortUtil.countTime(array17, new QuickSort3Ways<Integer>());
        System.out.println(String.format("select sort: %s, time=%f",
                SortUtil.isIntArraySorted(array11) ? "ok" : "failed", time11));
        System.out.println(String.format("insert sort: %s, time=%f",
                SortUtil.isIntArraySorted(array12) ? "ok" : "failed", time12));
        System.out.println(
                String.format("merge sort: %s, time=%f", SortUtil.isIntArraySorted(array13) ? "ok" : "failed", time13));
        System.out.println(String.format("merge sort nr: %s, time=%f",
                SortUtil.isIntArraySorted(array14) ? "ok" : "failed", time14));
        System.out.println(
                String.format("quick sort: %s, time=%f", SortUtil.isIntArraySorted(array15) ? "ok" : "failed", time15));
        System.out.println(String.format("quick sort nr: %s, time=%f",
                SortUtil.isIntArraySorted(array16) ? "ok" : "failed", time16));
        System.out.println(String.format("quick sort 3ways: %s, time=%f",
                SortUtil.isIntArraySorted(array17) ? "ok" : "failed", time17));

        System.out.println("=============== Random (small range) ===============");

        Integer[] array21 = SortUtil.generateRandomIntArray(n, 0, 10);
        Integer[] array22 = SortUtil.copyIntArray(array21);
        Integer[] array23 = SortUtil.copyIntArray(array21);
        Integer[] array24 = SortUtil.copyIntArray(array21);
        Integer[] array25 = SortUtil.copyIntArray(array21);
        Integer[] array26 = SortUtil.copyIntArray(array21);
        Integer[] array27 = SortUtil.copyIntArray(array21);
        double time21 = SortUtil.countTime(array21, new SelectSort<Integer>());
        double time22 = SortUtil.countTime(array22, new InsertSort<Integer>());
        double time23 = SortUtil.countTime(array23, new MergeSort<Integer>());
        double time24 = SortUtil.countTime(array24, new MergeSortNR<Integer>());
        double time25 = SortUtil.countTime(array25, new QuickSort<Integer>());
        double time26 = SortUtil.countTime(array26, new QuickSortNR<Integer>());
        double time27 = SortUtil.countTime(array27, new QuickSort3Ways<Integer>());
        System.out.println(String.format("select sort: %s, time=%f",
                SortUtil.isIntArraySorted(array21) ? "ok" : "failed", time21));
        System.out.println(String.format("insert sort: %s, time=%f",
                SortUtil.isIntArraySorted(array22) ? "ok" : "failed", time22));
        System.out.println(
                String.format("merge sort: %s, time=%f", SortUtil.isIntArraySorted(array23) ? "ok" : "failed", time23));
        System.out.println(String.format("merge sort nr: %s, time=%f",
                SortUtil.isIntArraySorted(array24) ? "ok" : "failed", time24));
        System.out.println(
                String.format("quick sort: %s, time=%f", SortUtil.isIntArraySorted(array25) ? "ok" : "failed", time25));
        System.out.println(String.format("quick sort nr: %s, time=%f",
                SortUtil.isIntArraySorted(array26) ? "ok" : "failed", time26));
        System.out.println(String.format("quick sort 3ways: %s, time=%f",
                SortUtil.isIntArraySorted(array27) ? "ok" : "failed", time27));

        System.out.println("=============== Almost Sorted ===============");

        Integer[] array31 = SortUtil.generateAlmostSortedIntArray(n, 10);
        Integer[] array32 = SortUtil.copyIntArray(array31);
        Integer[] array33 = SortUtil.copyIntArray(array31);
        Integer[] array34 = SortUtil.copyIntArray(array31);
        Integer[] array35 = SortUtil.copyIntArray(array31);
        Integer[] array36 = SortUtil.copyIntArray(array31);
        Integer[] array37 = SortUtil.copyIntArray(array31);
        double time31 = SortUtil.countTime(array31, new SelectSort<Integer>());
        double time32 = SortUtil.countTime(array32, new InsertSort<Integer>());
        double time33 = SortUtil.countTime(array33, new MergeSort<Integer>());
        double time34 = SortUtil.countTime(array34, new MergeSortNR<Integer>());
        double time35 = SortUtil.countTime(array35, new QuickSort<Integer>());
        double time36 = SortUtil.countTime(array36, new QuickSortNR<Integer>());
        double time37 = SortUtil.countTime(array37, new QuickSort3Ways<Integer>());
        System.out.println(String.format("select sort: %s, time=%f",
                SortUtil.isIntArraySorted(array31) ? "ok" : "failed", time31));
        System.out.println(String.format("insert sort: %s, time=%f",
                SortUtil.isIntArraySorted(array32) ? "ok" : "failed", time32));
        System.out.println(
                String.format("merge sort: %s, time=%f", SortUtil.isIntArraySorted(array33) ? "ok" : "failed", time33));
        System.out.println(String.format("merge sort nr: %s, time=%f",
                SortUtil.isIntArraySorted(array34) ? "ok" : "failed", time34));
        System.out.println(
                String.format("quick sort: %s, time=%f", SortUtil.isIntArraySorted(array35) ? "ok" : "failed", time35));
        System.out.println(String.format("quick sort nr: %s, time=%f",
                SortUtil.isIntArraySorted(array36) ? "ok" : "failed", time36));
        System.out.println(String.format("quick sort 3ways: %s, time=%f",
                SortUtil.isIntArraySorted(array37) ? "ok" : "failed", time37));
        return;
    }
}