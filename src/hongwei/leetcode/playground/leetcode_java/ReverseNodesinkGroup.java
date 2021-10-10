package hongwei.leetcode.playground.leetcode_java;

import hongwei.leetcode.playground.common.LogJava;
import hongwei.leetcode.playground.common.ListNode;
import hongwei.leetcode.playground.IQuestion;

/**
 * Package: com.jd.aibdp.algorithm
 * User: baihongwei1
 * Email: baihongwei1@jd.com
 * Date: 2019/2/15
 * Time: 17:35
 * Description:
 */
public class ReverseNodesinkGroup implements IQuestion {
    @Override
    public void run() {
        LogJava.i("aaaa", "------- run -------");
        ListNode[] input = new ListNode[]{
//                new ListNode(new int[]{1, 2, 3, 4, 5}),
                new ListNode(new int[]{1, 2, 3, 4, 5, 6, 7}),
//                new ListNode(new int[]{1, 2, 3, 4, 5}),
        };
        int[] input2 = new int[]{2, 3, 3};
        ListNode[] ref = new ListNode[]{
                new ListNode(new int[]{2, 1, 4, 3, 5}),
                new ListNode(new int[]{3, 2, 1, 6, 5, 4, 7}),
                new ListNode(new int[]{3, 2, 1, 4, 5}),
        };

        for (int i = 0; i < input.length; i++) {
            ListNode result = reverseKGroup(input[i], input2[i]);
            if (result.equals(ref[i])) {
                LogJava.i("aaaa", "case[" + i + "] passed");
            } else {
                LogJava.e("aaaa", "!!!!![WRONG]case[" + i + "] failed, result: " + result);
            }
        }
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode ans = head;
        int length = getLength(head);
        int lenBy3 = length / 3;
        ListNode[] subHeaders = new ListNode[lenBy3];
        ListNode[] subTails = new ListNode[lenBy3];

        if (head == null || length <= 1 || k > length) {
            return head;
        }

        ListNode p = head;
        int i = 0;
        while (p != null) {
            if (i % k == 0) {
                subHeaders[i / 3] = p;
            } else if (i % k == k - 1) {
                subTails[i / 3] = p;
            }
            p = p.next;
            i++;
        }

//        ListNode[] ret = reverse(head, null, 0, k - 1);
//        ListNode[] ret = reverse(head, null, 3, 5);
//        Log.i("aaaa", "ret0:" + (ret[0] != null ? ret[0].val : ret[0]));
//        Log.i("aaaa", "ret1:" + (ret[1] != null ? ret[1].val : ret[1]));
//        Log.i("aaaa", "ret2:" + (ret[2] != null ? ret[2].val : ret[2]));

//        ans = ret[0];
//        for (int i = k; i < length; i += k) {
//            ListNode newHead = ret[1];
//            ListNode nodeBeforeNewHead = ret[2];
//            ret = reverse(newHead, nodeBeforeNewHead, 0, k - 1);
//        }
//        return ans;
        return null;
    }

    int getLength(ListNode head) {
        int i = 0;
        ListNode p = head;
        while (p.next != null) {
            i++;
            p = p.next;
        }
        return i;
    }

    /*
     return array:
        [0] head: only useful for 1st section reverse, as final answer
        [1] tail
      */
    ListNode[] reverse(ListNode head, int from, int to) {
        LogJava.i("aaaa", "call reverse [" + from + ", " + to + "], list: " + head.toString());
        ListNode pFront = null;
        ListNode p = head;
        ListNode pBack = null;
        ListNode pLastAfterReverse = head;

        int i = 0;

        // keep
        while (p != null) {
            // loop
            pBack = p.next;

            // logic
            p.next = pFront;

            // loop
            pFront = p;
            p = pBack;
            if (i == to) {
                break;
            }
            i++;
        }
        return new ListNode[]{head, p, pLastAfterReverse};
    }
}
