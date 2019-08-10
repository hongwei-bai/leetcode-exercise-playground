package hongwei.leetcode.playground.leetcode_java;

import hongwei.leetcode.playground.common.Log;
import hongwei.leetcode.playground.IQuestion;

/**
 * Package: com.jd.aibdp.algorithm
 * User: baihongwei1
 * Email: baihongwei1@jd.com
 * Date: 2019/2/11
 * Time: 11:37
 * Description:
 */
public class SubarraySumsDivisiblebyK implements IQuestion {
    @Override
    public void run() {
        Log.i("aaaa", "------- run -------");
        int[][] input = new int[][]{{4, 5, 0, -2, -3, 1}, {-7, 2, 3, 0, -9}, {0, 0, 0}};
        int[] input2 = new int[]{5, 3, 4};
        int[] ref = new int[]{7, 6, 6};

        for (int i = 0; i < input.length; i++) {
            int result = subarraysDivByK(input[i], input2[i]);
            if (result == ref[i]) {
                Log.i("aaaa", "case[" + i + "] passed");
            } else {
                Log.e("aaaa", "!!!!![WRONG]case[" + i + "] failed, result: " + result);
            }
        }
    }

    /*
     Input: A = [4,5,0,-2,-3,1], K = 5
Output: 7
Explanation: There are 7 subarrays with a sum divisible by K = 5:
[4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
     */

    /*
    As is typical with problems involving subarrays, we use prefix sums to add each subarray.
    Let P[i+1] = A[0] + A[1] + ... + A[i]. Then, each subarray can be written as P[j] - P[i] (for j > i).
    Thus, we have P[j] - P[i] equal to 0 modulo K, or equivalently P[i] and P[j] are the same value modulo K.
     */
    public int subarraysDivByK1(int[] A, int K) {
        int N = A.length;
        int[] P = new int[N];
        P[0] = A[0];
        for (int i = 1; i < N; i++) {
            P[i] = P[i - 1] + A[i];
        }

        int ans = 0;
        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                if ((P[j] - P[i]) % K == 0) {
                    ans++;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            if ((P[i]) % K == 0) {
                ans++;
            }
        }

        return ans;
    }

    public int subarraysDivByK(int[] A, int K) {
        int N = A.length;
        int[] P = new int[N + 1];
        for (int i = 0; i < N; ++i)
            P[i + 1] = P[i] + A[i];

        int[] count = new int[K];
        for (int x : P)
            count[(x % K + K) % K]++;

        int ans = 0;
        for (int v : count)
            ans += v * (v - 1) / 2;
        return ans;
    }

    public int subarraysDivByK2(int[] A, int K) {
        int lo, hi;
        lo = 0;

        int ans = 0;
        int sum;
        int zerosLo = 1;
        while (lo < A.length) {
            while (lo < A.length && A[lo] == 0) {
                ans += zerosLo;
                zerosLo++;
                lo++;
            }

            if (lo >= A.length) {
                break;
            }

            hi = lo;
            sum = A[lo];
            while (hi < A.length) {
                boolean diversible = (0 == sum % K);
                if (diversible) {
                    ans += zerosLo;
                }

                while (++hi < A.length && A[hi] == 0) {
                    if (diversible) ans += zerosLo;
                }

                if (hi < A.length) sum += A[hi];
            }

            lo++;
            zerosLo = 1;
        }

        return ans;
    }
}
