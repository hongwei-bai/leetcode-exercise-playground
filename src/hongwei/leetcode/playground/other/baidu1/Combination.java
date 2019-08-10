package hongwei.leetcode.playground.other.baidu1;

public class Combination {
    public void printAllCombination(int[] a) {
        printRecursive(a, "");
    }

    public void printRecursive(int[] a, String s) {
        if (a.length == 1) {
            System.out.println(s + " " + a[0]);
            return;
        }

        for (int i = 0; i < a.length; i++) {
            String s1 = s + " " + a[i];
            printRecursive(removeElement(a, i), s1);
        }
    }

    public int[] removeElement(int[] a, int idx) {
        int[] r = new int[a.length - 1];
        for (int i = 0; i < idx; i++) {
            r[i] = a[i];
        }
        for (int i = idx; i < a.length - 1; i++) {
            r[i] = a[i + 1];
        }
        return r;
    }

    public static void main(String[] args) {
        Combination combination = new Combination();
        int[] a = { 1, 2, 3, 4 };
        combination.printAllCombination(a);
    }
}
