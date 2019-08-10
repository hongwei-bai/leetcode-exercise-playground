package hongwei.leetcode.playground.other.baidu1;

public class DPStair {
    public int dp(int n) {
        int[] box = new int[n + 1];
        box[1] = 1;
        box[2] = 2;
        for (int i = 3; i <= n; i++) {
            box[i] = box[i - 1] + box[i - 2];
        }
        return box[n];
    }

    public static void main(String[] args) {
        DPStair dpStair = new DPStair();
        System.out.print(dpStair.dp(3));
    }
}
