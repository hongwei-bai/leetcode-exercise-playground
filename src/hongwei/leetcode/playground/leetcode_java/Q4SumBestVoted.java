package hongwei.leetcode.playground.leetcode_java;

import hongwei.leetcode.playground.common.Log;
import hongwei.leetcode.playground.IQuestion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Package: com.jd.aibdp.algorithm
 * User: baihongwei1
 * Email: baihongwei1@jd.com
 * Date: 2019/2/1
 * Time: 14:29
 * Description:
 */
public class Q4SumBestVoted implements IQuestion {
    @Override
    public void run() {
//        int[][] input = new int[][]{{1, 0, -1, 0, -2, 2}};
//        int[] targets = new int[]{0};

//        int[][] input = new int[][]{{-7, -5, 0, 7, 1, 1, -10, -2, 7, 7, -2, -6, 0, -10, -5, 7, -8, 5}};
//        int[] targets = new int[]{28};

//        int[][] input = new int[][]{{5, 5, 3, 5, 1, -5, 1, -2}};
//        int[] targets = new int[]{4};

//        int[][] input = new int[][]{{0, 1, 5, 0, 1, 5, 5, -4}};
//        int[] targets = new int[]{11};

//        int[][] input = new int[][]{{-4, -3, -2, -1, 0, 0, 1, 2, 3, 4}};
//        int[] targets = new int[]{0};

//        int[][] input = new int[][]{{-5, 5, 4, -3, 0, 0, 4, -2}};
//        int[] targets = new int[]{4};

        int[][] input = new int[][]{{5, 0, 2, -5, -5, 4, -5, 1, -1}};
        int[] targets = new int[]{-5};

        List<List<Integer>>[] ref = new List[]{Arrays.asList(
                Arrays.asList(-1, 0, 0, 1),
                Arrays.asList(-2, -1, 1, 2),
                Arrays.asList(-2, 0, 0, 2)
        )};

        for (int i = 0; i < input.length; i++) {
            List<List<Integer>> result = fourSum(input[i], targets[i]);
            if (result != null && ref[i].equals(result)) {
                Log.i("aaaa", "case[" + i + "] passed");
            } else {
                Log.e("aaaa", "!!!!![WRONG]case[" + i + "] failed, result: " + result);
            }
        }
    }

    public List<List<Integer>> fourSum(int[] num, int target) {
        ArrayList<List<Integer>> ans = new ArrayList<>();
        if (num.length < 4) return ans;
        Arrays.sort(num);
        for (int i = 0; i < num.length - 3; i++) {
            if (num[i] + num[i + 1] + num[i + 2] + num[i + 3] > target)
                break; //first candidate too large, search finished
            if (num[i] + num[num.length - 1] + num[num.length - 2] + num[num.length - 3] < target)
                continue; //first candidate too small


            if (i > 0 && num[i] == num[i - 1]) continue; //prevents duplicate result in ans list
            for (int j = i + 1; j < num.length - 2; j++) {
                if (num[i] + num[j] + num[j + 1] + num[j + 2] > target) break; //second candidate too large
                if (num[i] + num[j] + num[num.length - 1] + num[num.length - 2] < target)
                    continue; //second candidate too small
                if (j > i + 1 && num[j] == num[j - 1]) continue; //prevents duplicate results in ans list
                int low = j + 1, high = num.length - 1;
                while (low < high) {
                    int sum = num[i] + num[j] + num[low] + num[high];
                    if (sum == target) {
                        ans.add(Arrays.asList(num[i], num[j], num[low], num[high]));
                        while (low < high && num[low] == num[low + 1]) low++; //skipping over duplicate on low
                        while (low < high && num[high] == num[high - 1]) high--; //skipping over duplicate on high
                        low++;
                        high--;
                    }
                    //move window
                    else if (sum < target) low++;
                    else high--;
                }
            }
        }
        return ans;
    }


    /*
    [-4,-3,-2,-1,0,0,1,2,3,4]
0

    [[-4,-3,3,4],[-4,-2,2,4],[-4,-1,1,4],[-4,-1,2,3],[-4,0,0,4],[-4,0,1,3],[-4,1,1,2],[-3,-2,1,4],[-3,-2,2,3],[-3,-1,0,4],[-3,-1,1,3],[-3,0,0,3],[-3,0,1,2],[-2,-1,-1,4],[-2,-1,0,3],[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]

    [[-4,-3,3,4],[-4,-2,2,4],[-4,-1,1,4],[-4,-1,2,3],[-4,0,0,4],[-4,0,1,3]           ,[-3,-2,1,4],[-3,-2,2,3],[-3,-1,0,4],[-3,-1,1,3],[-3,0,0,3],[-3,0,1,2],             [-2,-1,0,3],[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
     */
}
