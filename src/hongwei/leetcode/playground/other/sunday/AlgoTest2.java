package hongwei.leetcode.playground.other.sunday;

import hongwei.leetcode.playground.common.Log;

import java.util.ArrayList;

public class AlgoTest2 {
    private static final String START_TIME = "17.00 PM";
    private static final String FINISH_TIME = "17.40 PM";

    private int number = 0;
    private ArrayList<Integer> weightArray = null;
    private ArrayList<Integer> valueArray = null;
    private int wSum = 0;

    private ArrayList<Integer> group1 = new ArrayList<>();
    private ArrayList<Integer> group2 = new ArrayList<>();

    public void init(ArrayList<Integer> numberList) {
        this.weightArray = numberList;
        this.valueArray = numberList;
        number = numberList.size();
        int sum = 0;
        for (Integer integer : numberList) {
            sum += integer;
        }
        wSum = sum / 2;
    }

    public void runAlgo() {
        int result = funNoIter(4, 12);
        Log.d("result = " + result);
        printSelArray();
    }

    private int array[][];
    private boolean sel[][];

    private int funNoIter(int iBegin, int wSum) {
        array = new int[number][wSum + 1];
        sel = new boolean[number][wSum + 1];

        for (int w = 1; w <= wSum; w++) {
            for (int i = 0; i < number; i++) {
                int wi = weightArray.get(i);
                if (i == 0) {
                    array[i][w] = 0;
                } else if (wi > w) {
                    array[i][w] = getFromArray(i - 1, w);
                } else {
                    int pi = valueArray.get(i);
                    int a = getFromArray(i - 1, w);
                    int b = getFromArray(i - 1, w - wi) + pi;
                    if (a >= b) {
                        array[i][w] = a;
                    } else {
                        array[i][w] = b;
                        sel[i][w] = true;
                    }
                }
            }
        }
        return array[iBegin][wSum];
    }

    private void printSelArray() {
        int i = number, w = wSum;
        while (i-- > 0) {
            if (sel[i][w]) {
                Log.d("" + weightArray.get(i));
                w -= weightArray.get(i);
            }
        }
    }

    private int getFromArray(int i, int w) {
        if (i > -1 && w > 0) {
            return array[i][w];
        }
        return 0;
    }

    private int fun(int i, int w) {
        if (-1 == i || 0 == w) {
            return 0;
        }

        int wi = weightArray.get(i);
        if (wi > w) {
            return fun(i - 1, w);
        }

        int pi = valueArray.get(i);
        int a = fun(i - 1, w);
        int b = fun(i - 1, w - wi) + pi;
        if (a >= b) {
            return a;
        } else {
            return b;
        }
    }
}
