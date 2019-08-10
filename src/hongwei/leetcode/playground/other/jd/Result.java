package hongwei.leetcode.playground.other.jd;

import java.util.ArrayList;

public class Result {
    public String name;
    public int maxValue;
    public int steps;
    public ArrayList<Integer> resultList = new ArrayList<>();

    public void print() {
        // System.out.println("");
        System.out.print(">" + name + ":\t");
        System.out.print("steps: " + steps);
        System.out.print(", Max interval: " + maxValue + "\t");
        // System.out.print("They are:");
        // for (int i : resultList) {
        // System.out.print(" a[" + i + "]->a[" + (i + 1) + "],");
        // }
        // System.out.println("");
    }
}