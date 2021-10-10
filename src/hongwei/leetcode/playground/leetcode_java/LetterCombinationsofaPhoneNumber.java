package hongwei.leetcode.playground.leetcode_java;

import hongwei.leetcode.playground.common.LogJava;
import hongwei.leetcode.playground.IQuestion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Package: com.jd.aibdp.algorithm
 * User: baihongwei1
 * Email: baihongwei1@jd.com
 * Date: 2019/2/1
 * Time: 13:29
 * Description:
 */
public class LetterCombinationsofaPhoneNumber implements IQuestion {
    @Override
    public void run() {
        String[] input = new String[]{"23"};
        List<String>[] ref = new List[]{Arrays.asList("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf")};

        for (int i = 0; i < input.length; i++) {
            List<String> result = letterCombinations(input[i]);
            if (ref[i].equals(result)) {
                LogJava.i("aaaa", "case[" + i + "] passed");
            } else {
                LogJava.e("aaaa", "!!!!![WRONG]case[" + i + "] failed, result: " + result);
            }
        }
    }

    /*
     2 abc
     3 def
     4 ghi
     5 jkl
     6 mno
     7 pqrs
     8 tuv
     9 wxyz
      */

    private static List<Character>[] sButtons = new List[8];

    static {
        sButtons[0] = new ArrayList<>();
        sButtons[1] = new ArrayList<>();
        sButtons[2] = new ArrayList<>();
        sButtons[3] = new ArrayList<>();
        sButtons[4] = new ArrayList<>();
        sButtons[5] = new ArrayList<>();
        sButtons[6] = new ArrayList<>();
        sButtons[7] = new ArrayList<>();

        sButtons[0].addAll(Arrays.asList('a', 'b', 'c')); //2
        sButtons[1].addAll(Arrays.asList('d', 'e', 'f'));
        sButtons[2].addAll(Arrays.asList('g', 'h', 'i'));
        sButtons[3].addAll(Arrays.asList('j', 'k', 'l'));
        sButtons[4].addAll(Arrays.asList('m', 'n', 'o'));
        sButtons[5].addAll(Arrays.asList('p', 'q', 'r', 's'));
        sButtons[6].addAll(Arrays.asList('t', 'u', 'v'));
        sButtons[7].addAll(Arrays.asList('w', 'x', 'y', 'z')); //9
    }

    public List<String> letterCombinations(String digits) {
        if (digits.trim().isEmpty()) {
            return new ArrayList<>();
        }
        char[] chars = digits.toCharArray();
        int[] ints = new int[chars.length];
        for (int i = 0; i < chars.length; i++) {
            ints[i] = getIntWith2Shift(chars[i]);
        }

        List<String> builderList = doRecursive(ints, chars.length - 1);
        return builderList;
    }

    private List<String> doRecursive(int[] ints, int endIdx) {
        List<String> listBuilder = new ArrayList<>();
        if (endIdx == 0) {
            for (char c : sButtons[ints[endIdx]]) {
                listBuilder.add(Character.toString(c));
            }
        } else {
            List<String> listlistBuilder2 = doRecursive(ints, endIdx - 1);
            List<Character> buttonChars = sButtons[ints[endIdx]];
            for (String sb2 : listlistBuilder2) {
                for (char c : buttonChars) {
                    listBuilder.add(sb2 + c);
                }
            }
        }
        return listBuilder;
    }

    public List<String> letterCombinations2(String digits) {
        if (digits.trim().isEmpty()) {
            return new ArrayList<>();
        }
        char[] chars = digits.toCharArray();
        int[] ints = new int[chars.length];
        for (int i = 0; i < chars.length; i++) {
            ints[i] = getIntWith2Shift(chars[i]);
        }

        List<StringBuilder> builderList = doRecursive2(ints, chars.length - 1);
        List<String> list = new ArrayList<>();
        for (StringBuilder sb : builderList) {
            list.add(sb.toString());
        }
        return list;
    }

    private List<StringBuilder> doRecursive2(int[] ints, int endIdx) {
        List<StringBuilder> listBuilder = new ArrayList<>();
        if (endIdx == 0) {
            for (char c : sButtons[ints[endIdx]]) {
                listBuilder.add(new StringBuilder().append(c));
            }
        } else {
            List<StringBuilder> listlistBuilder2 = doRecursive2(ints, endIdx - 1);
            List<Character> buttonChars = sButtons[ints[endIdx]];
            for (StringBuilder sb2 : listlistBuilder2) {
                for (char c : buttonChars) {
                    listBuilder.add(new StringBuilder().append(sb2).append(c));
                }
            }
        }
        return listBuilder;
    }

    private int getIntWith2Shift(char c) {
        return c - '2';
    }
}
