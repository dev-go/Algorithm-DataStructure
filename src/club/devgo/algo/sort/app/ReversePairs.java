// Copyright (c) 2020, devgo.club
// All rights reserved.

package club.devgo.algo.sort.app;

public class ReversePairs {
    public int reversePairs(int[] nums) {
        if (nums.length <= 1) {
            return 0;
        }
        int[] copy = new int[nums.length];
        return this.mergeSort(nums, 0, nums.length, copy);
    }

    private int mergeSort(int[] nums, int i, int j, int[] copy) {
        if (i + 1 >= j) {
            return 0;
        }
        int mid = (j - i) / 2 + i;
        int resLeft = this.mergeSort(nums, i, mid, copy);
        int resRight = this.mergeSort(nums, mid, j, copy);
        return resLeft + resRight + this.merge(nums, i, mid, j, copy);
    }

    private int merge(int[] nums, int i, int mid, int j, int[] copy) {
        int count = 0;
        for (int k = i; k < j; k++) {
            copy[k] = nums[k];
        }
        int m = i;
        int n = mid;
        for (int k = i; k < j; k++) {
            if (m >= mid) {
                nums[k] = copy[n];
                n++;
            } else if (n >= j) {
                nums[k] = copy[m];
                m++;
            } else if (copy[m] <= copy[n]) {
                nums[k] = copy[m];
                m++;
            } else {
                nums[k] = copy[n];
                n++;
                count += mid - m;
            }
        }
        return count;
    }
}