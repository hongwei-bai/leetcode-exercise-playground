package hongwei.leetcode.playground.other.sort;

import java.util.ArrayList;
import java.util.List;

public class QuickSort extends Sort {
    public QuickSort(List<Integer> list) {
        super(list);
    }

    @Override
    protected List<Integer> sortImpl(List<Integer> l) {
        List<Integer> list = new ArrayList<>(l);
        QSort(list, 0, list.size() - 1);
        return list;
    }

    private void QSort(List<Integer> list, int low, int high) {
        int pivot;
        if (low < high) {
            pivot = partition(list, low, high);

            QSort(list, low, pivot);
            QSort(list, pivot + 1, high);
        }
    }

    private int partition(List<Integer> list, int low, int high) {
        int pivotKey = list.get(low);
        while (low < high) {

            while (low < high && pivotKey <= list.get(high)) {
                high--;
            }
            swap(list, low, high);

            while (low < high && pivotKey >= list.get(low)) {
                low++;
            }
            swap(list, low, high);
        }

        return low;
    }

    private void swap(List<Integer> list, int idx1, int idx2) {
        int t = list.get(idx1);
        list.set(idx1, list.get(idx2));
        list.set(idx2, t);
    }

    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort(createTestList());
        quickSort.printOriginalList();
        quickSort.sort();
        quickSort.printResultList();
    }

    private static List<Integer> createTestList() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(40);
        list.add(32);
        list.add(61);
        list.add(94);
        list.add(1);
        list.add(20);
        list.add(9);
        return list;
    }
}
