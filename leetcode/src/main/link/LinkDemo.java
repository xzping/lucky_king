package link;

public class LinkDemo {
    // 三节点

    /**
     * p -> q  r -> x
     * p <- q r -> x
     * ok <- p -> q r -> x
     */
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode p = head;
        ListNode q = head.next;
        head.next = null;
        ListNode r = null;
        while (q != null) {
            r = q.next;
            q.next = p;
            p = q;
            q = r;
        }
        head = p;
        return head;
    }

    // 双节点

    /**
     * 1 -> 2 -> 3 -> NULL
     * NULL <- 1 <- 2 <- 3
     */
    public ListNode reverseList2(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    // 递归

    /**
     * 原始链表
     * n1 -> n2 -> n3 -> ... -> nk-1 -> nk -> nk+1 -> ... -> nm
     * 假设已经反转了一部分了，当前处于nk
     * n1 -> n2 -> n3 -> ... -> nk-1 -> nk -> nk+1 <- ... <- nm
     * <p>
     * 所以我们希望nk+1的下一个节点指向nk
     * 即，nk.next.next = nk
     * <p>
     * 同时，还得保证n1的下一个节点指向null，否则会有环
     */
    public ListNode reverseList3(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode newHead = reverseList3(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    // 逐个反转
    public ListNode reverseList4(ListNode head) {
        ListNode ans = null;
        for (ListNode i = head; i != null; i = i.next) {
            ans = new ListNode(i.val, ans);
        }
        return ans;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
