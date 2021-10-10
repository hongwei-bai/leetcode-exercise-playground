package hongwei.leetcode.playground.other.moov;

import java.util.HashMap;

import hongwei.leetcode.playground.common.LogJava;
import hongwei.leetcode.playground.other.utils.BinarySearch;
import hongwei.leetcode.playground.other.utils.QSort;

/**
 * @author Hongwei Bai
 * 
 * @see implement() is the generic solution.
 * 
 * @see implementAlternative() has many limitations. But under some certain
 *      conditions, it is more efficient.
 * 
 * @see If timespan for most tasks are small, and the whole timespan is small,
 *      implementAlternative is more efficient. Otherwise, use generic
 *      implement.
 */
public class QueryCalc {
    public static void main(String[] args) {
        QueryCalc calc = new QueryCalc();
        int start[] = { 0, 4, 2 };
        int end[] = { 4, 8, 8 };
        int n = 3;
        int m = 4;
        int query[] = { 1, 9, 4, 3 };
        calc.printArray(calc.numberOfTaskRunning(start, end, n, query, m));
    }

    public void printArray(int[] a) {
        if (null == a) {
            LogJava.i("null");
            return;
        }
        String s = "[";
        for (int i = 0; i < a.length - 1; i++) {
            s += a[i] + ",";
        }
        s += a[a.length - 1] + "]";
        LogJava.i(s);
    }

    public int[] numberOfTaskRunning(int[] start, int[] end, int n, int[] query, int m) {
        if (getMaxTime(end) < 5000) {
            return implementAlternative(start, end, n, query, m);
        }
        return implement(start, end, n, query, m);
    }

    /**
     * Time complexity is O(m * logn)
     */
    private int[] implement(int[] start, int[] end, int n, int[] query, int m) {
        // time sections array
        int s[] = new int[n * 2];

        // number of running task for time sections
        int r[] = new int[n * 2];

        // map s[i] -> i
        HashMap<Integer, Integer> sReverse = new HashMap<>();

        // initialize array r[]
        for (int i = 0; i < n; i++) {
            r[i] = 0;
            r[n + i] = 0;
        }

        // break down time to small sections
        for (int i = 0; i < n; i++) {
            s[i] = start[i];
            s[n + i] = end[i];
        }
        s = QSort.sort(s);

        for (int i = 0; i < n * 2; i++) {
            sReverse.put(s[i], i);
        }

        // mark result for each time section
        for (int i = 0; i < n; i++) {
            int sIdx = sReverse.get(start[i]);
            int eIdx = sReverse.get(end[i]);
            for (int j = sIdx; j < eIdx; j++) {
                r[j]++;
            }
        }

        int result[] = new int[m];
        for (int i = 0; i < m; i++) {
            int idx = BinarySearch.findClosestLeftNumber(query[i], s);
            result[i] = r[idx];
        }

        return result;
    }

    /**
     * Time complexity is O(min(m, n^2))
     */
    private int[] implementAlternative(int[] start, int[] end, int n, int[] query, int m) {
        int maxTime = getMaxTime(end);

        // create continuous memory for all time span
        int timepiece[] = new int[maxTime];
        for (int i = 0; i < n; i++) {
            for (int j = start[i]; j < end[i]; j++) {
                // mark
                timepiece[j]++;
            }
        }

        int result[] = new int[m];
        for (int i = 0; i < m; i++) {
            if (query[i] < maxTime) {
                result[i] = timepiece[query[i]];
            } else {
                result[i] = 0;
            }
        }

        return result;
    }

    private int getMaxTime(int[] end) {
        int maxTime = 0;
        for (int e : end) {
            if (maxTime < e) {
                maxTime = e;
            }
        }
        return maxTime;
    }
}
