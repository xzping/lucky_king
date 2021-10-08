package link;

import java.util.List;

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

    // 合并2个有序链表
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }

    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        ListNode preHead = new ListNode(-1); // 牵引

        ListNode prev = preHead;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }

        // 如果其中有一个为null了，则拼接
        prev.next = (l1 == null) ? l2 : l1;
        return preHead.next;
    }

    // 两两交换链表中的节点
    public ListNode swapPairs(ListNode head) {
        // head -> newHead  =>  head <- newHead
        // head.next = 下一批newHead的交换结果
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = head.next;
        head.next = swapPairs(newHead.next);
        newHead.next = head;
        return newHead;
    }

    // 分隔链表

    /**
     * 直观来说我们只需维护两个链表small和large即可，small链表按顺序存储所有小于 xx 的节点，large链表按顺序存储所有大于等于xx的节点。遍历完原链表后，我们只要将small链表尾节点指向large链表的头节点即能完成对链表的分隔。
     *
     * 为了实现上述思路，我们设smallHead和largeHead分别为两个链表的哑节点，即它们的next指针指向链表的头节点，这样做的目的是为了更方便地处理头节点为空的边界条件。同时设small和large节点指向当前链表的末尾节点。
     * 开始时smallHead = small，largeHead = large。随后，从前往后遍历链表，判断当前链表的节点值是否小于 xx，如果小于就将small的next指针指向该节点，否则将large的next指针指向该节点。
     *
     * 遍历结束后，我们将large的next指针置空，这是因为当前节点复用的是原链表的节点，而其next指针可能指向一个小于xx的节点，我们需要切断这个引用。同时将small的next指针指向largeHead的next指针指向的节点，即真正意义上的large链表的头节点。最后返回smallHead的next 指针即为我们要求的答案。
     *
     * @param head
     * @param x
     * @return
     */
    public ListNode partition(ListNode head, int x) {
        ListNode small = new ListNode(-1);
        ListNode smallHead = small;
        ListNode large = new ListNode(-1);
        ListNode largeHead = large;

        while (head != null) {
            if (head.val < x) {
                small.next = head;
                small = small.next;
            } else {
                large.next = head;
                large = large.next;
            }
            head = head.next;
        }

        large.next = null;
        small.next = largeHead.next;
        return smallHead.next;
    }

    // 是否为回文链表
    public boolean isPalindrome(ListNode head) {
        return false;
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
