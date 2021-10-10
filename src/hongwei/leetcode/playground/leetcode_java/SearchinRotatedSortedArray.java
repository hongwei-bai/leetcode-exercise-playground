package hongwei.leetcode.playground.leetcode_java;

import hongwei.leetcode.playground.IQuestion;
import hongwei.leetcode.playground.common.LogJava;

public class SearchinRotatedSortedArray implements IQuestion {
    @Override
    public void run() {
        LogJava.i("aaaa", "------- run -------");
        int[][] input = new int[][]{{4, 5, 6, 7, 0, 1, 2}, {4, 5, 6, 7, 0, 1, 2}};
        int[] input2 = new int[]{0, 3};
        int[] ref = new int[]{4, -1};

        for (int i = 0; i < input.length; i++) {
            int result = search(input[i], input2[i]);
            if (result == ref[i]) {
                LogJava.i("aaaa", "case[" + i + "] passed");
            } else {
                LogJava.e("aaaa", "!!!!![WRONG]case[" + i + "] failed, result: " + result);
            }
        }
    }

    /*
     * 1. No duplicate elements in array.
     * 2. runtime complexity must be in the order of O(log n).
     */
    public int search(int[] nums, int target) {
        int pivot = findPivotFirst(nums, 0, nums.length - 1);
//        Log.i("pivot: " + pivot);

        //test getVirtualPos
//        Log.i("0 -> " + getVirtualPos(nums.length, pivot, 0));
//        Log.i("1 -> " + getVirtualPos(nums.length, pivot, 1));
//        Log.i("2 -> " + getVirtualPos(nums.length, pivot, 2));
//        Log.i("3 -> " + getVirtualPos(nums.length, pivot, 3));
//        Log.i("4 -> " + getVirtualPos(nums.length, pivot, 4));
//        Log.i("5 -> " + getVirtualPos(nums.length, pivot, 5));
//        Log.i("6 -> " + getVirtualPos(nums.length, pivot, 6));

        //test getActualPos
//        Log.i("0 -> " + getActualPos(nums.length, pivot, 0));
//        Log.i("1 -> " + getActualPos(nums.length, pivot, 1));
//        Log.i("2 -> " + getActualPos(nums.length, pivot, 2));
//        Log.i("3 -> " + getActualPos(nums.length, pivot, 3));
//        Log.i("4 -> " + getActualPos(nums.length, pivot, 4));
//        Log.i("5 -> " + getActualPos(nums.length, pivot, 5));
//        Log.i("6 -> " + getActualPos(nums.length, pivot, 6));

        //test getValByVirtualPos
//        Log.i("0 -> " + getValByVirtualPos(nums, nums.length, pivot, 0));
//        Log.i("1 -> " + getValByVirtualPos(nums, nums.length, pivot, 1));
//        Log.i("2 -> " + getValByVirtualPos(nums, nums.length, pivot, 2));
//        Log.i("3 -> " + getValByVirtualPos(nums, nums.length, pivot, 3));
//        Log.i("4 -> " + getValByVirtualPos(nums, nums.length, pivot, 4));
//        Log.i("5 -> " + getValByVirtualPos(nums, nums.length, pivot, 5));
//        Log.i("6 -> " + getValByVirtualPos(nums, nums.length, pivot, 6));

        // Virtual binary search
        final int length = nums.length;
        int vlo = 0;
        int vhi = length - 1;
        while (vlo < vhi) {
            LogJava.i("while(" + vlo + " ~ " + vhi + ")");
            int vmid = (vhi - vlo + 1) / 2 + vlo;
            int midVal = getValByVirtualPos(nums, length, pivot, vmid);
            if (target == midVal) {
                return getActualPos(length, pivot, vmid);
            } else if (target > midVal) {
                vlo = vmid;
                continue;
            } else {
                if (vlo + 1 == vhi) {
                    if (target == vlo) {
                        return getActualPos(length, pivot, vlo);
                    }
                }
                vhi = vmid;
                continue;
            }
        }
        return -1;
    }

    private int getValByVirtualPos(int[] nums, int length, int pivot, int i) {
        return nums[getActualPos(length, pivot, i)];
    }

    private int getVirtualPos(int length, int pivot, int i) {
        if (i < pivot) {
            return length - pivot + i;
        } else {
            return i - pivot;
        }
    }

    private int getActualPos(int length, int pivot, int v) {
        if (v < length - pivot) {
            return v + pivot;
        } else {
            return v + pivot - length;
        }
    }

    private int findPivotFirst(int[] nums, int lo, int hi) {
        if (lo == hi) {
            return hi;
        }

        if (lo + 1 == hi) {
            if (nums[lo] > nums[hi]) {
                return hi;
            } else {
                return lo;
            }
        }

        int lo3 = (hi - lo + 1) / 3 + lo;
        int hi3 = (hi - lo + 1) * 2 / 3 + lo;

//        Log.i("find: (" + lo + "~" + hi + ") nums are: " + nums[lo3] + " & " + nums[hi3]);
        // pivot is between lo3 and hi3
        if (nums[lo3] > nums[hi3]) {
            return findPivotFirst(nums, lo3, hi3);
        } else if (nums[lo3] > nums[lo]) {
            // pivot is after hi3
            return findPivotFirst(nums, hi3, hi);
        } else {
            // pivot is before lo3
            return findPivotFirst(nums, lo, lo3);
        }
    }
}
