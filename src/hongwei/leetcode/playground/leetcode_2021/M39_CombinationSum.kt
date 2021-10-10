package hongwei.leetcode.playground.leetcode_2021

import hongwei.leetcode.playground.common.Log

class M39_CombinationSum {
    fun test() {
        val listOfCandidates = listOf(
            listOf(2, 3, 6, 7),
            listOf(2, 3, 5),
            listOf(2),
            listOf(1),
            listOf(1),
        )
        val listOfTarget = listOf(
            7,
            8,
            1,
            1,
            2,
        )
        val expectedResultList = listOf(
            setOf(listOf(2, 2, 3), listOf(7)),
            setOf(listOf(2, 2, 2, 2), listOf(2, 3, 3), listOf(3, 5)),
            emptySet(),
            setOf(listOf(1)),
            setOf(listOf(1, 1)),
        )
        listOfCandidates.forEachIndexed { i, list ->
            val actualResult = combinationSum(list.toIntArray(), listOfTarget[i])
            if (actualResult.toSet() == expectedResultList[i]) {
                Log.pass("Test[$i] passed.")
            } else {
                Log.fail("Test[$i] FAILED!\ninput1: $list\ninput2: ${listOfTarget[i]}\nexpected: ${expectedResultList[i]}\nactual: $actualResult")
            }
        }
    }

    fun combinationSum(candidates: IntArray, target: Int): List<List<Int>> {
        val list = foo(candidates.size - 1, candidates, target)
        return list.filter { it.sum() == target }
    }

    fun foo(n: Int, candidates: IntArray, target: Int): List<List<Int>> {
        val fPrevious = if (n > 0) {
            foo(n - 1, candidates, target)
        } else {
            emptyList()
        }
        val list = mutableListOf<List<Int>>()
        fPrevious.forEach { prevList ->
            val prevSum = prevList.sum()
            var i = 0
            while (true) {
                if (prevSum + candidates[n] * i <= target) {
                    val lvl2List = mutableListOf<Int>()
                    prevList.forEach {
                        lvl2List.add(it)
                    }
                    for (j in 0 until i) {
                        lvl2List.add(candidates[n])
                    }
                    list.add(lvl2List)
                } else {
                    break
                }
                i++
            }
        }
        var i = 1
        while (true) {
            if (candidates[n] * i <= target) {
                val lvl2List = mutableListOf<Int>()
                for (j in 0 until i) {
                    lvl2List.add(candidates[n])
                }
                list.add(lvl2List)
            } else {
                break
            }
            i++
        }
        return list
    }
}

/*
39. Combination Sum
Medium

7604

180

Add to List

Share
Given an array of distinct integers candidates and a target integer target, return a list of all unique combinations of candidates where the chosen numbers sum to target. You may return the combinations in any order.

The same number may be chosen from candidates an unlimited number of times. Two combinations are unique if the frequency of at least one of the chosen numbers is different.

It is guaranteed that the number of unique combinations that sum up to target is less than 150 combinations for the given input.



Example 1:

Input: candidates = [2,3,6,7], target = 7
Output: [[2,2,3],[7]]
Explanation:
2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
7 is a candidate, and 7 = 7.
These are the only two combinations.
Example 2:

Input: candidates = [2,3,5], target = 8
Output: [[2,2,2,2],[2,3,3],[3,5]]
Example 3:

Input: candidates = [2], target = 1
Output: []
Example 4:

Input: candidates = [1], target = 1
Output: [[1]]
Example 5:

Input: candidates = [1], target = 2
Output: [[1,1]]


Constraints:

1 <= candidates.length <= 30
1 <= candidates[i] <= 200
All elements of candidates are distinct.
1 <= target <= 500
Accepted
848,346
Submissions
1,363,625
 */