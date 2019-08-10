package hongwei.leetcode.playground.other.tree;

import java.util.Stack;

public class Traverse {
    public Node initTree() {
        Node root = new Node("a");
        root.left = new Node("b");
        root.right = new Node("c");
        root.left.left = new Node("d");
        root.left.left.right = new Node("e");
        root.left.right = new Node("f");
        root.left.right.left = new Node("g");
        return root;
    }

    public void preorderRecursive(Node p) {
        if (null == p) {
            return;
        }
        System.out.print(p.data + " ");
        preorderRecursive(p.left);
        preorderRecursive(p.right);
    }

    public void midorderRecursive(Node p) {
        if (null == p) {
            return;
        }
        midorderRecursive(p.left);
        System.out.print(p.data + " ");
        midorderRecursive(p.right);
    }

    public void postorderRecursive(Node p) {
        if (null == p) {
            return;
        }
        postorderRecursive(p.left);
        postorderRecursive(p.right);
        System.out.print(p.data + " ");
    }

    public void preorderIteration(Node root) {
        Node p = root;
        Stack<Node> stack = new Stack<>();
        while (p != null || !stack.isEmpty()) {
            while (p != null) {
                System.out.print(p.data + " ");
                if (p.right != null) {
                    stack.push(p.right);
                }
                p = p.left;
            }
            if (!stack.isEmpty()) {
                p = stack.pop();
            }
        }
    }

    public static void main(String[] args) {
        Traverse traverse = new Traverse();
        Node root = traverse.initTree();
        System.out.print("pre order: ");
        traverse.preorderRecursive(root);

        System.out.print("\nmid order: ");
        traverse.midorderRecursive(root);

        System.out.print("\npost order:");
        traverse.postorderRecursive(root);

        System.out.print("\n\nIteration:");
        System.out.print("\npre order: ");
        traverse.preorderIteration(root);
    }

    public class Node {
        public String data = "";
        public Node left = null;
        public Node right = null;

        public Node() {
        }

        public Node(String name) {
            this.data = name;
        }
    }

}
