package hongwei.leetcode.playground.other.jd;

import java.util.ArrayList;

public class Algorithm3 {
    private int maxValue = 0;
    private ArrayList<Integer> list = new ArrayList<>();
    private int steps = 0;
    final int DIVIDE_THRESHOLD = 10; // 10

    public void reset() {
        maxValue = 0;
        list.clear();
        steps = 0;
    }

    public Result run(int[] a) {

        int maxPossibleInterval = Formular.formula1(a.length, a[0], a[a.length - 1]);
        recur(a, 0, a.length - 1, maxPossibleInterval);

        Result r = new Result();
        r.name = "algorithm3";
        r.maxValue = maxValue;
        r.steps = steps;
        r.resultList.addAll(list);
        return r;
    }

    public void recur(int[] a, int start, int end, int maxPossibleInterval) {
        steps++;

        int n = end - start;
        if (n > DIVIDE_THRESHOLD) {
            int mid = (start + end) >>> 1;
            int grpDiff = a[mid] - a[mid - 1];
            judgeMax(grpDiff, mid - 1);
            int maxPossibleDiff1 = Formular.formula1(start, mid - 1, a[start], a[mid - 1]);
            int maxPossibleDiff2 = Formular.formula1(mid, end, a[mid], a[end]);
            if (maxPossibleDiff1 > maxPossibleDiff2) {
                if (maxPossibleDiff1 >= maxValue) {
                    recur(a, start, mid - 1, maxPossibleDiff1);
                    if (maxPossibleDiff2 >= maxValue) {
                        recur(a, mid, end, maxPossibleDiff2);
                    }
                }
            } else {
                if (maxPossibleDiff2 >= maxValue) {
                    recur(a, mid, end, maxPossibleDiff2);
                    if (maxPossibleDiff1 >= maxValue) {
                        recur(a, start, mid - 1, maxPossibleDiff1);
                    }
                }
            }
            return;
        }

        int maxPossibleIntervalLeft = maxPossibleInterval;
        for (int i = start; i < end && maxValue <= maxPossibleIntervalLeft; i++) {
            int diff = a[i + 1] - a[i];
            judgeMax(diff, i);
            maxPossibleIntervalLeft = Formular.formula2(maxPossibleIntervalLeft, diff);
            steps++;
        }
    }

    private void judgeMax(int newDiff, int idx) {
        int prevMax = maxValue;
        if (prevMax < newDiff) {
            maxValue = newDiff;
            list.clear();
            list.add(idx);
        } else if (prevMax == newDiff) {
            list.add(idx);
        } else {

        }
    }
}
