package hongwei.leetcode.playground.other.jd;

public class Verify {
    public static boolean verify(Result r0, Result r) {
        if (r0.maxValue != r.maxValue) {
            return false;
        }
        return r0.resultList.equals(QSortArray.sort(r.resultList));
    }
}
