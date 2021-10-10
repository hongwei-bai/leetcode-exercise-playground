package hongwei.leetcode.playground.other.handwrite;

import hongwei.leetcode.playground.common.LogJava;

public class BinarySearch {
    public int binarySearch(int[] a, int n, int k) {
        int low = 0;
        int high = n - 1;
        int mid;
        while (low <= high) {
            mid = (low + high) >>> 1;
            if (k > a[mid]) {
                low = mid + 1;
            } else if (k < a[mid]) {
                high = mid - 1;
            } else {
                return mid;
            }
        }
        return ~low;
    }

    public static void main(String[] args) {
        BinarySearch binarySearch = new BinarySearch();
        int[] a = { 0, 5, 8, 18, 24, 79 };
        int pos = binarySearch.binarySearch(a, 6, 11);
        LogJava.d("pos = " + pos);
    }
}