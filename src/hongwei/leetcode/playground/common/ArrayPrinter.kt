package hongwei.leetcode.playground.common

import java.lang.StringBuilder

fun Array<IntArray>.print() {
    for (intArray in this) {
        val stringBuilder = StringBuilder()
        intArray.forEach { stringBuilder.append(it).append(" ") }
        println(stringBuilder)
    }
}

fun IntArray.print() {
    val stringBuilder = StringBuilder()
    forEach { stringBuilder.append(it).append(" ") }
    println(stringBuilder)
}