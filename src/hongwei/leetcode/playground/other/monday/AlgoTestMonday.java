package hongwei.leetcode.playground.other.monday;

import hongwei.leetcode.playground.common.LogJava;

public class AlgoTestMonday {
    private final static boolean runTest1 = true;
    private final static boolean runTest2 = true;
    // private final static boolean runTest3 = false;

    public static void main(String[] args) {
        if (runTest1) {
            AlgoTest1 algoTest1 = new AlgoTest1();
            String result = algoTest1.combine("abc", "cbad");
            LogJava.i("[Test2]" + result);
        }

        if (runTest2) {
            AlgoTest2 algoTest2 = new AlgoTest2();
            int a[] = { 6, 4, 2, 3, 5 };
            int k = 4;
            int result = algoTest2.getKthLargestInMultiplyArrayOf(a, k);
            if (result > 0) {
                LogJava.i("[Test2]The " + k + "th largest value in L is " + result);
            }
        }

    }
}
