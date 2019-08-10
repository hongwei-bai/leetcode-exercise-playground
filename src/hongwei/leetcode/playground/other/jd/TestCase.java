package hongwei.leetcode.playground.other.jd;

import hongwei.leetcode.playground.other.utils.SequenceGenerator;

public class TestCase {
    private final int LOOP = 20;

    public void test(int[] a) {
        System.out.println("id\tAlgorithm\tMax\tVerify\tSteps\tTime(ms)");
        Brute brute = new Brute();
        Algorithm3 algorithm3 = new Algorithm3();
        for (int i = 0; i < LOOP; i++) {
            Stat.mark();
            Result r0 = brute.run(a);
            System.out.println(i + "\t" + r0.name + "\t" + r0.maxValue + "\t-\t\t" + r0.steps + "\t" + Stat.getMs());

            algorithm3.reset();
            Stat.mark();
            Result r = algorithm3.run(a);
            System.out.println(i + "\t" + r.name + "\t" + r.maxValue + "\t" + Verify.verify(r0, r) + "\t" + r.steps
                    + "\t" + Stat.getMs());
        }
    }

    public void test1() {
        test(SequenceGenerator.generateAscendArray(640000, 100));
    }

    public void test2() {
        test(SequenceGenerator.generateAscendArray(640000, 5000));
    }

    public void test3() {
        test(SequenceGenerator.generateAscendArraySpecialType1(640000, 100, 1000, 500));
    }

    public static void main(String[] args) {
        TestCase testCase = new TestCase();
//         testCase.test1();
//        testCase.test2();
        testCase.test3();
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
}
