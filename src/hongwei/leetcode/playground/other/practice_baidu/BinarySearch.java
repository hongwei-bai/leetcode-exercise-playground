package hongwei.leetcode.playground.other.practice_baidu;

import hongwei.leetcode.playground.common.LogJava;

public class BinarySearch {
    public int binarySearch(int[] a, int k) {
        int n = a.length;
        int lo = 0;
        int hi = n - 1;
        while (lo <= hi) {
            int mid = (lo + hi) >>> 1;
            if (k > a[mid]) {
                lo = mid + 1;
            } else if (k < a[mid]) {
                hi = mid - 1;
            } else {
                return mid;
            }
        }
        return ~lo;
    }

    public static void main(String[] args) {
        BinarySearch binarySearch = new BinarySearch();
        QuickSort quickSort = new QuickSort();
        int[] a = { 1, 7, 9, 21, 4, 33, 2 };
        int[] r = quickSort.quickSort(a);
        int f = binarySearch.binarySearch(r, 33);
        for (int i : r) {
            System.out.print(i + " ");
        }
        LogJava.d("find = " + f);
    }
}
