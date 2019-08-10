package hongwei.leetcode.playground.other.utils;

public class QSort {
    public static int[] sort(int[] a) {
        return new QSort().qsort(a);
    }

    private int[] qsort(int[] a) {
        return qsort(a, 0, a.length - 1);
    }

    private int[] qsort(int[] a, int low, int high) {
        int pivot;
        if (low < high) {
            pivot = partition(a, low, high);

            a = qsort(a, low, pivot);
            a = qsort(a, pivot + 1, high);
        }
        return a;
    }

    private int partition(int[] a, int low, int high) {
        int pivotKey = a[low];
        while (low < high) {

            while (low < high && pivotKey <= a[high]) {
                high--;
            }
            swap(a, low, high);

            while (low < high && pivotKey >= a[low]) {
                low++;
            }
            swap(a, low, high);
        }

        return low;
    }

    private void swap(int[] a, int idx1, int idx2) {
        int t = a[idx1];
        a[idx1] = a[idx2];
        a[idx2] = t;
    }
}
