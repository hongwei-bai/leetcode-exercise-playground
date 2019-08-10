package hongwei.leetcode.playground.other.baidu1;

import java.util.ArrayList;
import java.util.Stack;

import hongwei.leetcode.playground.other.utils.Node;

public class FindTreeSum {
    public Node initTree() {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(5);
        root.left.right = new Node(6);
        root.left.right.left = new Node(8);
        root.right.right = new Node(4);
        return root;
    }

    public void printSumList(Node tree, int sum) {
        Node p = tree;
        ArrayList<Stack<Node>> list = traverse(p, 0, sum);
        for (Stack<Node> s : list) {
            System.out.print("path: ");
            while (!s.empty()) {
                System.out.print(s.pop().data + " ");
            }
            System.out.print("\n");
        }
    }

    public ArrayList<Stack<Node>> traverse(Node p, int add, int sum) {
        add += p.data;
        if (add < sum) {
            ArrayList<Stack<Node>> list = new ArrayList<>();
            if (p.left != null) {
                ArrayList<Stack<Node>> l = traverse(p.left, add, sum);
                for (Stack<Node> s : l) {
                    s.push(p);
                }
                list.addAll(l);
            }
            if (p.right != null) {
                ArrayList<Stack<Node>> l = traverse(p.right, add, sum);
                for (Stack<Node> s : l) {
                    s.push(p);
                }
                list.addAll(l);
            }
            return list;
        } else if (add == sum) {
            if (p.left == null && p.right == null) {
                ArrayList<Stack<Node>> list = new ArrayList<>();
                Stack<Node> stack = new Stack<>();
                stack.push(p);
                list.add(stack);
                return list;
            }
        }
        return new ArrayList<>();
    }

    public static void main(String[] args) {
        FindTreeSum findTreeSum = new FindTreeSum();
        Node tree = findTreeSum.initTree();
        findTreeSum.printSumList(tree, 17);
    }
}
