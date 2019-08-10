package hongwei.leetcode.playground.leetcode_java;

import hongwei.leetcode.playground.IQuestion;
import hongwei.leetcode.playground.common.Log;

import java.util.Arrays;

/**
 * Package: com.jd.aibdp.algorithm
 * User: baihongwei1
 * Email: baihongwei1@jd.com
 * Date: 2019/1/31
 * Time: 16:50
 * Description:
 */
public class Q3SumClosest implements IQuestion {
    @Override
    public void run() {
//        int[][] input = new int[][]{{-1, 2, 1, -4}};
//        int[] targets = new int[]{1};
//        int[] ref = new int[]{2};

        int[][] input = new int[][]{{0, 2, 1, -3}};
        int[] targets = new int[]{1};
        int[] ref = new int[]{0};

        for (int i = 0; i < input.length; i++) {
            int result = threeSumClosest(input[i], targets[i]);
            if (ref[i] == result) {
                Log.i("aaaa", "case[" + i + "] passed");
            } else {
                Log.e("aaaa", "!!!!![WRONG]case[" + i + "] failed, result: " + result);
            }
        }
    }

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);

        int sign = 1;
        int minDist = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int lo = i + 1;
            int hi = nums.length - 1;
            int cur = nums[i];

            while (lo < hi) {
                int sum = cur + nums[lo] + nums[hi];
                int dist = Math.abs(sum - target);
                if (dist == 0) {
                    return target;
                }
                Log.i("aaaa", "dist: " + dist + ", sum: " + sum);
                if (dist < minDist) {
                    minDist = dist;
                    if (sum > target) {
                        sign = 1;
                    } else {
                        sign = -1;
                    }
                }

                if (sum > target) {
                    hi--;
                } else {
                    lo++;
                }
            }
        }

        return target + sign * minDist;
    }
}
