package hongwei.leetcode.playground.leetcode

import hongwei.leetcode.playground.common.Log

class _13RomanToIntegert {
    fun test() {
        test(1, "I")
        test(5, "V")
        test(10, "X")
        test(50, "L")
        test(100, "C")
        test(500, "D")
        test(1000, "M")

        test(3, "III")
        test(4, "IV")
        test(9, "IX")
        test(58, "LVIII")
        test(1994, "MCMXCIV")

        test(1884, "MDCCCLXXXIV")
    }

    fun test(a: Int, b: String) {
        val num = romanToInt(b)
        val result = if (a == num) "[passed]" else "[FAILED!!]"
        if (a == num) {
            Log.i("aaaa", "$result: $b -> $num")
        } else {
            Log.e("aaaa", "$result: $b -> $num")
        }
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
        val valueMap = mapOf(
                "I" to 1,
                "V" to 5,
                "X" to 10,
                "L" to 50,
                "C" to 100,
                "D" to 500,
                "M" to 1000
        )

        val OneCollection = arrayOf("I", "X", "C")
        val OneCollectionForFive = arrayOf("V", "L", "D")
        val OneCollectionForTen = arrayOf("X", "C", "M")
    }

    fun romanToInt(s: String): Int {
        val s0 = s.toCharArray()

        var stack: String? = null

        var num = 0

        s0.forEachIndexed { index, c ->
            val symbol = c.toString()

            if (stack == null) {
                if (OneCollection.contains(symbol)) {
                    stack = symbol
                } else {
                    num += valueMap[symbol] ?: 0
                }
            } else {
                val popup = stack
                stack = null

                val indexOfOne = OneCollection.indexOf(popup)
                if (OneCollectionForFive[indexOfOne] == symbol) {
                    num += valueMap[symbol]!! - valueMap[popup]!!
                } else if (OneCollectionForTen[indexOfOne] == symbol) {
                    num += valueMap[symbol]!! - valueMap[popup]!!
                } else if (OneCollection.contains(symbol)) {
                    num += valueMap[popup] ?: 0
                    stack = symbol
                } else {
                    num += valueMap[popup] ?: 0
                    num += valueMap[symbol] ?: 0
                }
            }
        }

        stack?.let { num += valueMap[it]!! }
        return num
    }
}