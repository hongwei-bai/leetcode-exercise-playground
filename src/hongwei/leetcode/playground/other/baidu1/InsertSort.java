package hongwei.leetcode.playground.other.baidu1;

public class InsertSort {
    public int[] insertSort(int a[]) {
        for (int i = 0; i < a.length; i++) {
            for (int j = i; j > 0; j--) {
                if (a[j] < a[j - 1]) {
                    int t = a[j - 1];
                    a[j - 1] = a[j];
                    a[j] = t;
                }
            }
        }
        return a;
    }

    public static void main(String[] args) {
        int[] a = { 5, 6, 7, 3, 5, 132, 5, 1 };
        int[] r = new InsertSort().insertSort(a);
        for (int n : r) {
            System.out.print(n + " ");
        }
    }
}
