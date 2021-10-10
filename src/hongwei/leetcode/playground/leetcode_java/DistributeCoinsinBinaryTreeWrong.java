package hongwei.leetcode.playground.leetcode_java;

import hongwei.leetcode.playground.common.LogJava;
import hongwei.leetcode.playground.common.TreeNode;
import hongwei.leetcode.playground.IQuestion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Package: com.jd.aibdp.algorithm
 * User: baihongwei1
 * Email: baihongwei1@jd.com
 * Date: 2019/2/12
 * Time: 10:39
 * Description:
 */
public class DistributeCoinsinBinaryTreeWrong implements IQuestion {
    @Override
    public void run() {
        LogJava.i("aaaa", "------- run -------");
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
                LogJava.i("aaaa", "case[" + i + "] passed");
            } else {
                LogJava.e("aaaa", "!!!!![WRONG]case[" + i + "] failed, result: " + result);
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

    // link map <id, reachable id list>
    private HashMap<Integer, List<Integer>> hashMap = new HashMap<>();

    // value list <id> -> value
    private List<Integer> valueList = new ArrayList<>();

    public int distributeCoins(TreeNode root) {
        hashMap.clear();
        valueList.clear();

        LogJava.i("aaaa", "" + root.printTree());

        // Preorder traversal.
        List<TreeNode> preorderNodeList = new ArrayList<>();
        preorderTraversal(root, preorderNodeList);

        // Build reverse index map
        HashMap<TreeNode, Integer> idMap = new HashMap<>();
        for (int i = 0; i < preorderNodeList.size(); i++) idMap.put(preorderNodeList.get(i), i);

        // Use i as id of nodes.
        for (int i = 0; i < preorderNodeList.size(); i++) addToMap(i, preorderNodeList.get(i), idMap);

        // Build value list
        for (TreeNode node : preorderNodeList) valueList.add(node.val);

        // test
//        Log.i("aaaa", "val: " + valueList.toString());
//        for (TreeNode node : preorderNodeList)Log.i("aaaa", "link: " + idMap.get(node) + "->" + hashMap.get(idMap.get(node)));

//        int steps = dp(valueList);
        Random r = new Random();

        // No DP solution
        List<Integer> values = new ArrayList<>(valueList);
        int steps = 0;
        while (!judge(values)) {
            int maxVal = 0;
            int maxId = -1;
            for (int i = 0; i < values.size(); i++) {
                if (values.get(i) > maxVal) {
                    maxVal = values.get(i);
                    maxId = i;
                } else if (values.get(i) == maxVal) {
                    if (r.nextBoolean()) {
                        maxVal = values.get(i);
                        maxId = i;
                    }
                }
            }

            List<Integer> possibleDest = hashMap.get(maxId);
            int minVal = Integer.MAX_VALUE;
            int minId = -1;
            for (int destId : possibleDest) {
                if (values.get(destId) < minVal) {
                    minVal = values.get(destId);
                    minId = destId;
                } else if (values.get(destId) == minVal) {
                    if (r.nextBoolean()) {
                        minVal = values.get(destId);
                        minId = destId;
                    }
                }
            }

            int maxValBeforeChange = values.get(maxId);
            int minValBeforeChange = values.get(minId);
            values.set(maxId, maxValBeforeChange - 1);
            values.set(minId, minValBeforeChange + 1);
            steps++;
        }

        return steps;
    }

    private boolean judge(List<Integer> values) {
        for (int value : values) {
            if (value != 1) {
                return false;
            }
        }
        return true;
    }

    private void preorderTraversal(TreeNode p, List<TreeNode> output) {
        output.add(p);
        if (p.left != null) preorderTraversal(p.left, output);
        if (p.right != null) preorderTraversal(p.right, output);
    }

    private void addToMap(int id, TreeNode node, HashMap<TreeNode, Integer> idMap) {
        if (!hashMap.containsKey(id)) {
            hashMap.put(id, new ArrayList<Integer>());
        }
        if (node.left != null) {
            int leftId = idMap.get(node.left);
            hashMap.get(id).add(leftId);
            if (!hashMap.containsKey(leftId)) {
                hashMap.put(leftId, new ArrayList<Integer>());
            }
            if (!hashMap.get(leftId).contains(id)) {
                hashMap.get(leftId).add(id);
            }
        }
        if (node.right != null) {
            int rightId = idMap.get(node.right);
            hashMap.get(id).add(rightId);
            if (!hashMap.containsKey(rightId)) {
                hashMap.put(rightId, new ArrayList<Integer>());
            }
            if (!hashMap.get(rightId).contains(id)) {
                hashMap.get(rightId).add(id);
            }
        }
    }
}
