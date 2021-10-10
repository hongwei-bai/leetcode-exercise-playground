package hongwei.leetcode.playground.other.handwrite;

import hongwei.leetcode.playground.common.LogJava;

public class QuickSort1 {
    public void quickSort(int[] a, int low, int high) {
        int pivot = 0;
        if (high - low > 0) {
            while (low < high) {
                pivot = partition(a, low, high);

                quickSort(a, low, pivot - 1);
                low = pivot + 1;
            }
        } else {
            // insertSort(a, high);
        }
    }

    private int partition(int[] a, int low, int high) {
        int pivotKey = a[low];
        while (low < high) {
            while (low < high && pivotKey <= a[high])
                high--;
            a[low] = a[high];
            while (low < high && pivotKey >= a[low])
                low++;
            a[high] = a[low];
            a[low] = pivotKey;
        }
        return low;
    }

    public static void main(String[] args) {
        QuickSort1 quickSort1 = new QuickSort1();
        int[] a = { 3, 4, 1, 5, 9, 6, 2 };
        quickSort1.quickSort(a, 0, 6);
        for (int num : a) {
            LogJava.d(num + " ");
        }
    }
}
