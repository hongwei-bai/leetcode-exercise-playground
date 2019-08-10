package hongwei.leetcode.playground.common;

import java.util.ArrayList;
import java.util.List;

/**
 * Package: com.jd.aibdp.mike.algotest.common
 * User: baihongwei1
 * Email: baihongwei1@jd.com
 * Date: 2019/2/12
 * Time: 15:50
 * Description:
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int x) {
        val = x;
    }

    public TreeNode(int x, TreeNode left, TreeNode right) {
        val = x;
        this.left = left;
        this.right = right;
    }

    public TreeNode(Integer[] array) {
        if (array == null || array.length == 0) {
            return;
        }

        int N = array.length;
        // calculate L dynamically
        List<Integer> nullNodeListByDepth = new ArrayList<>();
        nullNodeListByDepth.add(0);
        int l = 0;
        int realCnt = 0;
        for (int i = 0; i < N; i++, realCnt++) {
            int accW = (int) Math.pow(2, l + 1) - 1;

            if (array[i] == null) {
                int old = nullNodeListByDepth.get(l);
                nullNodeListByDepth.set(l, old + 1);
            }

            if (realCnt >= accW) {
                l++;
                nullNodeListByDepth.add(0);
                for (int j = l - 1; j >= 1; j--) {
                    realCnt += nullNodeListByDepth.get(j) * (int) Math.pow(2, l - j);
                }
            }
        }

//        double L1 = Math.log10(N + 1) / Math.log10(2);
//        int L = (int) Math.ceil(L1);
        int L = l + 1;
        int MAX_WIDTH = (int) Math.pow(2, L - 1);

//            Log.i("aaaa", "N: " + N);
//            Log.i("aaaa", "binary tree layers - L: " + L);
//            Log.i("aaaa", "MAX_WIDTH: " + MAX_WIDTH);

        int[] width = new int[L];
        TreeNode[][] treeNodes = new TreeNode[L][MAX_WIDTH];
        for (int i = 0; i < L; i++) {
            width[i] = (int) Math.pow(2, i);
        }

        this.val = array[0];
        treeNodes[0][0] = this;
        int i = 1;

        // d, x are depth and x of parent nodes, respectively.
        for (int d = 0; d < L - 1; d++) {
            TreeNode[] parents = treeNodes[d];
            for (int x = 0; x < width[d]; x++) {
                TreeNode parent = parents[x];
                if (parent != null) {
                    if (i >= N) return;
                    if (array[i] != null) {
                        treeNodes[d + 1][x * 2] = new TreeNode(array[i]);
                        parent.left = treeNodes[d + 1][x * 2];
                    }
                    i++;
                    if (i >= N) return;
                    if (array[i] != null) {
                        treeNodes[d + 1][x * 2 + 1] = new TreeNode(array[i]);
                        parent.right = treeNodes[d + 1][x * 2 + 1];
                    }
                    i++;
                }
            }
        }
    }

    private void parse(Integer[] array, TreeNode[][] outputArray2d, int[] outputWidth) {

    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("" + val);

        List<TreeNode> parents = new ArrayList<>();
        parents.add(this);

        while (!parents.isEmpty()) {
            List<TreeNode> tmp = new ArrayList<>();
            for (TreeNode node : parents) {
                if (node.left != null) {
                    stringBuilder.append(", " + node.left.val);
                    tmp.add(node.left);
                    if (node.right != null) {
                        stringBuilder.append(", " + node.right.val);
                        tmp.add(node.right);
                    } else if (!tmp.isEmpty()) {
                        stringBuilder.append(", null");
                    }
                } else if (node.right != null) {
                    stringBuilder.append(", null");
                    stringBuilder.append(", " + node.right.val);
                    tmp.add(node.right);
                }
            }
            parents.clear();
            parents.addAll(tmp);
        }
        return stringBuilder.toString();
    }

    /*
++1++++1
+/+\++/+\
0++2+3++4
+/ \ / \
3++4 5++6
     */
    public String printTree() {
        return printTree(">");
    }

    public String printTree(String title) {
        Integer[] array = toArray();
        int N = array.length;

        // calculate L dynamically
        List<Integer> nullNodeListByDepth = new ArrayList<>();
        nullNodeListByDepth.add(0);
        int l = 0;
        int realCnt = 0;
        for (int i = 0; i < N; i++, realCnt++) {
            int accW = (int) Math.pow(2, l + 1) - 1;
//            Log.i("aaaa", "i: " + i + ", l: " + l + ", accW: " + accW + ", realCnt: " + realCnt);

            if (realCnt >= accW) {
                l++;
                nullNodeListByDepth.add(0);
                for (int j = l - 1; j >= 0; j--) {
                    realCnt += nullNodeListByDepth.get(j) * (int) Math.pow(2, l - j);
                }
            }

            if (array[i] == null) {
                int old = nullNodeListByDepth.get(l);
                nullNodeListByDepth.set(l, old + 1);
            }
        }
        int L = l + 1;
        int MAX_WIDTH = (int) Math.pow(2, L - 1);

        int[] width = new int[L];
        TreeNode[][] treeNodes = new TreeNode[L][MAX_WIDTH];
        for (int i = 0; i < L; i++) {
            width[i] = (int) Math.pow(2, i);
        }

        this.val = array[0];
        treeNodes[0][0] = this;
        int i = 1;

        boolean done = false;
        // d, x are depth and x of parent nodes, respectively.
        for (int d = 0; !done && d < L - 1; d++) {
            TreeNode[] parents = treeNodes[d];
            for (int x = 0; !done && x < width[d]; x++) {
                TreeNode parent = parents[x];
                if (parent != null) {
                    if (i >= N) {
                        done = true;
                        break;
                    }
                    if (array[i] != null) {
                        treeNodes[d + 1][x * 2] = new TreeNode(array[i]);
                    }
                    i++;
                    if (i >= N) {
                        done = true;
                        break;
                    }
                    if (array[i] != null) {
                        treeNodes[d + 1][x * 2 + 1] = new TreeNode(array[i]);
                    }
                    i++;
                }
            }
        }

//        for (int j = 0; j < treeNodes.length; j++) {
//            StringBuilder sb = new StringBuilder();
//            sb.append("w: " + width[j] + "-> ");
//            for (TreeNode n : treeNodes[j]) {
//                if (n != null) {
//                    sb.append(n.val + ", ");
//                } else {
//                    sb.append("null, ");
//                }
//            }
//            Log.i("aaaa", sb.toString());
//            Log.i("aaaa", "\n");
//        }

        // print
        // occupy 5 spaces (4 + 1 space) per 2 nodes at bottom.
        // occupy 5*2 = 10 at -2 row, 5*2*2 at -3 row
        // So, root node take 5 * 2^(d-2)
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(title);    // output ----->
        stringBuilder.append("\n"); // output ----->

        //TreeNode[][] treeNodes = new TreeNode[L][MAX_WIDTH];
        // root
        int root2W = (int) (5 * Math.pow(2, L - 2));//5 * 2 ^ ((L - d) - 2);
        stringBuilder.append(getSpace(root2W / 2)); // output ----->
        stringBuilder.append(val);

        for (int d = 1; d < L; d++) {
            int doubleW = (int) (5 * Math.pow(2, (L - d) - 2));//5 * 2 ^ ((L - d) - 2);
            int paddingLeft = doubleW / 2;
            int gapX = doubleW;

            stringBuilder.append("\n"); // output ----->
            stringBuilder.append(getSpace(paddingLeft + 1)); // output ----->
            StringBuilder nextLineStringBuilder = new StringBuilder();
            nextLineStringBuilder.append(getSpace(paddingLeft)); // output ----->
            for (int x = 0; x < width[d]; x++) {
                String nodeStr = " ";
                if (treeNodes[d][x] != null) {
                    nodeStr = treeNodes[d][x].val + "";
                    if (x % 2 == 0) {
                        //left
                        stringBuilder.append("/"); // output ----->
                    } else {
                        //right
                        stringBuilder.append("\\"); // output ----->
                    }
                } else {
                    stringBuilder.append(" "); // output ----->
                }

                if (x % 2 == 0) {
                    stringBuilder.append(getSpace(gapX - 2)); // output ----->
                } else {
                    stringBuilder.append(getSpace(gapX + 1)); // output ----->
                }

                nextLineStringBuilder.append(nodeStr); // output ----->
                if (x % 2 == 0) {
                    nextLineStringBuilder.append(getSpace(gapX)); // output ----->
                } else {
                    nextLineStringBuilder.append(getSpace(gapX - 1)); // output ----->
                }
            }
            stringBuilder.append("\n");
            stringBuilder.append(nextLineStringBuilder.toString());
        }
        return stringBuilder.toString();
    }

    private String getSpace(int width) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < width; i++) {
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }

    private Integer[] toArray() {
        List<Integer> list = new ArrayList<>();
        list.add(val);

        List<TreeNode> parents = new ArrayList<>();
        parents.add(this);

        while (!parents.isEmpty()) {
            List<TreeNode> tmp = new ArrayList<>();
            for (TreeNode node : parents) {
                if (node.left != null) {
                    list.add(node.left.val);
                    tmp.add(node.left);
                    if (node.right != null) {
                        list.add(node.right.val);
                        tmp.add(node.right);
                    } else if (!tmp.isEmpty()) {
                        list.add(null);
                    }
                } else if (node.right != null) {
                    list.add(null);
                    list.add(node.right.val);
                    tmp.add(node.right);
                }
            }
            parents.clear();
            parents.addAll(tmp);
        }
        return list.toArray(new Integer[0]);
    }
}
