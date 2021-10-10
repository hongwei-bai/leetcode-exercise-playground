package hongwei.leetcode.playground.leetcodecba

import hongwei.leetcode.playground.common.print

class M1466ReorderRoutes2MakeAllPathsLead2theCityZero {
    fun test() {
        val testData = listOf(
            arrayOf(
                6,
                arrayOf(intArrayOf(0, 1), intArrayOf(1, 3), intArrayOf(2, 3), intArrayOf(4, 0), intArrayOf(4, 5)),
                3
            ),
            arrayOf(
                5,
                arrayOf(intArrayOf(1, 0), intArrayOf(1, 2), intArrayOf(3, 2), intArrayOf(3, 4)),
                2
            ),
            arrayOf(
                5,
                arrayOf(intArrayOf(4, 3), intArrayOf(2, 3), intArrayOf(1, 2), intArrayOf(1, 0)),
                2
            )
        )
        val onlyTestIndex = 2
        testData.forEachIndexed { i, data ->
            if (!(onlyTestIndex >= 0 && onlyTestIndex != i)) {
                val input1 = data[0] as Int
                val input2 = data[1] as Array<IntArray>
                val expectedOutput = data[2] as Int
                val output = minReorder(input1, input2)
                if (expectedOutput == output) {
                    println("test[$i] passed.")
                } else {
                    println("test[$i] FAILED!")
                    println(output)
                }
            }
        }
    }

    fun minReorder(n: Int, connections: Array<IntArray>): Int {
        val reachableCities = hashSetOf<Int>().apply { add(0) }

        var count = 0

        val toProcessCityList = mutableListOf<IntArray>().apply {
            addAll(connections)
        }
        val toProcessCityListNextRound: MutableList<IntArray> = mutableListOf()
        while (toProcessCityList.isNotEmpty()) {
            toProcessCityListNextRound.clear()
            for (i in toProcessCityList.indices) {
                val departureCity = toProcessCityList[i].first()
                val destinationCity = toProcessCityList[i].last()
                if (reachableCities.contains(destinationCity)) {
                    reachableCities.add(departureCity)
                } else if (reachableCities.contains(departureCity)) {
                    count++
                    reachableCities.add(destinationCity)
                } else {
                    toProcessCityListNextRound.add(toProcessCityList[i])
                }
            }
            toProcessCityList.clear()
            toProcessCityList.addAll(toProcessCityListNextRound)
        }
        return count
    }
}

/*
https://leetcode.com/problems/reorder-routes-to-make-all-paths-lead-to-the-city-zero/
 */