package hongwei.leetcode.playground.other.sunday;

import java.util.ArrayList;

import hongwei.leetcode.playground.common.LogJava;
import hongwei.leetcode.playground.other.sort.QuickSort;

public class BinarySearch {
    ArrayList<Integer> list;

    public void init(ArrayList<Integer> l) {
        list = l;
        QuickSort quickSort = new QuickSort(l);
        quickSort.sort();
        list = (ArrayList<Integer>) quickSort.getResultList();
        LogJava.i("sorted list: " + list.toString());
    }

    public int find(int number) {
        int low = 0;
        int high = list.size() - 1;
        int mid;
        for (; low <= high;) {
            mid = (high - low) / 2 + low;
            if (number == list.get(mid)) {
                return mid;
            }

            if (number > list.get(mid)) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }
}
