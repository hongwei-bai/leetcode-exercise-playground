package hongwei.leetcode.playground.other.jdbk;

import java.util.ArrayList;

import hongwei.leetcode.playground.other.jd.Formular;
import hongwei.leetcode.playground.other.jd.Result;

public class Algorithm2 {
    private static final boolean isPrintInternalProcedure = false;

    public Result run(int[] a) {
        int steps = 0;
        int maxValue = 0;
        ArrayList<Integer> list = new ArrayList<>();

        final int DIVIDE_THRESHOLD = 10; // 10
        int n = a.length;

        int fold = 0;
        while (n > DIVIDE_THRESHOLD) {
            n >>>= 1;
            fold++;
        }
        final int eachGrpCount = n;

        final int subArrayCount = fold << 1;
        int[] divPoint = new int[subArrayCount];
        int[] sectionMaxPossibleDiff = new int[subArrayCount];
        for (int i = 0; i < subArrayCount; i++) {
            // divide into 2 arrays 0-(n-1), n-length
            int start = i * eachGrpCount;
            int end = (i + 1) * eachGrpCount - 1;

            String s = "from " + start + " to " + end;
            if (i < subArrayCount - 1) {
                int grpDiff = a[end + 1] - a[end];
                maxValue = judgeMax(maxValue, grpDiff, list, end);
                divPoint[i + 1] = end + 1;
                s += ", grpDiff " + grpDiff + ", divPoint[i] = " + divPoint[i];
            }
            LogInternal(s);
            steps++;
        }

        printArray("divPoint", divPoint, true);

        for (int i = 0; i < subArrayCount; i++) {
            int start = i * eachGrpCount;
            int end = (i + 1) * eachGrpCount - 1;
            sectionMaxPossibleDiff[i] = Formular.formula1(start, end, a[start], a[end]);
            if (sectionMaxPossibleDiff[i] < maxValue) {
                // cut branch, to do...
                sectionMaxPossibleDiff[i] = -1;
            }
        }
        printArray("sectionMaxPossibleDiff", sectionMaxPossibleDiff, true);

        // mark section sorted sequence
        int[] sortArray = generateSortArrayDescending(sectionMaxPossibleDiff);
        printArray("sortArray", sortArray, true);

        // traverse the most possible section
        for (int k = 0; k < sortArray.length; k++) {
            if (sectionMaxPossibleDiff[k] < 0) {
                continue;
            }
            int idx = sortArray[k];
            int start = divPoint[k];
            int end = k < sortArray.length - 1 ? (divPoint[k + 1] - 1) : a.length - 1;
            // System.out.println("start = " + start + ", end = " + end);
            int maxPossibleIntervalLeft = Formular.formula1(start, end, a[start], a[end]);
            for (int i = start; i < end && maxValue <= maxPossibleIntervalLeft; i++) {
                int diff = a[i + 1] - a[i];
                maxValue = judgeMax(maxValue, diff, list, i);
                maxPossibleIntervalLeft = Formular.formula2(maxPossibleIntervalLeft, diff);
                steps++;
            }

            // cut branch again
            for (int i = 0; i < subArrayCount; i++) {
                if (sectionMaxPossibleDiff[i] > -1 && sectionMaxPossibleDiff[i] < maxValue) {
                    // cut branch, to do...
                    sectionMaxPossibleDiff[i] = -1;
                    printArray("sectionMaxPossibleDiff(cut)", sectionMaxPossibleDiff, true);
                }
            }
        }

        Result r = new Result();
        r.name = "algorithm(2)";
        r.maxValue = maxValue;
        r.steps = steps;
        r.resultList.addAll(list);
        return r;
    }

    private int[] generateSortArrayDescending(final int[] a0) {
        int[] sort = new int[a0.length];
        for (int i = 0; i < a0.length; i++) {
            sort[i] = i;
        }
        int[] a = new int[a0.length];
        System.arraycopy(a0, 0, a, 0, a0.length);

        // insert sort, need to be improved
        // to do...
        for (int i = 0; i < a.length; i++) {
            for (int j = i; j > 0; j--) {
                if (a[j] > a[j - 1]) {
                    int ts = sort[j - 1];
                    int t = a[j - 1];

                    sort[j - 1] = sort[j];
                    a[j - 1] = a[j];

                    sort[j] = ts;
                    a[j] = t;
                }
            }
        }
        return sort;
    }

    private int judgeMax(int prevMax, int newDiff, ArrayList<Integer> list, int idx) {
        if (prevMax < newDiff) {
            prevMax = newDiff;
            list.clear();
            list.add(idx);
        } else if (prevMax == newDiff) {
            list.add(idx);
        } else {

        }
        return prevMax;
    }

    public static void printArray(String name, int[] a) {
        printArray(name, a, false);
    }

    public static void printArray(String name, int[] a, boolean internal) {
        String s = "";
        s += "array " + name + " has " + a.length + " elements: ";
        for (int i : a) {
            s += i + " ";
        }
        if (internal) {
            LogInternal(s);
        } else {
            System.out.println(s);
        }
    }

    public static void LogInternal(String info) {
        if (!isPrintInternalProcedure)
            return;
        System.out.println(info);
    }
}
