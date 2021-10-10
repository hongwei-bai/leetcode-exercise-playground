package hongwei.leetcode.playground.leetcode_java;

import hongwei.leetcode.playground.common.LogJava;
import hongwei.leetcode.playground.IQuestion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Package: com.jd.aibdp.algorithm
 * User: baihongwei1
 * Email: baihongwei1@jd.com
 * Date: 2019/2/1
 * Time: 14:29
 * Description:
 */
public class Q4Sum implements IQuestion {
    @Override
    public void run() {
//        int[][] input = new int[][]{{1, 0, -1, 0, -2, 2}};
//        int[] targets = new int[]{0};

//        int[][] input = new int[][]{{-7, -5, 0, 7, 1, 1, -10, -2, 7, 7, -2, -6, 0, -10, -5, 7, -8, 5}};
//        int[] targets = new int[]{28};

//        int[][] input = new int[][]{{5, 5, 3, 5, 1, -5, 1, -2}};
//        int[] targets = new int[]{4};

//        int[][] input = new int[][]{{0, 1, 5, 0, 1, 5, 5, -4}};
//        int[] targets = new int[]{11};

//        int[][] input = new int[][]{{-4, -3, -2, -1, 0, 0, 1, 2, 3, 4}};
//        int[] targets = new int[]{0};

//        int[][] input = new int[][]{{-5, 5, 4, -3, 0, 0, 4, -2}};
//        int[] targets = new int[]{4};

//        int[][] input = new int[][]{{5, 0, 2, -5, -5, 4, -5, 1, -1}};
//        int[] targets = new int[]{-5};

        int[][] input = new int[][]{{0,0,0,0}};
        int[] targets = new int[]{1};

        List<List<Integer>>[] ref = new List[]{Arrays.asList(
                Arrays.asList(-1, 0, 0, 1),
                Arrays.asList(-2, -1, 1, 2),
                Arrays.asList(-2, 0, 0, 2)
        )};

        for (int i = 0; i < input.length; i++) {
            List<List<Integer>> result = fourSum(input[i], targets[i]);
            if (result != null && ref[i].equals(result)) {
                LogJava.i("aaaa", "case[" + i + "] passed");
            } else {
                LogJava.e("aaaa", "!!!!![WRONG]case[" + i + "] failed, result: " + result);
            }
        }
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<>();
        if (nums.length < 4) {
            return list;
        }

        Arrays.sort(nums);

        int lo = 0;

        int lo2, hi2;

        while (lo <= nums.length - 3) {
            int hi = nums.length - 1;
            if (lo + 3 < nums.length && nums[lo] + nums[lo + 1] + nums[lo + 2] + nums[lo + 3] > target) {
                break;
            }
            if (nums[lo] + nums[nums.length - 1] + nums[nums.length - 2] + nums[nums.length - 3] < target) {
                lo++;
                continue;
            }

            while (hi >= lo + 3) {
//                Log.i("aaaa", "[" + nums[lo] + ", " + nums[hi] + "]");

                if (hi - 3 >= 0 && nums[hi] + nums[hi - 1] + nums[hi - 2] + nums[hi - 3] < target) {
                    break;
                }
                if (lo + 2 < nums.length && nums[hi] + nums[lo] + nums[lo + 1] + nums[lo + 2] > target) {
                    hi--;
                    continue;
                }

                int targetInside = target - nums[lo] - nums[hi];

                lo2 = lo + 1;
                hi2 = hi - 1;
                while (lo2 < hi2) {
//                    if (lo == 0 && hi == 7) {
//                        Log.i("aaaa", "lo2: " + lo2 + ", hi2: " + hi2 + ", range[" + nums[lo2] + ", " + nums[hi2] + "]");
//                    }

                    if (nums[lo2] + nums[hi2] == targetInside) {
                        list.add(Arrays.asList(nums[lo], nums[lo2], nums[hi2], nums[hi]));
                        lo2++;
                        hi2--;
                        while (lo2 < hi2 && lo2 > lo + 1 && nums[lo2] == nums[lo2 - 1]) {
                            lo2++;
                        }
                        while (lo2 < hi2 && hi2 < hi - 1 && nums[hi2] == nums[hi2 + 1]) {
                            hi2--;
                        }
                    } else if (nums[lo2] + nums[hi2] > targetInside) {
                        hi2--;
                    } else {
                        lo2++;
                    }
                }
                hi--;
                while (hi >= lo + 3 && hi < nums.length - 1 && hi > lo && nums[hi] == nums[hi + 1]) {
                    hi--;
                }
            }
            lo++;
            while (lo > 0 && lo < nums.length && nums[lo] == nums[lo - 1]) {
                lo++;
            }
        }

        return list;
    }


    /*
    [-4,-3,-2,-1,0,0,1,2,3,4]
0

    [[-4,-3,3,4],[-4,-2,2,4],[-4,-1,1,4],[-4,-1,2,3],[-4,0,0,4],[-4,0,1,3],[-4,1,1,2],[-3,-2,1,4],[-3,-2,2,3],[-3,-1,0,4],[-3,-1,1,3],[-3,0,0,3],[-3,0,1,2],[-2,-1,-1,4],[-2,-1,0,3],[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]

    [[-4,-3,3,4],[-4,-2,2,4],[-4,-1,1,4],[-4,-1,2,3],[-4,0,0,4],[-4,0,1,3]           ,[-3,-2,1,4],[-3,-2,2,3],[-3,-1,0,4],[-3,-1,1,3],[-3,0,0,3],[-3,0,1,2],             [-2,-1,0,3],[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
     */
}
