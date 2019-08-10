package hongwei.leetcode.playground.leetcode

class _24SwapNodesInPairs {
    fun test() {

    }

    fun swapPairs(head: ListNode?): ListNode? {
        var nodeBefore: ListNode? = null
        var nodeAfter: ListNode? = null
        var nodeLeft: ListNode? = null
        var nodeRight: ListNode? = null

        var node = head

        if (null == head?.next) return head

        val newHead = head.next

        while (node != null) {
            if (node.next == null) {
                break
            }

            nodeLeft = node
            nodeRight = node.next
            nodeAfter = nodeRight?.next

            nodeBefore?.next = nodeRight
            nodeRight?.next = nodeLeft
            nodeLeft.next = nodeAfter

            // for next sprint
            nodeBefore = nodeLeft
            node = nodeAfter
        }

        return newHead
    }

    // Definition for singly-linked list.
    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }
}