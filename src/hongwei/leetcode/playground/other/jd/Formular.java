package hongwei.leetcode.playground.other.jd;

public class Formular {
    public static int formula1(int start, int end, int min, int max) {
        return formula1(end - start + 1, min, max);
    }

    public static int formula1(int n, int min, int max) {
        return max - min - (n - 2);
    }

    public static int formula2(int maxPossibleInterval, int diff) {
        return maxPossibleInterval - (diff - 1);
    }
}
