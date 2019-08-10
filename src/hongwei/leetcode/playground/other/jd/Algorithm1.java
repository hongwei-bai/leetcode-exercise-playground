package hongwei.leetcode.playground.other.jd;

import java.util.ArrayList;

public class Algorithm1 {
    public Result run(int[] a) {
        int maxValue = 0;
        int steps = 0;
        ArrayList<Integer> list = new ArrayList<>();
        int maxPossibleIntervalLeft = Formular.formula1(a.length, a[0], a[a.length - 1]);
        for (int i = 0; i < a.length && maxValue <= maxPossibleIntervalLeft; i++) {
            int diff = a[i + 1] - a[i];
            if (maxValue < diff) {
                maxValue = diff;
                list.clear();
                list.add(i);
            } else if (maxValue == diff) {
                list.add(i);
            } else {
                // do nothing
            }
            maxPossibleIntervalLeft = Formular.formula2(maxPossibleIntervalLeft, diff);
            steps++;
        }

        Result r = new Result();
        r.name = "algorithm(1)";
        r.maxValue = maxValue;
        r.steps = steps;
        r.resultList.addAll(list);
        return r;
    }
}
