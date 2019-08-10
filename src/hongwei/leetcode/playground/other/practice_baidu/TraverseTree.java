package hongwei.leetcode.playground.other.practice_baidu;

import java.util.Stack;

public class TraverseTree {
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

    public void preorder(Node root) {
        Stack<Node> s = new Stack<>();
        Node p = root;
        while (p != null || !s.isEmpty()) {
            while (p != null) {
                System.out.print(p.data + " ");
                if (p.right != null) {
                    s.push(p.right);
                }
                p = p.left;
            }

            if (!s.isEmpty()) {
                p = s.pop();
            }
        }
    }

    public void midorder(Node root) {
        Stack<Node> s = new Stack<>();
        Node p = root;
        while (p != null || !s.isEmpty()) {
            while (p != null) {
                s.push(p);
                p = p.left;
            }

            if (!s.isEmpty()) {
                p = s.pop();
                System.out.print(p.data + " ");
                p = p.right;
            }
        }
    }

    public void postorder(Node root) {
        Stack<Node> s = new Stack<>();
        Node p = root;
        Node lastVisited = null;
        while (p != null || !s.isEmpty()) {
            while (p != null) {
                s.push(p);
                p = p.left;
            }

            while (!s.isEmpty()) {
                p = s.pop();
                if (p.right == null || p.right == lastVisited) {
                    System.out.print(p.data + " ");
                    lastVisited = p;
                } else {
                    s.push(p);
                    p = p.right;
                    while (p != null) {
                        s.push(p);
                        p = p.left;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        TraverseTree traverse = new TraverseTree();
        Node root = traverse.initTree();
        System.out.print("pre order: ");
        traverse.preorderRecursive(root);

        System.out.print("\nmid order: ");
        traverse.midorderRecursive(root);

        System.out.print("\npost order:");
        traverse.postorderRecursive(root);

        System.out.print("\n\nIteration:");
        System.out.print("\npre order: ");
        traverse.preorder(root);

        System.out.print("\nmid order: ");
        traverse.midorder(root);

        System.out.print("\npost order: ");
        traverse.postorder(root);
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
