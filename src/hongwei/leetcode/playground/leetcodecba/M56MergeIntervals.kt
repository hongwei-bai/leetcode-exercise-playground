package hongwei.leetcode.playground.leetcodecba

import hongwei.leetcode.playground.common.print

class M56MergeIntervals {
    fun test() {
//        val input = arrayOf(intArrayOf(1, 3), intArrayOf(2, 6), intArrayOf(8, 10), intArrayOf(15, 18))
        val input = arrayOf(intArrayOf(3, 4), intArrayOf(1, 1))
//        val output = merge(input)
//        output.print()
    }

    fun mergeNotWorkForContinuousCloseInterval(intervals: Array<IntArray>): Array<IntArray> {
        val result = mutableListOf<IntArray>()
        val array = BooleanArray(10000) { false }

        intervals.forEach { array.fill(true, it[0], it[1]) }

        var startSaved = -1
        var flag = false
        array.forEachIndexed { i, value ->
            if (flag != value) {
                if (value) {
                    startSaved = i
                } else {
                    result.add(intArrayOf(startSaved, i))
                }
                flag = value
            }
        }

        return result.toTypedArray()
    }

    fun mergeOnlyWorkForSequenced(intervals: Array<IntArray>): Array<IntArray> {
        val result = mutableListOf<IntArray>()
        var cur = intervals.first()
        for (interval in intervals) {
            if (cur[1] >= interval[1]) {
                // No-Op
            } else if (cur[1] >= interval[0]) {
                cur[1] = interval[1]
            } else {
                result.add(cur)
                cur = interval
            }
        }
        result.add(cur)
        return result.toTypedArray()
    }
}

/*
56. Merge Intervals
Medium

6507

358

Add to List

Share
Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.



Example 1:

Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
Example 2:

Input: intervals = [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.


Constraints:

1 <= intervals.length <= 104
intervals[i].length == 2
0 <= starti <= endi <= 104
Accepted
810,058
Submissions
1,977,511
 */