package hongwei.leetcode.playground.other.sort;

import java.util.ArrayList;
import java.util.List;

public abstract class Sort {
    private static List<Integer> originalList = new ArrayList<>();
    private static List<Integer> resultList = null;

    public Sort(List<Integer> list) {
        originalList.addAll(list);
    }

    public void printOriginalList() {
        System.out.println(originalList.toString());
    }

    public void printResultList() {
        if (null == resultList) {
            System.out.println(resultList);
            return;
        }
        System.out.println(resultList.toString());
    }

    public void sort() {
        resultList = sortImpl(originalList);
    }

    public List<Integer> getResultList() {
        return resultList;
    }

    abstract protected List<Integer> sortImpl(List<Integer> list);
}
