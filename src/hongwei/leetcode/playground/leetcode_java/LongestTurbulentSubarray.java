package hongwei.leetcode.playground.leetcode_java;

import hongwei.leetcode.playground.common.Log;
import hongwei.leetcode.playground.IQuestion;

/**
 * Package: com.jd.aibdp.algorithm
 * User: baihongwei1
 * Email: baihongwei1@jd.com
 * Date: 2019/2/11
 * Time: 11:03
 * Description:
 */
public class LongestTurbulentSubarray implements IQuestion {
    @Override
    public void run() {
        int[][] input = new int[][]{/*{9, 4, 2, 10, 7, 8, 8, 1, 9}, {0, 1, 1, 0, 1, 0, 1, 1, 0, 0}, */{0, 8, 45, 88, 48, 68, 28, 55, 17, 24}};
        int[] ref = new int[]{/*5, 5,*/ 8};

        for (int i = 0; i < input.length; i++) {
            int result = maxTurbulenceSize(input[i]);
            if (result == ref[i]) {
                Log.i("aaaa", "case[" + i + "] passed");
            } else {
                Log.e("aaaa", "!!!!![WRONG]case[" + i + "] failed, result: " + result);
            }
        }
    }

    /*
    A subarray A[i], A[i+1], ..., A[j] of A is said to be turbulent if and only if:

For i <= k < j, A[k] > A[k+1] when k is odd, and A[k] < A[k+1] when k is even;
OR, for i <= k < j, A[k] > A[k+1] when k is even, and A[k] < A[k+1] when k is odd.
That is, the subarray is turbulent if the comparison sign flips between each adjacent pair of elements in the subarray.

Return the length of a maximum size turbulent subarray of A.
     */
    public int maxTurbulenceSize(int[] A) {
        int ans = 1;
        int cur = 0;
        int sign = 0;
        for (int i = 1; i < A.length; i++) {
            if (sign == 1) {
                if (A[i] > A[i - 1]) {
                    cur++;
                    sign = -1;
                } else {
                    ans = Math.max(ans, cur);
                    cur = 0;
                    sign = 0;
                }//0,8,45,88,48,68,28,55,17,24
            } else if (sign == -1) {
                if (A[i] < A[i - 1]) {
                    cur++;
                    sign = 1;
                } else {
                    ans = Math.max(ans, cur);
                    cur = 0;
                    sign = 0;
                }
            }

            if (0 == sign) {
                if (A[i] > A[i - 1]) {
                    cur = 2;
                    sign = -1;
                } else if (A[i] < A[i - 1]) {
                    cur = 2;
                    sign = 1;
                }
            }
        }
        ans = Math.max(ans, cur);
        return ans;
    }
}
