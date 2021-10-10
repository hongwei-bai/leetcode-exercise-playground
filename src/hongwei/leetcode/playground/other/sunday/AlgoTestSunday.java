package hongwei.leetcode.playground.other.sunday;

import hongwei.leetcode.playground.common.LogJava;

import java.util.ArrayList;

public class AlgoTestSunday {
    public static void main(String[] args) {
        // AlgoTest1 test1 = new AlgoTest1();
        // test1.reverseSentenceWordByWord("Hello World I am Hongwei Bai");
        // test1.printResult();

        // AlgoTest2 test2 = new AlgoTest2();
        // ArrayList<Integer> list = new ArrayList<>();
        // list.add(2);
        // list.add(4);
        // list.add(5);
        // list.add(6);
        // list.add(7);
        // test2.init(list);
        // test2.runAlgo();

        BinarySearch binarySearch = new BinarySearch();
        ArrayList<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(190);
        list.add(34);
        list.add(66);
//        list.add(23);
//        list.add(5);
//        list.add(78);
//        list.add(121);
//        list.add(88);
//        list.add(12);
//        list.add(25);
        binarySearch.init(list);
        int index = binarySearch.find(190);
        LogJava.d("index = " + index);
    }
}
