package hongwei.leetcode.playground.leetcodecba

import hongwei.leetcode.playground.common.print
import kotlin.experimental.or

class M56MergeIntervals2 {
    fun test() {
//        val input = arrayOf(intArrayOf(1, 3), intArrayOf(2, 6), intArrayOf(8, 10), intArrayOf(15, 18))
//        val input = arrayOf(intArrayOf(5, 6), intArrayOf(3, 3), intArrayOf(4, 4), intArrayOf(1, 2))
//        val input = arrayOf(intArrayOf(1, 4), intArrayOf(4, 5))
        val input = arrayOf(intArrayOf(1, 4), intArrayOf(5, 6))
//        val input = arrayOf(intArrayOf(2, 3), intArrayOf(5, 5), intArrayOf(2, 2), intArrayOf(3, 4), intArrayOf(3, 4))
        val output = merge(input)
        output.print()

//        testFillArray()

        // test
//        var a = YES
//        a  = a or OPEN
//        println(a)
    }

//    companion object {
//        /*
//         open: 0x0001
//         close: 0x0010
//         point: ox0011
//         mid: ox0111
//         */
//        private val NO: Byte = 0
//        private const val OPEN: Byte = 1
//        private const val CLOSE: Byte = 2
//        private const val POINT: Byte = 3
//        private const val YES: Byte = 7
//    }

    companion object {
        /*
         point: 0x0001
         open: 0x0011
         close: 0x0101
         mid: ox0111
         */
        private val NO: Byte = 0
        private const val POINT: Byte = 1
        private const val OPEN: Byte = 3
        private const val CLOSE: Byte = 5
        private const val YES: Byte = 7
    }

    fun merge(intervals: Array<IntArray>): Array<IntArray> {
        val result = mutableListOf<IntArray>()
        val array = ByteArray(10000) { NO }

        intervals.forEach {
            val open = it[0]
            val close = it[1]
            println("================= round ==================")
            if (open == close) {
                array[open] = array[open] or POINT
                println("open==close: mark index[$open] to ${array[open]}")
            } else {
                println("($open ~ $close)")
                array[open] = array[open] or OPEN
                println("open!=close: mark index[$open] to ${array[open]}")
                array[close] = array[close] or CLOSE
                println("open!=close: mark index[$close] to ${array[close]}")
                if (open + 1 <= close) {
                    array.fill(YES, open + 1, close)
                    println("close > open + 1: mark {${open + 1} ~ ${close - 1}} as $YES")
                }
            }
            debug(array)
        }

        var savedStart = -1
        array.forEachIndexed { i, value ->
            if (value == OPEN) {
                savedStart = i
            } else if (value == CLOSE) {
                result.add(intArrayOf(savedStart, i))
                savedStart = -1
            } else if (value == POINT) {
                result.add(intArrayOf(i, i))
            }
        }
        return result.toTypedArray()
    }

    fun testFillArray() {
        val array = ByteArray(10) { 0 }
        array.fill(1, 1, 3)
        array.forEach {
            print("$it ")
        }
    }

    fun debug(a: ByteArray) {
        for (i in 0..20) {
            print("${a[i]} ")
        }
        println(" ")
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