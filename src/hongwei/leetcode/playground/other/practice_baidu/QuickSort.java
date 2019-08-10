package hongwei.leetcode.playground.other.practice_baidu;

public class QuickSort {
    public int[] quickSort(int[] a) {
        return qSort(a, 0, a.length - 1);
    }

    private int[] qSort(int[] a, int low, int high) {
        if (low < high) {
            while (low < high) {
                int pivot = partition(a, low, high);
                qSort(a, low, pivot - 1);
                low = pivot + 1;
            }
        }
        return a;
    }

    private int partition(int[] a, int low, int high) {
        int pivotKey = a[low];
        while (low < high) {
            while (low < high && pivotKey <= a[high]) {
                high--;
            }
            a[low] = a[high];
            while (low < high && pivotKey >= a[low]) {
                low++;
            }
            a[high] = a[low];
        }
        a[low] = pivotKey;
        return low;
    }

    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();
        int[] a = { 1, 7, 9, 21, 4, 33, 2 };
        int[] r = quickSort.quickSort(a);
        for (int i : r) {
            System.out.print(i + " ");
        }
    }
}
