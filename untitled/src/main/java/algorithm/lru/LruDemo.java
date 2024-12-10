package algorithm.lru;

public class LruDemo {
    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 2); // [(1,2)]
        cache.put(2, 3); // [(2,3) -> (1,2)]
        System.out.println(cache.get(1)); // [(1,2) -> (2,3)]
        cache.put(3, 4); // [(3,4) -> (1,2)]
        System.out.println(cache.get(2)); // -1
    }
}
