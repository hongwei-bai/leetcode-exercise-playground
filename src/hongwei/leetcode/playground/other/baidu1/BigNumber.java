package hongwei.leetcode.playground.other.baidu1;

public class BigNumber {
    public Node initListA() {
        Node node = new Node(1);
        node.next = new Node(3);
        node.next.next = new Node(6);
        node.next.next.next = new Node(6);
        return node;
    }

    public Node initListB() {
        Node node = new Node(4);
        node.next = new Node(7);
        return node;
    }

    public Node addBigNumber(Node lista, Node listb) {
        Node rla = reverse(lista);
        Node rlb = reverse(listb);
        int advanced = 0;
        Node rlc = null, cc = null;
        while (rla != null || rlb != null) {
            int na = rla != null ? rla.data : 0;
            int nb = rlb != null ? rlb.data : 0;
            int n = na + nb + advanced;
            int left = n % 10;
            advanced = n / 10;
            if (cc == null) {
                cc = new Node(left);
                rlc = cc;
            } else {
                cc.next = new Node(left);
                cc = cc.next;
            }

            rla = rla != null ? rla.next : null;
            rlb = rlb != null ? rlb.next : null;
        }
        return reverse(rlc);
    }

    public Node reverse(Node head) {
        Node prev = null;
        Node cur = head;
        while (cur != null) {
            Node t = cur.next;
            cur.next = prev;
            prev = cur;
            cur = t;
        }
        return prev;
    }

    private class Node {
        public int data;
        public Node next;

        public Node(int d) {
            data = d;
        }
    }

    public static void main(String[] args) {
        BigNumber bigNumber = new BigNumber();
        Node a = bigNumber.initListA();
        Node b = bigNumber.initListB();
        Node c = bigNumber.addBigNumber(a, b);
        // Node c = bigNumber.reverse(b);
        while (c != null) {
            System.out.println(c.data + "->");
            c = c.next;
        }
    }
}
