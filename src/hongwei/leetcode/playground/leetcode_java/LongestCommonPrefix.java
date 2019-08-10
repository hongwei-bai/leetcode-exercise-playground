package hongwei.leetcode.playground.leetcode_java;

import hongwei.leetcode.playground.common.Log;
import hongwei.leetcode.playground.IQuestion;

/**
 * Package: com.jd.aibdp.algorithm
 * User: baihongwei1
 * Email: baihongwei1@jd.com
 * Date: 2019/1/31
 * Time: 13:55
 * Description:
 */
public class LongestCommonPrefix implements IQuestion {
    @Override
    public void run() {
        String[][] input = new String[][]{{"flower", "flow", "flight"}};
        String[] ref = new String[]{"fl"};
        for (int i = 0; i < input.length; i++) {
            String result = longestCommonPrefix(input[i]);
            if (ref[i].equals(result)) {
                Log.i("aaaa", "case[" + i + "] passed");
            } else {
                Log.e("aaaa", "!!!!![WRONG]case[" + i + "] failed.");
            }
        }
    }

    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            prefix = fun(prefix, strs[i]);
            if (prefix == null) {
                return "";
            }
        }
        return prefix;
    }

    private String fun(String a, String b) {
        char[] chars1 = a.toCharArray();
        char[] chars2 = b.toCharArray();
        int i = 0;
        int len = Math.min(chars1.length, chars2.length);
        while (i < len) {
            if (chars1[i] != chars2[i]) {
                break;
            }
            i++;
        }
        if (i == 0) {
            return null;
        }
        return a.substring(0, i);
    }
}
