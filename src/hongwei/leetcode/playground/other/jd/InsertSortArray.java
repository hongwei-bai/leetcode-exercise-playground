package hongwei.leetcode.playground.other.jd;

import java.util.ArrayList;

public class InsertSortArray {
    public static ArrayList<Integer> sort(ArrayList<Integer> a) {
        return sort(a, 0, a.size() - 1);
    }

    public static ArrayList<Integer> sort(ArrayList<Integer> a, int start, int end) {
        for (int i = start; i <= end; i++) {
            for (int j = i; j > start; j--) {
                if (a.get(j) < a.get(j - 1)) {
                    int t = a.get(j - 1);
                    a.set(j - 1, a.get(j));
                    a.set(j, t);
                }
            }
        }
        return a;
    }

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(7);
        list.add(4);
        InsertSortArray.sort(list, 0, 1);
        for (int i : list) {
            System.out.print(i + " ");
        }
    }
}
