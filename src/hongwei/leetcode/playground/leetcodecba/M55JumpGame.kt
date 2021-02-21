package hongwei.leetcode.playground.leetcodecba

class M55JumpGame {
    fun test() {
//        val input = intArrayOf(2, 3, 1, 1, 4)
//        val input = intArrayOf(3, 2, 1, 0, 4)
//        val input = intArrayOf(2, 5, 0, 0)
        val input = intArrayOf(3, 0, 8, 2, 0, 0, 1)
        val result = canJump(input)

        println("result is $result")
    }

    fun canJump(nums: IntArray): Boolean = skip(nums)

    fun skip(nums: IntArray): Boolean {
        var p0 = 0
        while (p0 < nums.size) {
            if (nums[p0] == 0 && p0 < nums.size - 1) {
                return false
            }

            val maxReachByP0 = nums[p0] + p0
            if (maxReachByP0 >= nums.size - 1) {
                return true
            }

            var p0updated = false
            for (p1 in p0 + 1..maxReachByP0) {
                if (nums[p1] + p1 > maxReachByP0) {
                    p0 = p1
                    p0updated = true
                    break
                }
            }

            if (!p0updated) {
                p0++
            }
        }
        return false
    }

    fun dp(nums: IntArray, cur: Int): Boolean {
        if (cur == nums.size - 1) {
            return true
        }

        val maxStep = nums[cur]
        if (maxStep == 0) {
            return false
        }

        var i = cur + 1
        while (i <= cur + maxStep && i < nums.size) {
            if (dp(nums, i)) {
                return true
            }
            i++
        }
        return false
    }
}

/*
55. Jump Game
Medium

5741

401

Add to List

Share
Given an array of non-negative integers nums, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.



Example 1:

Input: nums = [2,3,1,1,4]
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
Example 2:

Input: nums = [3,2,1,0,4]
Output: false
Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it impossible to reach the last index.


Constraints:

1 <= nums.length <= 3 * 104
0 <= nums[i] <= 105
Accepted
586,803
Submissions
1,670,524
 */