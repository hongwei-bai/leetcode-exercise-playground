package hongwei.leetcode.playground.other.jdbk;

import hongwei.leetcode.playground.other.utils.SequenceGenerator;
import hongwei.leetcode.playground.other.jd.Algorithm1;
import hongwei.leetcode.playground.other.jd.Algorithm3;
import hongwei.leetcode.playground.other.jd.Brute;
import hongwei.leetcode.playground.other.jd.Result;
import hongwei.leetcode.playground.other.jd.Verify;

public class AscendingArray {
    public int[] initArray() {
        // int[] a = SequenceGenerator.generateAscendArray(6400, 5000);
//        int[] a = SequenceGenerator.generateAscendArray(16, 5);
         int[] a = SequenceGenerator.generateAscendArraySpecialType1(6400000,
         10, 10000, 5000);
        return a;
    }

    public static void main(String[] args) {
        AscendingArray ascendingArray = new AscendingArray();
        int[] a = ascendingArray.initArray();
//        printArray("ascendingArray", a);

        Stat.mark();
        Result r0 = new Brute().run(a);
        r0.print();
        System.out.println("time = " + Stat.getMs() + " ms");

        Stat.mark();
        new Algorithm1().run(a).print();
        System.out.println("time = " + Stat.getMs() + " ms");

        Stat.mark();
        Result r3 = new Algorithm3().run(a);
        r3.print();
        System.out.println("time = " + Stat.getMs() + " ms");

        System.out.println("verify: " + Verify.verify(r0, r3));
    }
    private static class Stat {
        private static long t0 = 0;

        public static void mark() {
            t0 = System.currentTimeMillis();
        }

        public static long getMs() {
            return System.currentTimeMillis() - t0;
        }
    }

    public static void printArray(String name, int[] a) {
        String s = "";
        s += "array " + name + " has " + a.length + " elements: ";
        for (int i : a) {
            s += i + " ";
        }
        System.out.println(s);
    }
}
