package hongwei.leetcode.playground.leetcode_java;

import hongwei.leetcode.playground.common.LogJava;
import hongwei.leetcode.playground.IQuestion;

/**
 * Package: com.jd.aibdp.algorithm
 * User: baihongwei1
 * Email: baihongwei1@jd.com
 * Date: 2019/1/31
 * Time: 12:10
 * Description:
 */
//Container With Most Water
public class ContainerWithMostWater implements IQuestion {
    @Override
    public void run() {
        int[][] input = new int[][]{{1, 8, 6, 2, 5, 4, 8, 3, 7}};
        int[] ref = new int[]{49};
        for (int i = 0; i < input.length; i++) {
            int result = maxArea(input[i]);
            if (ref[i] == result) {
                LogJava.i("aaaa", "case[" + i + "] passed");
            } else {
                LogJava.e("aaaa", "!!!!![WRONG]case[" + i + "] failed.");
            }
        }
    }

    public int maxArea(int[] height) {
        int max = 0;
        int left = 0;
        int right = height.length - 1;
        while (left < right) {
            int capacity = (right - left) * Math.min(height[left], height[right]);
            if (capacity > max) {
                max = capacity;
            }

            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return max;
    }

    public int maxArea2(int[] height) {
//        int loop = 0;
        int maxAll = 0;
        int maxHeightTilNow = height[0];
        for (int i = 1; i < height.length; i++) {
            for (int j = 0; j < i; j++) {
                if (height[j] == -1) {
                    continue;
                }
//                loop++;
                int width = i - j;
                int capacity = width * Math.min(height[i], height[j]);
                if (capacity > maxAll) {
                    maxAll = capacity;
                }
            }

            if (height[i] > maxHeightTilNow) {
                maxHeightTilNow = height[i];
            } else {
                height[i] = -1;
            }
        }
//        Log.i("aaaa", "loop: " + loop);
        return maxAll;
    }

    /*
    Initially we consider the area constituting the exterior most lines.
    Now, to maximize the area, we need to consider the area between the lines of larger lengths.
    If we try to move the pointer at the longer line inwards, we won't gain any increase in area,
    since it is limited by the shorter line. But moving the shorter line's pointer could turn out to be beneficial,
    as per the same argument, despite the reduction in the width.
    This is done since a relatively longer line obtained by moving the shorter line's pointer might overcome the reduction in area caused by the width reduction.
     */
    /*
    Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai).
    n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines,
    which together with x-axis forms a container, such that the container contains the most water.

Note: You may not slant the container and n is at least 2.





The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7].
In this case, the max area of water (blue section) the container can contain is 49.



Example:

Input: [1,8,6,2,5,4,8,3,7]
Output: 49
     */
}
