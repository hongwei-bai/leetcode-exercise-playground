package hongwei.leetcode.playground.leetcodecba

import hongwei.leetcode.playground.common.print

class M57InsertIntervals {
    fun test() {
        val testData = listOf(
            arrayOf(
                arrayOf(intArrayOf(1, 3), intArrayOf(6, 9)),
                intArrayOf(2, 5),
                arrayOf(intArrayOf(1, 5), intArrayOf(6, 9))
            ),
            arrayOf(
                arrayOf(intArrayOf(1, 2), intArrayOf(3, 5), intArrayOf(6, 7), intArrayOf(8, 10), intArrayOf(12, 16)),
                intArrayOf(4, 8),
                arrayOf(intArrayOf(1, 2), intArrayOf(3, 10), intArrayOf(12, 16))
            ), arrayOf(
                emptyArray<IntArray>(),
                intArrayOf(5, 7),
                arrayOf(intArrayOf(5, 7))
            ), arrayOf(
                arrayOf(intArrayOf(1, 5)),
                intArrayOf(2, 3),
                arrayOf(intArrayOf(1, 5))
            ), arrayOf(
                arrayOf(intArrayOf(1, 5)),
                intArrayOf(2, 7),
                arrayOf(intArrayOf(1, 7))
            ), arrayOf(
                arrayOf(intArrayOf(3, 5)),
                intArrayOf(1, 2),
                arrayOf(intArrayOf(1, 2), intArrayOf(3, 5))
            ), arrayOf( // [6]
                arrayOf(intArrayOf(1, 2)),
                intArrayOf(3, 5),
                arrayOf(intArrayOf(1, 2), intArrayOf(3, 5))
            ), arrayOf( // [7]
                arrayOf(intArrayOf(1, 5), intArrayOf(8, 10)),
                intArrayOf(7, 8),
                arrayOf(intArrayOf(1, 5), intArrayOf(7, 10))
            ), arrayOf( // [8]
                arrayOf(intArrayOf(1, 5), intArrayOf(8, 10)),
                intArrayOf(7, 7),
                arrayOf(intArrayOf(1, 5), intArrayOf(7, 7), intArrayOf(8, 10))
            )
        )

        val onlyTestIndex = -1
        testData.forEachIndexed { i, data ->
            if (!(onlyTestIndex >= 0 && onlyTestIndex != i)) {
                val input1 = data[0]
                val input2 = data[1]
                val expectedOutput = data[2] as Array<IntArray>
                val output = insert(input1 as Array<IntArray>, input2 as IntArray)
                var pass = expectedOutput.size == output.size
                if (pass) {
                    expectedOutput.forEachIndexed { index, ints ->
                        if (!ints.contentEquals(output[index])) {
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

    companion object {
        private const val UNSET = -1
        private const val DONE = -2
    }

    fun insert(intervals: Array<IntArray>, newInterval: IntArray): Array<IntArray> {
        if (intervals.isEmpty()) {
            return arrayOf(newInterval)
        }
        val list = mutableListOf<IntArray>()
        val lo = conservativeBinarySearch(intervals, 0, newInterval[0])

        for (i in 0 until lo) {
            list.add(intervals[i])
        }

        var newBegin = UNSET
        var i = lo
        while (i < intervals.size) {
            val begin = intervals[i][0]
            val end = intervals[i][1]
            if (newBegin == UNSET) {
                if (newInterval[0] > end) {
                    list.add(intervals[i])
                    i++
                    continue
                } else {
                    newBegin = newInterval[0].coerceAtMost(begin)
                }
            }

            if (newInterval[1] < begin) {
                list.add(intArrayOf(newBegin, newInterval[1]))
                newBegin = DONE
                break
            }

            if (newInterval[1] <= end) {
                list.add(intArrayOf(newBegin, end))
                newBegin = DONE
                i++
                break
            }

            i++
        }

        if (newBegin == UNSET) {
            list.add(newInterval)
        } else if (newBegin >= 0) {
            list.add(intArrayOf(newBegin, newInterval[1]))
        }

        for (j in i until intervals.size) {
            list.add(intervals[j])
        }

        return list.toTypedArray()
    }

    private fun conservativeBinarySearch(
        intervals: Array<IntArray>,
        index: Int,
        target: Int,
        from: Int = 0,
        to: Int = intervals.size - 1
    ): Int {
        var lo = from
        var hi = to
        var mi = (lo + hi) / 2
        while (lo <= hi) {
            if (target == intervals[mi][index]) {
                return mi
            } else if (target > intervals[mi][index]) {
                lo = mi + 1
            } else {
                hi = mi - 1
            }
            mi = (lo + hi) / 2
        }
        return if (lo > 0) {
            lo - 1
        } else 0
    }
}

/*
https://leetcode.com/problems/insert-interval/
57. Insert Interval
Medium

2602

234

Add to List

Share
Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

You may assume that the intervals were initially sorted according to their start times.



Example 1:

Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
Output: [[1,5],[6,9]]
Example 2:

Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
Output: [[1,2],[3,10],[12,16]]
Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
Example 3:

Input: intervals = [], newInterval = [5,7]
Output: [[5,7]]
Example 4:

Input: intervals = [[1,5]], newInterval = [2,3]
Output: [[1,5]]
Example 5:

Input: intervals = [[1,5]], newInterval = [2,7]
Output: [[1,7]]


Constraints:

0 <= intervals.length <= 104
intervals[i].length == 2
0 <= intervals[i][0] <= intervals[i][1] <= 105
intervals is sorted by intervals[i][0] in ascending order.
newInterval.length == 2
0 <= newInterval[0] <= newInterval[1] <= 105
Accepted
329,143
Submissions
938,976
 */