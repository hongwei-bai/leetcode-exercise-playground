package hongwei.leetcode.playground.other.na2;

import hongwei.leetcode.playground.common.Log;

import java.util.ArrayList;

public class QuestionFramework {
    public static void testQ1() {
        Question1 question1 = new Question1();
        ArrayList<String> testList = new ArrayList<>();
        testList.add("abccba");// 0
        testList.add("abdcba");// 1
        testList.add("abdcea");// 2
        testList.add("qwertytrewq");// 0
        testList.add("qweaaytrewq");// 2
        testList.add("qwertytrkwq");// 1
        for (String s : testList) {
            Log.d(question1.canBePalindromeByChangingOneCharacter(s));
        }
    }

    public static void testQ2() {
        Question2 question2 = new Question2();
        String input = "2\n" + "abc\n" + "bca\n";
        Log.d("result2: " + question2.calculateMinimumMove(input));
    }

    public static void main(String[] args) {
        // QuestionFramework.testQ1();
        QuestionFramework.testQ2();
        // Log.d("test2a: " + new Question2().equalCompareLoop("abc", "cab",
        // 1));

        // Log.d("test2: " + new Question2().calcMove("abcd", "dabc"));
    }
}
