package hongwei.leetcode.playground.other.utils;

import java.util.Random;

public class SequenceGenerator {

    public static int[] generateAscendArray(int n, int incLimit) {
        int[] a = new int[n];

        Random r = new Random(System.currentTimeMillis());
        a[0] = Math.abs(r.nextInt() % 3) + 1;
        for (int i = 1; i < n; i++) {
            a[i] = a[i - 1] + Math.abs(r.nextInt()) % incLimit + 1;
        }

        return a;
    }

    public static int[] generateAscendArraySpecialType1(int n, int incLimit, int incLargeBase, int odd) {
        int[] a = new int[n];

        Random r = new Random(System.currentTimeMillis());
        a[0] = Math.abs(r.nextInt() % 3) + 1;
        for (int i = 1; i < n; i++) {
            int num = Math.abs(r.nextInt()) % odd;
            if (num == 0) {
                a[i] = a[i - 1] + Math.abs(r.nextInt()) % incLimit + incLargeBase + 1;
            } else {
                a[i] = a[i - 1] + Math.abs(r.nextInt()) % incLimit + 1;
            }
        }

        return a;
    }
}
