package algorithm.lru;

public class DoubleList {
    private Node head, tail; // 头尾虚节点
    private int size; // 链表元素数量

    public DoubleList() {
        // 初始化双向链表的数据
        head = new Node(0, 0);
        head = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    // 在链表尾部添加节点x，时间复杂度O(1)
    public void addLast(Node x) {
        x.prev = tail.prev;
        x.next = tail;
        tail.prev.next = x;
        tail.prev = x;
        size++;
    }

    // 删除链表中的x节点（x一定存在）
    // 由于是双向链表且给的是目标Node节点，时间复杂度O(1)
    public void remove(Node x) {
        x.prev.next = x.next;
        x.next.prev = x.prev;
        size--;
    }

    // 删除链表中第一个节点，并返回该节点，时间复杂度O(1)
    public Node removeFirst() {
        if (head.next == null) {
            return null;
        }
        Node first = head.next;
        remove(first);
        return first;
    }

    // 返回链表长度
    public int size() {
        return size;
    }
}
