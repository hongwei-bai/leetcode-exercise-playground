package hongwei.leetcode.playground.leetcode_java;

import hongwei.leetcode.playground.common.LogJava;
import hongwei.leetcode.playground.common.TreeNode;
import hongwei.leetcode.playground.IQuestion;

/**
 * Package: com.jd.aibdp.algorithm
 * User: baihongwei1
 * Email: baihongwei1@jd.com
 * Date: 2019/2/12
 * Time: 10:39
 * Description:
 */
public class DistributeCoinsinBinaryTree implements IQuestion {
    @Override
    public void run() {
        LogJava.i("tag", "------- run -------");
        Integer[][] input = new Integer[][]{
                {1, 0, 2},
                {1, 0, 0, null, 3},
//                {1,2,3,4,5,6,7,8,9,8,7,6,5,4,3,2,1},
                {4, 0, null, null, 0, null, 0}
        };
        TreeNode[] input2 = new TreeNode[]{
                new TreeNode(1, new TreeNode(0), new TreeNode(2)),
                new TreeNode(1, new TreeNode(0, null, new TreeNode(3)), new TreeNode(0)),
                new TreeNode(4, new TreeNode(0, null, new TreeNode(0, null, new TreeNode(0))), null),
        };
        int[] ref = new int[]{
                2,
                4,
                6,
        };

        for (int i = 0; i < input.length; i++) {
            int result = distributeCoins(new TreeNode(input[i]));
//            int result = distributeCoins(input2[i]);
            if (result == ref[i]) {
                LogJava.i("tag", "case[" + i + "] passed");
            } else {
                LogJava.e("tag", "!!!!![WRONG]case[" + i + "] failed, result: " + result);
            }
        }
    }

    /*
    Given the root of a binary tree with N nodes, each node in the tree has node.val coins, and there are N coins total.

In one move, we may choose two adjacent nodes and move one coin from one node to another.
(The move may be from parent to child, or from child to parent.)

Return the number of moves required to make every node have exactly one coin.
     */
    /*
    Note:
        1<= N <= 100
        0 <= node.val <= N
     */

    public int distributeCoins(TreeNode root) {
        LogJava.i("tag", root.printTree());
        int[] ret = postorderTraversal(root);

        if (ret[0] == 0) {
            return ret[1];
        }

        return -1;
    }

    private int[] postorderTraversal(TreeNode p) {
        // leaf
        if (p.left == null && p.right == null) {
            return new int[]{p.val - 1, Math.abs(p.val - 1)};
        }

        // branch
        int childrenSum = 0;
        int stepSum = 0;
        if (p.left != null) {
            int[] leftRet = postorderTraversal(p.left);
            childrenSum += leftRet[0];
            stepSum += leftRet[1];
        }
        if (p.right != null) {
            int[] rightRet = postorderTraversal(p.right);
            childrenSum += rightRet[0];
            stepSum += rightRet[1];
        }

        int sum = childrenSum + p.val - 1;
        stepSum += Math.abs(sum);
        return new int[]{sum, stepSum};
    }
}
