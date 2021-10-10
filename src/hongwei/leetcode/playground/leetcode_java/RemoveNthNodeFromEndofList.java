package hongwei.leetcode.playground.leetcode_java;

import hongwei.leetcode.playground.common.LogJava;
import hongwei.leetcode.playground.common.ListNode;
import hongwei.leetcode.playground.IQuestion;

/**
 * Package: com.jd.aibdp.algorithm
 * User: baihongwei1
 * Email: baihongwei1@jd.com
 * Date: 2019/2/1
 * Time: 16:38
 * Description:
 */
public class RemoveNthNodeFromEndofList implements IQuestion {
    @Override
    public void run() {
        //1->2->3->4->5
        ListNode input = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        int node = 2;

//        ListNode ref = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(5))));
//        ListNode ref = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(5))));
        ListNode ref = new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5))));

        for (int i = 0; i < 1; i++) {
            ListNode result = removeNthFromEnd(input, node);
            if (result != null && ref.equals(result)) {
                LogJava.i("aaaa", "case[" + i + "] passed");
            } else {
                LogJava.e("aaaa", "!!!!![WRONG]case[" + i + "] failed, result: " + result);
            }
        }
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head.next == null && n == 1) {
            return null;
        }

        int length = 0;
        ListNode p = head;
        ListNode last2 = p;
        ListNode last = p;
        while (p != null) {
            length++;
            if (n == 1) {
                last2 = last;
                last = p;
            }
            p = p.next;
        }

        //rmv tail
        if (n == 1) {
            last2.next = null;
            return head;
        }

        int nodeFromHead = length - n;

        // if rmv head, just return head->next
        if (nodeFromHead == 0) {
            return head.next;
        }

        // rmv mid
        p = head;
        for (int i = 1; i < length; i++) {
            if (i == nodeFromHead) {
                p.next = p.next.next;
                break;
            }
            p = p.next;
        }

        return head;
    }
}
