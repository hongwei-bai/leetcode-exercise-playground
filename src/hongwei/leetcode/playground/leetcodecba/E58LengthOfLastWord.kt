package hongwei.leetcode.playground.leetcodecba

class E58LengthOfLastWord {
    fun test() {
        val testData = arrayOf(
            "Hello World" to 5,
            " " to 0,
            "a " to 1
        )
        val onlyTestIndex = -1
        testData.forEachIndexed { i, pair ->
            if (!(onlyTestIndex >= 0 && onlyTestIndex != i)) {
                val input1 = pair.first as String
                val expectedOutput = pair.second as Int
                val output = lengthOfLastWord(input1)
                var pass = expectedOutput == output
                if (pass) {
                    println("test[$i] passed.")
                } else {
                    println("test[$i] FAILED!")
                    println("test[$i] output: $output")
                }
            }
        }
    }

    fun lengthOfLastWord(s: String): Int {
        val cs = s.toCharArray()
        var i = cs.size - 1
        var c = 0
        while (i >= 0) {
            while (i >= 0 && cs[i] == ' ') i--
            while (i >= 0 && cs[i] != ' ') {
                i--
                c++
            }
            break
        }
        return c
    }

    fun lengthOfLastWord2(s: String): Int {
        val a = s.trim().split(" ")
        if (a == null || a.isEmpty()) {
            return 0
        }

        return a.last().length
    }
}

/*
https://leetcode.com/problems/length-of-last-word/
58. Length of Last Word
Easy

971

3004

Add to List

Share
Given a string s consists of some words separated by spaces, return the length of the last word in the string. If the last word does not exist, return 0.

A word is a maximal substring consisting of non-space characters only.



Example 1:

Input: s = "Hello World"
Output: 5
Example 2:

Input: s = " "
Output: 0


Constraints:

1 <= s.length <= 104
s consists of only English letters and spaces ' '.
Accepted
475,316
Submissions
1,420,184
 */