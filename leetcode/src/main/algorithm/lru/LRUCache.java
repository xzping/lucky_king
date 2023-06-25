package algorithm.lru;

import java.util.HashMap;

public class LRUCache {
    private HashMap<Integer, Node> map; // 哈希表，key -> Node(key,val)
    private DoubleList cache; // 双向链表，Node1 <-> Node2 <-> Node3 ...
    private int cap; // 最大容量

    public LRUCache(int cap) {
        this.cap = cap;
        map = new HashMap<>();
        cache = new DoubleList();
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        // 将该节点提升为最近使用的
        makeRecently(key);
        return map.get(key).val;
    }

    public void put(int key, int val) {
        if (map.containsKey(key)) {
            // 删除旧数据
            deleteKey(key);
            // 新插入的数据为最近使用的数据
            addRecently(key, val);
            return;
        }

        if (cap == cache.size()) {
            // 删除最久未使用的元素
            removeLeastRecently();
        }
        // 添加为最近使用的元素
        addRecently(key, val);
    }

    // 将某个key提升为最近使用的
    private void makeRecently(int key) {
        Node x = map.get(key);
        // 先从链表里删除该节点
        cache.remove(x);

        // 重新插入队尾
        cache.addLast(x);
    }

    // 添加最近使用的元素
    private void addRecently(int key, int val) {
        Node x = new Node(key, val);
        // 链表尾部就是最近使用的元素
        cache.addLast(x);

        // 哈希表添加该节点的映射
        map.put(key, x);
    }

    private void deleteKey(int key) {
        Node x = map.get(key);
        cache.remove(x);
        map.remove(key);
    }

    // 删除最久未使用的元素
    private void removeLeastRecently() {
        // 链表头部的第一个元素就是最久未使用的
        Node deletedNode = cache.removeFirst();

        // 哈希表移除该节点的映射
        int deletedKey = deletedNode.key;
        map.remove(deletedKey);
    }
}
