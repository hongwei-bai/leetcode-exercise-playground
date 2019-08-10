package hongwei.leetcode.playground.other.utils;

public class BinarySearch {
    public static int find(int n, int[] a) {
        int low = 0;
        int high = a.length - 1;
        int mid;
        for (; low <= high;) {
            mid = (high - low) / 2 + low;
            if (n == a[mid]) {
                return mid;
            }

            if (n > a[mid]) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    public static int findClosestLeftNumber(int n, int[] a) {
        int low = 0;
        int high = a.length - 1;
        int mid;
        for (; low <= high;) {
            mid = (high - low) / 2 + low;
            if (n == a[mid]) {
                return mid;
            }

            if (n > a[mid]) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return high;
    }
}
