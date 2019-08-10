package hongwei.leetcode.playground.other.baidu1;

public class TestStack {
    static int deep = 0;

    public void recursive() {
        deep++;
        recursive();
    }

    public static void main(String[] args) {
//        try {
//            new TestStack().recursive();
//        } catch (StackOverflowError e) {
//            System.out.print("deep = " + deep);
//        }
    }
}
