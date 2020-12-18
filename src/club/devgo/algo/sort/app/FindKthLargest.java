// Copyright (c) 2020, devgo.club
// All rights reserved.

package club.devgo.algo.sort.app;

import java.util.Random;

class FindKthLargest {
    public int findKthLargest(int[] nums, int k) {
        Random random = new Random(System.currentTimeMillis());
        return this.quickSort(nums, nums.length - k, 0, nums.length, random);
    }

    private int quickSort(int[] nums, int k, int i, int j, Random random) {
        if (i + 1 >= j) {
            return nums[i];
        }
        int rand = random.nextInt(j - i) + i;
        // pivot
        int pivot = nums[rand];
        nums[rand] = nums[i];
        nums[i] = pivot;

        // <pivot : [i, m)
        // >pivot : [m, n)
        int m = i + 1;
        for (int n = i + 1; n < j; n++) {
            if (nums[n] <= pivot) {
                int tmp = nums[m];
                nums[m] = nums[n];
                nums[n] = tmp;
                m++;
            }
        }
        nums[i] = nums[m - 1];
        nums[m - 1] = pivot;

        // pivot index m-1
        if (m - 1 == k) {
            return nums[m - 1];
        } else if (m - 1 > k) {
            return this.quickSort(nums, k, i, m - 1, random);
        } else {
            return this.quickSort(nums, k, m, j, random);
        }
    }
}