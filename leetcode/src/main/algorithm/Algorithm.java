package algorithm;

import java.util.*;

public class Algorithm {
    // 两数之和
    private int[] twoNum(int[] nums, int target) {
        Map<Integer, Integer> hashtable = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (hashtable.containsKey(target - nums[i])) {
                return new int[]{hashtable.get(target - nums[i]), i};
            }
            hashtable.put(nums[i], i);
        }
        return new int[0];
    }

    // 两整数之和
    public int getSum(int a, int b) {
        while (b != 0) {
            int carry = (a & b) << 1;
            a = a ^ b;
            b = carry;
        }
        return a;
    }

    // 递归
    public int getSum2(int a, int b) {
        // a^b 无进位的加法
        // (a&b)<<1 进位
        return (b == 0) ? a : getSum2(a ^ b, (a & b) << 1);
    }

    // 无重复字符的最长子串
    private int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) {
            return 0;
        }
        int max = 0;
        int left = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            /**
             1、首先，判断当前字符是否包含在map中，如果不包含，将该字符添加到map（字符，字符在数组下标）,
             此时没有出现重复的字符，左指针不需要变化。此时不重复子串的长度为：i-left+1，与原来的maxLen比较，取最大值；

             2、如果当前字符 ch 包含在 map中，此时有2类情况：
             1）当前字符包含在当前有效的子段中，如：abca，当我们遍历到第二个a，当前有效最长子段是 abc，我们又遍历到a，
             那么此时更新 left 为 map.get(a)+1=1，当前有效子段更新为 bca；
             2）当前字符不包含在当前最长有效子段中，如：abba，我们先添加a,b进map，此时left=0，我们再添加b，发现map中包含b，
             而且b包含在最长有效子段中，就是1）的情况，我们更新 left=map.get(b)+1=2，此时子段更新为 b，而且map中仍然包含a，map.get(a)=0；
             随后，我们遍历到a，发现a包含在map中，且map.get(a)=0，如果我们像1）一样处理，就会发现 left=map.get(a)+1=1，实际上，left此时
             应该不变，left始终为2，子段变成 ba才对。

             为了处理以上2类情况，我们每次更新left，left=Math.max(left , map.get(ch)+1).
             另外，更新left后，不管原来的 s.charAt(i) 是否在最长子段中，我们都要将 s.charAt(i) 的位置更新为当前的i，
             因此此时新的 s.charAt(i) 已经进入到 当前最长的子段中！
             */
            if (map.containsKey(s.charAt(i))) {
                left = Math.max(left, map.get(s.charAt(i)) + 1); // 这一步是为什么？计算左边的窗口位置？
            }
            map.put(s.charAt(i), i);
            max = Math.max(max, i - left + 1); // i-left+1是窗口大小
        }
        return max;
    }

    // 盛最多水的容器
    private int maxArea(int[] height) {
        int maxArea = 0;
        int i = 0;
        int j = height.length - 1;
        while (i <= j) {
            maxArea = Math.max(maxArea, Math.min(height[i], height[j]) * (j - i));
            if (height[i] > height[j]) {
                j--;
            } else {
                i++;
            }
        }
        return maxArea;
    }

    // 最长公共前缀
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        String ans = strs[0];
        for (int i = 1; i < strs.length; i++) {
            int j = 0;
            while (j < ans.length() && j < strs[i].length() && ans.charAt(j) == strs[i].charAt(j)) {
                j++;
            }
            ans = strs[i].substring(0, j);
        }
        return ans;
    }

    // 链表反转，p->q->r
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

    // 二分查找
    public int binarySearch(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] < target) {
                high = mid - 1;
            } else if (nums[mid] > target) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public int bSearch(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        return bSearch(nums, target, 0, nums.length - 1);
    }

    private int bSearch(int[] nums, int target, int low, int high) {
        if (low > high) return -1;
        int mid = (low + high) / 2;
        if (nums[mid] < target) {
            return bSearch(nums, target, low, mid - 1);
        } else if (nums[mid] > target) {
            return bSearch(nums, target, mid + 1, high);
        } else {
            return mid;
        }
    }

    // 最大的岛屿面积-dfs
    private int curArea;
    private int res = 0;

    public int maxAreaOfIsland(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                // 一个一个点进行寻找最大区域
                if (grid[i][j] == 1) {
                    curArea = 0;
                    // 基于改点进行dfs
                    maxArea(i, j, grid);
                    res = Math.max(res, curArea);
                }
            }
        }
        return res;
    }

    private void maxArea(int i, int j, int[][] grid) {
        if (i != -1 && i != grid.length && j != -1 && j != grid[i].length && grid[i][j] == 1) {
            grid[i][j] = -1; // 标记下
            curArea++;
            // 继续四周递归寻找
            maxArea(i - 1, j, grid);
            maxArea(i + 1, j, grid);
            maxArea(i, j - 1, grid);
            maxArea(i, j + 1, grid);
        }
    }

    // 深度优先遍历
    public int sumNumbers(TreeNode root) {
        return dfs(root, 0);
    }

    private int dfs(TreeNode root, int prevSum) {
        if (root == null) return 0;
        int sum = prevSum * 10 + root.val;
        if (root.left == null && root.right == null) {
            return sum;
        } else {
            return dfs(root.left, sum) + dfs(root.right, sum);
        }
    }

    // 广度优先遍历
    public int sumNumbers2(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> numQueue = new LinkedList<>();
        queue.offer(root);
        numQueue.offer(root.val);
        int sum = 0;
        while (!queue.isEmpty()) {
            TreeNode tmp = queue.poll();
            int num = numQueue.poll();
            if (tmp.left == null && tmp.right == null) {
                sum += num;
            } else {
                if (tmp.left != null) {
                    queue.offer(tmp.left);
                    numQueue.offer(num * 10 + tmp.left.val);
                }
                if (tmp.right != null) {
                    queue.offer(tmp.right);
                    numQueue.offer(num * 10 + tmp.right.val);
                }
            }
        }
        return sum;
    }

    // 锯齿形层序遍历
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        List<List<Integer>> tmpList = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean flag = true;
        // 正常的层序遍历
        while (!queue.isEmpty()) {
            int l = queue.size();
            List<Integer> inner = new ArrayList<>();
            for (int i = 0; i < l; i++) {
                TreeNode tmp = queue.poll();
                inner.add(tmp.val);
                if (tmp.left != null) {
                    queue.offer(tmp.left);
                }
                if (tmp.right != null) {
                    queue.offer(tmp.right);
                }
            }
            tmpList.add(inner);
        }

        // 对层序遍历结果进行隔层反转
        for (List<Integer> l : tmpList) {
            if (flag) {
                res.add(l);
                flag = false;
            } else {
                Collections.reverse(l);
                res.add(l);
                flag = true;
            }
        }
        return res;
    }

    // 锯齿形层序遍历
    public List<List<Integer>> zigzagLevelOrder2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean flag = true;
        while (!queue.isEmpty()) {
            Deque<Integer> deque = new LinkedList<>();
            int l = queue.size();
            for (int i = 0; i < l; i++) {
                TreeNode tmp = queue.poll();
                if (!flag) {
                    deque.offerFirst(tmp.val);
                } else {
                    deque.offerLast(tmp.val);
                }
                if (tmp.left != null) {
                    queue.offer(tmp.left);
                }
                if (tmp.right != null) {
                    queue.offer(tmp.right);
                }
            }
            res.add(new ArrayList<>(deque));
            flag = !flag;
        }
        return res;
    }

    // 搜索旋转排序数组（旋转二分查找） 4 5 6 7 1 2  ||  6 7 1 2 3 4
    public int search(int[] arr, int target) {
        if (arr == null || arr.length == 0) return -1;
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = (high - low) / 2 + low;
            if (target == arr[mid]) {
                return mid;
            }
            // 4 5 6 7 1 2
            if (arr[mid] >= arr[low]) {
                // 左边
                if (target < arr[mid] && target >= arr[low]) {
                    high = mid - 1;
                } else {
                    // 右边
                    low = mid + 1;
                }
            } else {
                // 6 7 1 2 3 4
                // 右边
                if (target > arr[mid] && target <= arr[high]) {
                    low = mid + 1;
                } else {
                    // 左边
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

    // 合并2个非递减的数组
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1, p2 = n - 1;
        int tail = m + n - 1;
        int cur;
        while (p1 >= 0 || p2 >= 0) {
            if (p1 == -1) {
                cur = nums2[p2--];
            } else if (p2 == -1) {
                cur = nums1[p1--];
            } else if (nums1[p1] > nums2[p2]) {
                cur = nums1[p1--];
            } else {
                cur = nums2[p2--];
            }
            nums1[tail--] = cur;
        }
    }

    // 复原IP地址
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        if (s == null || s.length() == 0) return res;
        for (int a = 1; a < 4; a++) {
            for (int b = 1; b < 4; b++) {
                for (int c = 1; c < 4; c++) {
                    for (int d = 1; d < 4; d++) {
                        if ((a + b + c + d) == s.length()) {
                            int n1 = Integer.parseInt(s.substring(0, a));
                            int n2 = Integer.parseInt(s.substring(a, a + b));
                            int n3 = Integer.parseInt(s.substring(a + b, a + b + c));
                            int n4 = Integer.parseInt(s.substring(a + b + c, a + b + c + d));
                            if (n1 <= 255 && n2 <= 255 && n3 <= 255 && n4 <= 255) {
                                String r = n1 + "." + n2 + "." + n3 + "." + n4;
                                if (r.length() == s.length() + 3) { // 避免出现01被优化为1的情况
                                    res.add(r);
                                }
                            }
                        }
                    }
                }
            }
        }
        return res;
    }

    // 基于小顶堆实现topK
    // 分而治之 + 小顶堆
    public int[] topKFrequent(int[] nums, int k) {
        // 先遍历数组，然后记录元素的频数集合
        Map<Integer, Integer> occurrences = new HashMap<>();
        for (int num : nums) {
            occurrences.put(num, occurrences.getOrDefault(num, 0) + 1);
        }

        // 优先队列PriorityQueue是基于堆实现的队列（完全二叉树）
        // int[] 的第一个元素代表数组的值，第二个元素代表了该值出现的次数
        PriorityQueue<int[]> queue = new PriorityQueue<int[]>(new Comparator<int[]>() {
            public int compare(int[] m, int[] n) {
                return m[1] - n[1];
            }
        });
        for (Map.Entry<Integer, Integer> entry : occurrences.entrySet()) {
            int num = entry.getKey(), count = entry.getValue();
            if (queue.size() == k) {
                if (queue.peek()[1] < count) {
                    queue.poll();
                    queue.offer(new int[]{num, count});
                }
            } else {
                queue.offer(new int[]{num, count});
            }
        }
        int[] ret = new int[k];
        for (int i = 0; i < k; ++i) {
            ret[i] = queue.poll()[0];
        }
        return ret;
    }

    // 前K个高频单词
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        PriorityQueue<TopKNode> queue = new PriorityQueue<>(new Comparator<TopKNode>() {
            @Override
            public int compare(TopKNode o1, TopKNode o2) {
                return o1.count - o2.count;
            }
        });

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String word = entry.getKey();
            int count = entry.getValue();
            if (queue.size() == k) {
                if (queue.peek().count < count) {
                    queue.poll();
                    queue.offer(new TopKNode(word, count));
                }
            } else {
                queue.offer(new TopKNode(word, count));
            }
        }

        List<String> ret = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            ret.add(queue.poll().word);
        }
        return ret;
    }

    // 连续字符
    public int maxPower(String s) {
        int ans = 1, cnt = 1;
        for (int i = 1; i < s.length(); ++i) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                ++cnt;
                ans = Math.max(ans, cnt);
            } else {
                cnt = 1;
            }
        }
        return ans;
    }

    // 删除有序数组中的重复项
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        int index = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                index++;
            }
            nums[index] = nums[i];
        }
        return index + 1;
    }

    // 删除有序数组中的重复项（双指针实现）
    public int removeDuplicates2(int[] nums) {
        if (nums.length == 0) return 0;
        int slow = 1, fast = 1;
        int len = nums.length;
        while (fast < len) {
            if (nums[fast] != nums[fast - 1]) {
                nums[slow] = nums[fast];
                ++slow;
            }
            ++fast;
        }
        return slow;
    }

    // 链表是否有环
    public boolean hasCycle(ListNode head) {
        Set<ListNode> nodes = new HashSet<>();
        while (head != null) {
            if (nodes.contains(head)) {
                return true;
            }
            nodes.add(head);
            head = head.next;
        }
        return false;
    }

    // 链表是否有环，快慢指针(有环的话，快慢指针终会相遇)
    public boolean hasCycle2(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        ListNode fast = head.next.next;
        ListNode slow = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }

    // 是否为回文数
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int cur = 0;
        int num = x;
        while (num != 0) {
            cur = cur * 10 + num % 10;
            num /= 10;
        }
        return cur == x;
    }

    // 是否为回文串
    public boolean isPalindromeString(String s) {
        if (s.length() == 0) {
            return false;
        }

        if (s.length() == 1) {
            return true;
        }

        int left = 0;
        int right = s.length() - 1;
        while (left <= right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            } else {
                left++;
                right--;
            }
        }
        return true;
    }

    /**
     * 回文子串，dp问题
     * 给你一个字符串 s ，请你统计并返回这个字符串中 回文子串 的数目。
     * <p>
     * P(i, j) = P(i+1, j-1)&(Si==Sj) // 长度大于3
     * P(i, i) = true // 长度为1
     * P(i, i+1) = (Si == Si+1) // 长度为2
     */
    public int countSubstrings(String s) {
        int len = s.length();
        if (len <= 1) {
            return len;
        }

        int count = 0;

        // dp[i][j]表示s[i...j]是否为回文串
        boolean[][] dp = new boolean[len][len];

        // 初始化，所有长度为1的子串都是回文串
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
            count++;
        }

        // 递归开始
        // 先枚举子串长度
        char[] charArray = s.toCharArray();
        for (int i = 2; i <= len; i++) {
            for (int l = 0; l < len; l++) {
                int r = l + i - 1;
                if (r >= len) {
                    break;
                }
                if (charArray[l] != charArray[r]) {
                    dp[l][r] = false;
                } else {
                    if (r - l < 3) {
                        dp[l][r] = true;
                    } else {
                        dp[l][r] = dp[l + 1][r - 1];
                    }
                }

                if (dp[l][r]) {
                    count++;
                }
            }
        }

        return count;
    }

    /**
     * 最长回文子串，dp问题
     * <p>
     * P(i, j) = P(i+1, j-1)&(Si==Sj) // 长度大于3
     * P(i, i) = true // 长度为1
     * P(i, i+1) = (Si == Si+1) // 长度为2
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        int len = s.length();
        if (len <= 1) {
            return s;
        }

        int maxLen = 1;
        int begin = 0;

        // dp[i][j]表示s[i...j]是否为回文串
        boolean[][] dp = new boolean[len][len];

        // 初始化，所有长度为1的子串都是回文串
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }

        char[] charArray = s.toCharArray();

        // 递归开始
        // 先枚举子串长度/窗口，长度先从2开始（1的已经初始化过了）
        for (int window = 2; window <= len; window++) {
            // 枚举左边界，左边界的上限设置可以宽松一些
            for (int l = 0; l < len; l++) {
                // 由i和j可以确定右边界，即r-l+1=LENGTH
                int r = window + l - 1; // 右边界
                if (r >= len) {
                    break; // 越界退出
                }

                if (charArray[l] != charArray[r]) {
                    dp[l][r] = false;
                } else {
                    if (r - l < 3) {
                        dp[l][r] = true; // 长度为2
                    } else {
                        dp[l][r] = dp[l + 1][r - 1];
                    }
                }

                if (dp[l][r] && window > maxLen) {
                    maxLen = window;
                    begin = l;
                }
            }
        }

        return s.substring(begin, begin + maxLen);
    }

    // 只出现一次的数字，其余均出现2次 -> 异或实现
    public int singleNumber(int[] nums) {
        int ret = 0;
        for (int num : nums) {
            ret ^= num;
        }
        return ret;
    }

    // 链表中的两数相加
    public ListNode addTwoListNumbers(ListNode l1, ListNode l2) {
        Deque<Integer> stack1 = new ArrayDeque<Integer>();
        Deque<Integer> stack2 = new ArrayDeque<Integer>();
        while (l1 != null) {
            stack1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            stack2.push(l2.val);
            l2 = l2.next;
        }
        int carry = 0;
        ListNode ans = null;
        while (!stack1.isEmpty() || !stack2.isEmpty() || carry != 0) {
            int a = stack1.isEmpty() ? 0 : stack1.pop();
            int b = stack2.isEmpty() ? 0 : stack2.pop();
            int cur = a + b + carry;
            carry = cur / 10;
            cur %= 10;
            ListNode curnode = new ListNode(cur);
            curnode.next = ans;
            ans = curnode;
        }
        return ans;
    }

    public static void main(String[] args) {
        String s = "abcabcbb";
        Algorithm algorithm = new Algorithm();
        System.out.println(algorithm.lengthOfLongestSubstring(s));

        System.out.println(algorithm.getSum2(2, 3));

        String ip = "25525511135";
        System.out.println(algorithm.restoreIpAddresses(ip));

        String s2 = "abbcccddddeeeeedcba";
        System.out.println(algorithm.maxPower(s2));

        System.out.println(algorithm.isPalindromeString("abaa"));
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    // construction...
}

class TopKNode {
    String word;
    int count;

    TopKNode(String word, int count) {
        this.word = word;
        this.count = count;
    }
}