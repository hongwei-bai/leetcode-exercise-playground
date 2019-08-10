package hongwei.leetcode.playground.other.jd;

import java.util.ArrayList;

public class QSortArray {
    public static ArrayList<Integer> sort(ArrayList<Integer> a) {
        return new QSortArray().qsort(a);
    }

    private ArrayList<Integer> qsort(ArrayList<Integer> a) {
        return qsort(a, 0, a.size() - 1);
    }

    private ArrayList<Integer> qsort(ArrayList<Integer> a, int low, int high) {
        int pivot;
        final int INSERT_THREADHOLD = 7;
        if ((high - low) > INSERT_THREADHOLD) {
            while (low < high) {
                pivot = partition(a, low, high);

                a = qsort(a, low, pivot - 1);
                low = pivot + 1;
            }
        } else {
            InsertSortArray.sort(a, low, high);
        }
        return a;
    }

    private int partition(ArrayList<Integer> a, int low, int high) {
        int pivotKey = a.get(low);
        while (low < high) {

            while (low < high && pivotKey <= a.get(high)) {
                high--;
            }
            a.set(low, a.get(high));

            while (low < high && pivotKey >= a.get(low)) {
                low++;
            }
            a.set(high, a.get(low));
        }
        a.set(low, pivotKey);

        return low;
    }

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
//        list.add(3);
//        list.add(2);
//        list.add(6);
        list.add(7);
        list.add(4);
        QSortArray.sort(list);
        for (int i : list) {
            System.out.print(i + " ");
        }
    }
}
