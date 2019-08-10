package hongwei.leetcode.playground.leetcode_java;

import hongwei.leetcode.playground.common.Log;
import hongwei.leetcode.playground.IQuestion;

import java.util.Stack;

/**
 * Package: com.jd.aibdp.algorithm
 * User: baihongwei1
 * Email: baihongwei1@jd.com
 * Date: 2019/2/1
 * Time: 17:04
 * Description:
 */
public class ValidParentheses implements IQuestion {
    @Override
    public void run() {
        String[] input = new String[]{"()", "([])", "(){}", "([)]"};
        boolean[] ref = new boolean[]{true, true, true, false};

        for (int i = 0; i < input.length; i++) {
            boolean result = isValid(input[i]);
            if (result == ref[i]) {
                Log.i("aaaa", "case[" + i + "] passed");
            } else {
                Log.e("aaaa", "!!!!![WRONG]case[" + i + "] failed, result: " + result);
            }
        }
    }

    public boolean isValid(String s) {
        if (s.trim().isEmpty()) {
            return true;
        }
        Stack<Character> stack = new Stack<>();

        char[] chars = s.toCharArray();

        for (char c : chars) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
                continue;
            }

            if (stack.empty()) {
                return false;
            }

            if (c == ')') {
                char out = stack.pop();
                if (out != '(') return false;
            }
            if (c == ']') {
                char out = stack.pop();
                if (out != '[') return false;
            }
            if (c == '}') {
                char out = stack.pop();
                if (out != '{') return false;
            }
        }

        return stack.empty();
    }
}
