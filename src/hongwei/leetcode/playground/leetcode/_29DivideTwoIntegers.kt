package hongwei.leetcode.playground.leetcode

import hongwei.leetcode.playground.common.Log

class _29DivideTwoIntegers {
    fun test() {
        val r = divide(-2147483648, -1)
        Log.i("aaaa", "divide $r")
    }

    var array2 = mutableListOf<Int>()

    init {
        var number = 2
        while (number > 0 && number < Int.MAX_VALUE) {
            array2.add(number)
            number = number shl 1
        }
    }

    fun divide(dividend: Int, divisor: Int): Int {
        if (dividend == 0) return 0

        if (1 == divisor) {
            return when (dividend) {
                in Int.MIN_VALUE..Int.MAX_VALUE -> dividend
                else -> if (dividend > 0) Int.MAX_VALUE else Int.MIN_VALUE
            }
        } else if (-1 == divisor) {
            if(dividend > 0) {
                return when (dividend) {
                    in 0..Int.MAX_VALUE -> -dividend
                    else -> Int.MAX_VALUE
                }
            } else {
                return when (dividend) {
                    in -Int.MAX_VALUE..0 -> -dividend
                    else -> Int.MAX_VALUE
                }
            }
        } else if (array2.contains(divisor)) {
            val index = array2.indexOf(divisor) + 1
            return dividend shr index
        } else if (array2.contains(-divisor)) {
            val index = array2.indexOf(divisor) + 1
            return -(dividend shr index)
        } else {
            val sign = if ((dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0)) 1 else -1
            val dividend1 = Math.abs(dividend.toLong())
            val divisor1 = Math.abs(divisor.toLong())

            var number: Long = 0
            var count: Long = -1
            while (dividend1 >= number) {
                number += divisor1
                count++
            }
            val r = count * sign
            if (r > Int.MAX_VALUE) return Int.MAX_VALUE
            if (r < Int.MIN_VALUE) return Int.MIN_VALUE
            return r.toInt()
        }
    }
}