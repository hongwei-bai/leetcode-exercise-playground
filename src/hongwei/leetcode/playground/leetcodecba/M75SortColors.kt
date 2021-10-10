package hongwei.leetcode.playground.leetcodecba

import hongwei.leetcode.playground.common.print

class M75SortColors {
    fun test() {
        val testData = listOf(
            arrayOf(
                intArrayOf(2, 0, 2, 1, 1, 0),
                intArrayOf(0, 0, 1, 1, 2, 2)
            ),
            arrayOf(
                intArrayOf(2, 0, 1),
                intArrayOf(0, 1, 2)
            ),
            arrayOf(
                intArrayOf(0),
                intArrayOf(0)
            ),
            arrayOf(
                intArrayOf(1),
                intArrayOf(1)
            )
        )

        val onlyTestIndex = -1
        testData.forEachIndexed { i, data ->
            if (!(onlyTestIndex >= 0 && onlyTestIndex != i)) {
                val input1 = data[0] as IntArray
                var output = IntArray(input1.size).apply {
                    forEachIndexed { index, _ -> set(index, input1[index]) }
                }
                val expectedOutput = data[1]
                sortColors(output)
                var pass = expectedOutput.size == output.size
                if (pass) {
                    expectedOutput.forEachIndexed { index, ints ->
                        if (ints != output[index]) {
                            pass = false
                        }
                    }
                }
                if (pass) {
                    println("test[$i] passed.")
                } else {
                    println("test[$i] FAILED!")
                    output.print()
                }
            }
        }
    }

    fun sortColors(nums: IntArray): Unit {
        var red = 0
        var white = 0
        nums.forEach {
            when (it) {
                0 -> red++
                1 -> white++
            }
        }
        nums.fill(0, 0, red)
        nums.fill(1, red, red + white)
        nums.fill(2, red + white, nums.size)
    }
}

/*
https://leetcode.com/problems/sort-colors/
75. Sort Colors
Medium

4896

278

Add to List

Share
Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white, and blue.

We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.



Example 1:

Input: nums = [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]
Example 2:

Input: nums = [2,0,1]
Output: [0,1,2]
Example 3:

Input: nums = [0]
Output: [0]
Example 4:

Input: nums = [1]
Output: [1]


Constraints:

n == nums.length
1 <= n <= 300
nums[i] is 0, 1, or 2.


Follow up:

Could you solve this problem without using the library's sort function?
Could you come up with a one-pass algorithm using only O(1) constant space?
Accepted
634.8K
Submissions
1.3M
 */