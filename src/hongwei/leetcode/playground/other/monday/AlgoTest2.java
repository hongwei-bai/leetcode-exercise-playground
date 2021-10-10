package hongwei.leetcode.playground.other.monday;

import hongwei.leetcode.playground.common.LogJava;

public class AlgoTest2 {

    public int getKthLargestInMultiplyArrayOf(int[] a, int k) {

        int lLength = (a.length - 1) * a.length / 2;
        if (k > lLength || k <= 0) {
            LogJava.i("L does not have the " + k + "th elements!");
            return -1;
        }

        int rank = calcRankInArrayA(k, a.length, lLength);

        int value = getValueInArrayAByRank(a, rank);

        return value;
    }

    private int calcRankInArrayA(int k, int n, int lLength) {
        int rank = 1; // start from 1, 1 is the largest.
        int numberInThisRound = 0;
        int accNumber = 0;

        for (int i = 0; i <= lLength; i++) {
            if (k == i) {
                return rank;
            }
            if (i >= accNumber) {
                rank++;
                numberInThisRound = rank - 1;
                accNumber += numberInThisRound;
            }
        }

        return -1;
    }

    private int getValueInArrayAByRank(int[] a, int rank) {
        return findk(rank, a, 0, a.length - 1);
    }

    private int findk(int k, int[] a, int low, int high) {
        int temp;
        temp = partition(a, low, high);
        if (temp == k - 1) {
            return a[temp];
        } else if (temp > k - 1)
            return findk(k, a, low, temp - 1);
        else
            return findk(k, a, temp + 1, high);
    }

    private int partition(int[] a, int low, int high) {
        int pivotKey = a[low];
        while (low < high) {

            while (low < high && pivotKey >= a[high]) {
                high--;
            }
            swap(a, low, high);

            while (low < high && pivotKey <= a[low]) {
                low++;
            }
            swap(a, low, high);
        }

        return low;
    }

    private void swap(int[] a, int idx1, int idx2) {
        int t = a[idx1];
        a[idx1] = a[idx2];
        a[idx2] = t;
    }
}
