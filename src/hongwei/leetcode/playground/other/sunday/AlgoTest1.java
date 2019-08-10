package hongwei.leetcode.playground.other.sunday;

import hongwei.leetcode.playground.common.Log;

import java.util.Stack;

public class AlgoTest1 {

    private static final String START_TIME = "13.05 PM";
    private static final String FINISH_TIME = "13.15 PM";

    private static final String WORD_SEPARATOR = " ";

    private String result = "";

    public String reverseSentenceWordByWord(String sentence) {
        if (null == sentence) {
            return null;
        }

        Stack<String> stack = new Stack<>();

        String wordArray[] = sentence.split(WORD_SEPARATOR);
        for (String word : wordArray) {
            stack.push(word);
        }

        if (stack != null && stack.size() > 0) {
            while (stack.size() > 1) {
                result += stack.pop() + WORD_SEPARATOR;
            }
            result += stack.pop();
        }

        return result;
    }

    public void printResult() {
        Log.i(result);
    }

}
