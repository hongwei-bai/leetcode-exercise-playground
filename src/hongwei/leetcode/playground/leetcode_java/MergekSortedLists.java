package hongwei.leetcode.playground.leetcode_java;

import hongwei.leetcode.playground.common.Log;
import hongwei.leetcode.playground.common.ListNode;
import hongwei.leetcode.playground.IQuestion;

/**
 * Package: com.jd.aibdp.algorithm
 * User: baihongwei1
 * Email: baihongwei1@jd.com
 * Date: 2019/2/15
 * Time: 16:11
 * Description:
 */
public class MergekSortedLists implements IQuestion {
    @Override
    public void run() {
        Log.i("aaaa", "------- run -------");
        ListNode[][] input = new ListNode[][]{
                {new ListNode(new int[]{1, 4, 5}), new ListNode(new int[]{1, 3, 4}), new ListNode(new int[]{2, 6})},
                //[[-10,-9,-9,-3,-1,-1,0],[-5],[4],[-8],[],[-9,-6,-5,-4,-2,2,3],[-3,-3,-2,-1,0]]
                {new ListNode(new int[]{-10, -9, -9, -3, -1, -1, 0}), new ListNode(new int[]{-5}), new ListNode(new int[]{4}),
                        new ListNode(new int[]{-8}), null, new ListNode(new int[]{-9, -6, -5, -4, -2, 2, 3}), new ListNode(new int[]{-3, -3, -2, -1, 0})}
        };
        ListNode[] ref = new ListNode[]{
                new ListNode(new int[]{1, 1, 2, 3, 4, 4, 5, 6}),
                new ListNode(new int[]{1, 1, 2, 3, 4, 4, 5, 6}),
        };

        for (int i = 0; i < input.length; i++) {
            ListNode result = mergeKLists(input[i]);
            if (result.equals(ref[i])) {
                Log.i("aaaa", "case[" + i + "] passed");
            } else {
                Log.e("aaaa", "!!!!![WRONG]case[" + i + "] failed, result: " + result);
            }
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        return mergeRecursive(lists, 0, lists.length - 1);
    }

    private ListNode mergeRecursive(ListNode[] lists, int from, int to) {
        Log.i("aaaa", "recur: " + from + "  -> " + to);
        if (from >= to) {
            return lists[from];
        }

        if (from == to - 1) {
            return mergeTwo(lists[from], lists[to]);
        }

        int mid = (to + from) / 2;
        return mergeTwo(mergeRecursive(lists, from, mid), mergeRecursive(lists, mid + 1, to));
    }

    public ListNode mergeKLists2(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        ListNode node = lists[0];

        for (int i = 1; i < lists.length; i++) {
            node = mergeTwo(node, lists[i]);
        }

        return node;
    }

    private ListNode mergeTwo(ListNode n1, ListNode n2) {
        if (n1 == null) {
            return n2;
        }

        if (n2 == null) {
            return n1;
        }

        ListNode head = null;
        if (n1.val < n2.val) {
            head = new ListNode(n1.val);
            n1 = n1.next;
        } else {
            head = new ListNode(n2.val);
            n2 = n2.next;
        }

        ListNode r = head;
        while (n1 != null && n2 != null) {
            if (n1.val < n2.val) {
                r.next = new ListNode(n1.val);
                n1 = n1.next;
            } else {
                r.next = new ListNode(n2.val);
                n2 = n2.next;
            }
            r = r.next;
        }

        if (n1 != null) {
            r.next = n1;
        } else {
            r.next = n2;
        }
        return head;
    }
}
