package hongwei.leetcode.playground.leetcode_java;

import hongwei.leetcode.playground.common.LogJava;
import hongwei.leetcode.playground.IQuestion;

/**
 * Package: com.jd.aibdp.algorithm
 * User: baihongwei1
 * Email: baihongwei1@jd.com
 * Date: 2019/2/11
 * Time: 16:04
 * Description:
 */
public class UniquePathsIII implements IQuestion {
    @Override
    public void run() {
        LogJava.i("aaaa", "------- run -------");
        int[][][] input = new int[][][]{
                {{1, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 2, -1}}
        };
        int[] ref = new int[]{
                2
        };

        for (int i = 0; i < input.length; i++) {
            int result = uniquePathsIII(input[i]);
            if (result == ref[i]) {
                LogJava.i("aaaa", "case[" + i + "] passed");
            } else {
                LogJava.e("aaaa", "!!!!![WRONG]case[" + i + "] failed, result: " + result);
            }
        }
    }

    /*
    On a 2-dimensional grid, there are 4 types of squares:

1 represents the starting square.  There is exactly one starting square.
2 represents the ending square.  There is exactly one ending square.
0 represents empty squares we can walk over.
-1 represents obstacles that we cannot walk over.
Return the number of 4-directional walks from the starting square to the ending square, that walk over every non-obstacle square exactly once.
     */
    public int uniquePathsIII(int[][] grid) {
        yMax = grid.length;
        xMax = grid[0].length;

        STEP_DESIRED = 1;
        int startX = 0, startY = 0;
        for (int y = 0; y < yMax; y++) {
            for (int x = 0; x < xMax; x++) {
                if (grid[y][x] == 0) {
                    STEP_DESIRED++;
                } else if (grid[y][x] == 1) {
                    startX = x;
                    startY = y;
                }
            }
        }

//        Log.i("aaaa", "ENTRY: x:" + startX + ", y:" + startY);
        grid[startY][startX] = -1;
        return dp(grid, startX, startY, 0);
    }

    private int xMax, yMax, STEP_DESIRED;

    private int dp(int[][] grid, int x, int y, int steps) {
        //deal myself
//        Log.i("aaaa", "dp, x:" + x + ", y:" + y);
        if (grid[y][x] == 2 && STEP_DESIRED == steps) {
            return 1;
        }

        int count = 0;
        if (x > 0 && grid[y][x - 1] != -1) {
            int[][] newg = copy(grid);
            mark(newg, x, y);
            count += dp(newg, x - 1, y, steps + 1);
        }
        if (x < xMax - 1 && grid[y][x + 1] != -1) {
            int[][] newg = copy(grid);
            mark(newg, x, y);
            count += dp(newg, x + 1, y, steps + 1);
        }
        if (y > 0 && grid[y - 1][x] != -1) {
            int[][] newg = copy(grid);
            mark(newg, x, y);
            count += dp(newg, x, y - 1, steps + 1);
        }
        if (y < yMax - 1 && grid[y + 1][x] != -1) {
            int[][] newg = copy(grid);
            mark(newg, x, y);
            count += dp(newg, x, y + 1, steps + 1);
        }
        return count;
    }

    private int[][] copy(int[][] grid) {
        int[][] newg = new int[yMax][xMax];
        for (int y = 0; y < yMax; y++) {
            System.arraycopy(grid[y], 0, newg[y], 0, xMax);
        }
        return newg;
    }

    private void mark(int[][] grid, int x1, int y1) {
        for (int y = 0; y < yMax; y++) {
            for (int x = 0; x < xMax; x++) {
                if (x1 == x && y1 == y) {
                    grid[y][x] = -1;
                }
            }
        }
    }

    private String toString(int[][] grid) {
        StringBuilder stringBuilder = new StringBuilder("\n");
        for (int y = 0; y < yMax; y++) {
            for (int x = 0; x < xMax; x++) {
                stringBuilder.append(grid[y][x] + " ");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
