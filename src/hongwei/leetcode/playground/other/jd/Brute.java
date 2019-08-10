package hongwei.leetcode.playground.other.jd;

import java.util.ArrayList;

public class Brute {
    public Result run(int[] a) {
        int maxValue = 0;
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < a.length - 1; i++) {
            int interval = a[i + 1] - a[i];
            if (maxValue < interval) {
                maxValue = interval;
                list.clear();
                list.add(i);
            } else if (maxValue == interval) {
                list.add(i);
            } else {
                // do nothing
            }
        }

        Result r = new Result();
        r.name = "brute    ";
        r.maxValue = maxValue;
        r.steps = a.length;
        r.resultList.addAll(list);
        return r;
    }
}
