package hongwei.leetcode.playground.leetcode_java;

import hongwei.leetcode.playground.common.LogJava;
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
public class Q3Sum implements IQuestion {
    @Override
    public void run() {
        int[][] input = new int[][]{{-1, 0, 1, 2, -1, -4}};
        List<List<Integer>>[] ref = new List[]{};
        for (int i = 0; i < input.length; i++) {
            List<List<Integer>> result = threeSum(input[i]);

            LogJava.i("aaaa", "r: " + result.toString());
//            if (ref[i].equals(result)) {
//                Log.i("aaaa", "case[" + i + "] passed");
//            } else {
//                Log.e("aaaa", "!!!!![WRONG]case[" + i + "] failed.");
//            }
        }
    }

    public List<List<Integer>> threeSum(int[] nums) {
        if (nums.length < 3) {
            return new ArrayList<>();
        }
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int cur = nums[i];
            if (hashMap.containsKey(cur)) {
                int val = hashMap.get(cur);
                hashMap.put(cur, val + 1);
            } else {
                hashMap.put(cur, 1);
            }
        }

        Set<List<Integer>> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            int base = nums[i];
            HashMap<Integer, Integer> hashMap2 = new HashMap(hashMap);
            int count = hashMap2.get(base);
            if (1 == count) {
                hashMap2.remove(base);
            } else {
                int val = hashMap2.get(base);
                hashMap2.put(base, val - 1);
            }
            Set<List<Integer>> list2 = twoSum(hashMap2, -base);

            for (List<Integer> set2 : list2) {
                set2.add(base);
                sort(set2);
                set.add(set2);
            }
        }

        List<List<Integer>> list = new ArrayList<>();
        list.addAll(set);
        return list;
    }

    private Set<List<Integer>> twoSum(HashMap<Integer, Integer> hashMap, int sum) {
        Set<List<Integer>> list = new HashSet<>();

        Set<Map.Entry<Integer, Integer>> entrySet = hashMap.entrySet();
        for (Map.Entry<Integer, Integer> entry : entrySet) {
            int base = entry.getKey();
            HashMap<Integer, Integer> hashMap2 = new HashMap(hashMap);
            int count = hashMap2.get(base);
            if (1 == count) {
                hashMap2.remove(base);
            } else {
                int val = hashMap2.get(base);
                hashMap2.put(base, val - 1);
            }

            if (hashMap2.containsKey(sum - base)) {
                List<Integer> list2 = new ArrayList<>();
                list2.add(sum - base);
                list2.add(base);
                list.add(list2);
            }
        }

        return list;
    }

    private void sort(List<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < i; j++) {
                if (list.get(i) < list.get(j)) {
                    int swp = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, swp);
                }
            }
        }

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
