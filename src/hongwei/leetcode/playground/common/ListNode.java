package hongwei.leetcode.playground.common;

/**
 * Package: com.jd.aibdp.mike.algotest.common
 * User: baihongwei1
 * Email: baihongwei1@jd.com
 * Date: 2019/2/15
 * Time: 16:13
 * Description:
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

    public ListNode(int x, ListNode n) {
        val = x;
        next = n;
    }

    public ListNode(int[] array) {
        if (null == array || 0 == array.length) {
            return;
        }

        val = array[0];

        ListNode p = this;
        for (int i = 1; i < array.length; i++) {
            p.next = new ListNode(array[i]);
            p = p.next;
        }
    }

    @Override
    public String toString() {
        if (next != null) {
            return val + "->" + next.toString();
        }
        return val + "";
    }

    @Override
    public boolean equals(Object obj) {
        ListNode r = (ListNode) obj;
        ListNode p = this;
        while (p != null) {
            if (r == null) {
                return false;
            }

            if (p.val != r.val) {
                return false;
            }
            p = p.next;
            r = r.next;
        }

        if (r != null) {
            return false;
        }

        return true;
    }
}
