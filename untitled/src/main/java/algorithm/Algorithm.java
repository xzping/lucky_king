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
                left = Math.max(left, map.get(s.charAt(i)) + 1); // left不可以回退，所以取max
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
                    // 基于该点进行dfs
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

    // 根节点到叶子节点数字之和
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
                // 找到最深的层，进行求和
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

    // 不使用第二个队列，使用层序遍历计算根节点到叶子结点之和
    public int sumNumbers3(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int sum = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left == null && node.right == null) {
                    sum += node.val;
                }
                if (node.left != null) {
                    node.left.val = node.val * 10 + node.left.val;
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    node.right.val = node.val * 10 + node.right.val;
                    queue.offer(node.right);
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

    /**
     * 回文链表 中点反转比较法
     * 1->2->3->2->1 true
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        ListNode pre = new ListNode(-1);
        pre.next = head;
        ListNode fast = pre, slow = pre;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode tail = slow.next;
        slow.next = null;
        ListNode head1 = reverse(tail);
        while (head1 != null && head != null) {
            if (head1.val != head.val) return false;
            head1 = head1.next;
            head = head.next;
        }
        return true;
    }

    // 链表反转 p->q r
    public ListNode reverse(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode p = head;
        ListNode q = head.next;
        ListNode r = null;
        head.next = null;
        while (q != null) {
            r = q.next;
            q.next = p;
            p = q;
            q = r;
        }
        return p;
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

        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    /**
     * 验证回文串
     * 输入: s = "A man, a plan, a canal: Panama"
     * 输出: true
     * 解释："amanaplanacanalpanama" 是回文串
     *
     * @param s
     * @return boolean
     */
    public boolean isPalindrome(String s) {
        StringBuffer sgood = new StringBuffer();
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char ch = s.charAt(i);
            if (Character.isLetterOrDigit(ch)) {
                sgood.append(Character.toLowerCase(ch));
            }
        }
        int n = sgood.length();
        int left = 0, right = n - 1;
        while (left < right) {
            if (sgood.charAt(left) != sgood.charAt(right)) {
                return false;
            }
            ++left;
            --right;
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

    /**
     * 最大子数组和
     * <p>
     * dp[i]：表示以nums[i]结尾的连续子数组的最大和
     * dp[i] = dp[i-1] + nums[i] , dp[i-1]>0
     * dp[i] = nums[i], dp[i-1]<=0
     * dp[0] = nums[0]
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len];
        dp[0] = nums[0]; // 初始值

        for (int i = 1; i < len; i++) {
            if (dp[i - 1] > 0) {
                dp[i] = dp[i - 1] + nums[i];
            } else {
                dp[i] = nums[i];
            }
        }

        int max = dp[0];
        for (int i = 1; i < dp.length; i++) {
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    /**
     * 乘积最大子数组，记住前一个数组的最大和最小值，相比子数组和而言，需要记一个最小值，因为可能有反转，例如负数反转
     * dp[i] = max(nums[i]*pre_max, nums[i]*pre_min, nums[i])
     */
    public int maxProduct(int[] nums) {
        int preMax = nums[0];
        int preMin = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int curMax = Math.max(Math.max(nums[i] * preMax, nums[i] * preMin), nums[i]);
            int curMin = Math.min(Math.min(nums[i] * preMax, nums[i] * preMin), nums[i]);
            preMax = curMax;
            preMin = curMin;
            max = Math.max(max, curMax);
        }
        return max;
    }

    /**
     * 颜色分类 0 1 2
     * 双指针
     *
     * @param nums
     */
    public void sortColors(int[] nums) {
        if (nums == null || nums.length == 0) return;
        int p1 = 0;
        int p2 = nums.length - 1;
        for (int i = 0; i <= p2; ++i) {
            while (i <= p2 && nums[i] == 2) {
                int temp = nums[i];
                nums[i] = nums[p2];
                nums[p2] = temp;
                --p2;
            }
            if (nums[i] == 0) {
                int temp = nums[i];
                nums[i] = nums[p1];
                nums[p1] = temp;
                ++p1;
            }
        }
    }

    /**
     * 括号的生成
     * 深度优先遍历 - 做减法
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        if (n == 0) {
            return res;
        }
        dfs("", n, n, res);
        return res;
    }

    /**
     * @param curStr 当前递归得到的结果
     * @param left   左括号还有几个可以使用
     * @param right  右括号还有几个可以使用
     * @param res    结果集
     */
    private void dfs(String curStr, int left, int right, List<String> res) {
        // 因为每一次尝试，都使用新的字符串变量，所以无需回溯
        // 在递归终止的时候，直接把它添加到结果集即可
        if (left == 0 && right == 0) {
            res.add(curStr);
            return;
        }

        // 剪枝（如图，左括号可以使用的个数严格大于右括号可以使用的个数，才剪枝，注意这个细节）
        if (left > right) {
            return;
        }

        if (left > 0) {
            dfs(curStr + "(", left - 1, right, res);
        }

        if (right > 0) {
            dfs(curStr + ")", left, right - 1, res);
        }
    }

    /**
     * 括号的生成
     * 深度优先遍历 - 做加法
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis2(int n) {
        List<String> res = new ArrayList<>();
        if (n == 0) {
            return res;
        }
        dfs2("", 0, 0, n, res);
        return res;
    }

    /**
     * @param curStr 当前递归得到的结果
     * @param left   左括号已经用了几个
     * @param right  右括号已经用了几个
     * @param n      左括号、右括号一共得用几个
     * @param res    结果集
     */
    private void dfs2(String curStr, int left, int right, int n, List<String> res) {
        if (left == n && right == n) {
            res.add(curStr);
            return;
        }

        // 剪枝
        if (left < right) {
            return;
        }

        if (left < n) {
            dfs2(curStr + "(", left + 1, right, n, res);
        }
        if (right < n) {
            dfs2(curStr + ")", left, right + 1, n, res);
        }
    }

    /**
     * 和为k的子数组
     *
     * @param nums
     * @param k
     * @return
     */
    public int subArraySum(int[] nums, int k) {
        int res = 0;
        for (int j = 0; j < nums.length; j++) {
            int num = 0;
            for (int i = j; i >= 0; i--) {
                num += nums[i];
                if (num == k) {
                    res++;
                }
            }
        }
        return res;
    }

    /**
     * 最短无序子串
     *
     * @param nums
     * @return
     */
    public int findUnsortedSubArray(int[] nums) {
        if (nums == null || nums.length <= 1) return 0;
        // 先对nums排序
        int[] copy = nums.clone();
        Arrays.sort(copy);
        int i = 0;
        int j = nums.length - 1;
        while (i < j && nums[i] == copy[i]) {
            i++;
        }
        while (i < j && nums[j] == copy[j]) {
            j--;
        }
        if (i == j) return 0; // 整体有序
        return j - i + 1;
    }

    // 全排列 回溯算法
    public List<List<Integer>> permute(int[] nums) {
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }
        boolean[] used = new boolean[len];
        Deque<Integer> path = new ArrayDeque<>();
        dfs(nums, len, 0, used, path, res);
        return res;
    }

    private void dfs(int[] nums, int len, int depth, boolean[] used, Deque<Integer> path, List<List<Integer>> res) {
        if (depth == len) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < len; i++) {
            if (!used[i]) {
                path.addLast(nums[i]);
                used[i] = true;
                System.out.println("开始递归前 ==> " + path);
                dfs(nums, len, depth + 1, used, path, res);

                // 删除 回溯
                used[i] = false;
                path.removeLast();
                System.out.println("开始递归后 ==> " + path);
            }
        }
    }

    // 数组子集（找到数组的所有子集） 回溯算法
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> sub = new ArrayList<>();
        dfs(nums, 0, res, sub);
        return res;
    }

    private void dfs(int[] nums, int depth, List<List<Integer>> res, List<Integer> sub) {
        res.add(new ArrayList<>(sub));
        for (int i = depth; i < nums.length; i++) {
            sub.add(nums[i]);
            dfs(nums, i + 1, res, sub);
            sub.remove(sub.size() - 1);
        }
    }

    // 旋转90度的图像二维矩阵
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        int[][] newMatrix = new int[n][n];
        // fill
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 重点在找到这个公式
                newMatrix[j][n - i - 1] = matrix[i][j];
            }
        }
        // copy
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = newMatrix[i][j];
            }
        }
    }

    // 接雨水
    public int trap(int[] height) {
        int n = height.length;
        if (n == 0) {
            return 0;
        }
        int[] leftMax = new int[n];
        leftMax[0] = height[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }
        int[] rightMax = new int[n];
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans += Math.min(leftMax[i], rightMax[i]) - height[i];
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