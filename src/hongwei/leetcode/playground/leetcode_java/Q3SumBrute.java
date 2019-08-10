package hongwei.leetcode.playground.leetcode_java;

import hongwei.leetcode.playground.common.Log;
import hongwei.leetcode.playground.IQuestion;

import java.util.*;

/**
 * Package: com.jd.aibdp.algorithm
 * User: baihongwei1
 * Email: baihongwei1@jd.com
 * Date: 2019/1/31
 * Time: 14:08
 * Description:
 */
public class Q3SumBrute implements IQuestion {
    @Override
    public void run() {
//        int[][] input = new int[][]{{-1, 0, 1, 2, -1, -4}};
//        List<List<Integer>>[] ref = new List[]{};

        int[][] input = new int[][]{{0, 0, 0, 0}};
        List<List<Integer>>[] ref = new List[]{};

        for (int i = 0; i < input.length; i++) {
            List<List<Integer>> result = threeSum(input[i]);

            Log.i("aaaa", "r: " + result.toString());
//            if (ref[i].equals(result)) {
//                Log.i("aaaa", "case[" + i + "] passed");
//            } else {
//                Log.e("aaaa", "!!!!![WRONG]case[" + i + "] failed.");
//            }
        }
    }

    public List<List<Integer>> threeSum(int[] num) {
        Arrays.sort(num);

        List<List<Integer>> list = new ArrayList<>();

        for (int i = 0; i < num.length - 2; i++) {
            if (i > 0 && num[i] == num[i - 1]) {
                continue;
            }

            int lo = i + 1;
            int hi = num.length - 1;
            int sum = -num[i];

            while (lo < hi) {
                if (num[lo] + num[hi] < sum) {
                    lo++;
                } else if (num[lo] + num[hi] > sum) {
                    hi--;
                } else {
                    list.add(Arrays.asList(num[lo], num[hi], -sum));
                    lo++;
                    while (num[lo] == num[lo - 1] && lo < hi) {
                        lo++;
                    }
                }
            }
        }

        return list;
    }

    public List<List<Integer>> threeSumOp(int[] num) {
        /*
        The idea is to sort an input array and then run through all indices of a possible first element of a triplet.
        For each possible first element we make a standard bi-directional 2Sum sweep of the remaining part of the array.
        Also we want to skip equal elements to avoid duplicates in the answer without making a set or smth like that.
         */
        Arrays.sort(num);
        List<List<Integer>> res = new LinkedList<>();
        for (int i = 0; i < num.length - 2; i++) {
            if (i == 0 || (i > 0 && num[i] != num[i - 1])) {
                int lo = i + 1, hi = num.length - 1, sum = 0 - num[i];
                while (lo < hi) {
                    if (num[lo] + num[hi] == sum) {
                        res.add(Arrays.asList(num[i], num[lo], num[hi]));
                        while (lo < hi && num[lo] == num[lo + 1]) lo++;
                        while (lo < hi && num[hi] == num[hi - 1]) hi--;
                        lo++;
                        hi--;
                    } else if (num[lo] + num[hi] < sum) lo++;
                    else hi--;
                }
            }
        }
        return res;
    }

    public List<List<Integer>> threeSumBrute(int[] nums) {
        Set<List<Integer>> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int sum2 = nums[i] + nums[j];
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[k] == -sum2) {
                        List<Integer> list2 = new ArrayList<>();
                        list2.add(nums[i]);
                        list2.add(nums[j]);
                        list2.add(nums[k]);
                        Collections.sort(list2);
                        set.add(list2);
                    }
                }
            }
        }
        List<List<Integer>> list = new ArrayList<>();
        list.addAll(set);
        return list;
    }


    /*
    Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

Note:

The solution set must not contain duplicate triplets.

Example:

Given array nums = [-1, 0, 1, 2, -1, -4],

A solution set is:
[
  [-1, 0, 1],
  [-1, -1, 2]
]
     */
}
