package hongwei.leetcode.playground.picker

import kotlin.random.Random

class ProblemPicker {
    fun test(poolSize: Int = 2000) {
        val randomValues = List(10) { Random.nextInt(1, poolSize) }
        // prints new sequence every time
        println(randomValues.last())
    }
}