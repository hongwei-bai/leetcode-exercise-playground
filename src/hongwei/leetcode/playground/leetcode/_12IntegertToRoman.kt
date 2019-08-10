package hongwei.leetcode.playground.leetcode

import hongwei.leetcode.playground.common.Log
import java.lang.StringBuilder

class _12IntegertToRoman {
    fun test() {
        Log.i("aaaa", "1 -> ${intToRoman(1)}")
        Log.i("aaaa", "5 -> ${intToRoman(5)}")
        Log.i("aaaa", "10 -> ${intToRoman(10)}")
        Log.i("aaaa", "50 -> ${intToRoman(50)}")
        Log.i("aaaa", "100 -> ${intToRoman(100)}")
        Log.i("aaaa", "500 -> ${intToRoman(500)}")
        Log.i("aaaa", "1000 -> ${intToRoman(1000)}")

        Log.i("aaaa", "3 -> ${intToRoman(3)}")
        Log.i("aaaa", "4 -> ${intToRoman(4)}")
        Log.i("aaaa", "9 -> ${intToRoman(9)}")
        Log.i("aaaa", "58 -> ${intToRoman(58)}")
        Log.i("aaaa", "1994 -> ${intToRoman(1994)}")
    }

    /*
        Symbol       Value
        I             1
        V             5
        X             10
        L             50
        C             100
        D             500
        M             1000
    */
    companion object {
        val Oct = arrayOf(1000, 100, 10, 1)

        val Symbols = arrayOf(arrayOf("^", "M"),
                arrayOf("D", "C"),
                arrayOf("L", "X"),
                arrayOf("V", "I"))

        val Values = arrayOf(arrayOf(5000, 1000),
                arrayOf(500, 100),
                arrayOf(50, 10),
                arrayOf(5, 1))
    }

    fun intToRoman(num: Int): String {
        var number = num
        val stringBuilder = StringBuilder()
        Oct.forEachIndexed { i, octUnit ->
            if (number >= octUnit) {
                val top = number / octUnit

                when (top) {
                    9 -> stringBuilder.append(Symbols[i][1] + Symbols[i - 1][1])
                    in 6..8 -> {
                        stringBuilder.append(Symbols[i][0])
                        for (j in 6..top) {
                            stringBuilder.append(Symbols[i][1])
                        }
                    }
                    5 -> stringBuilder.append(Symbols[i][0])
                    4 -> stringBuilder.append(Symbols[i][1] + Symbols[i][0])
                    in 1..3 -> for (j in 1..top) {
                        stringBuilder.append(Symbols[i][1])
                    }
                }
                number %= octUnit
            }
        }
        return stringBuilder.toString()
    }
}