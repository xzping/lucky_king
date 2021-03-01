package main.algorithm;


import java.util.HashMap;
import java.util.Map;

public class Algorithm {
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
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
    }
}