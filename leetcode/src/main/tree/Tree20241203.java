package tree;

import java.util.*;

/**
 * 二叉树遍历问题
 */
public class Tree20241203 {
    // 前序 pre-order
    public void preOrder(TreeNode20241203 root) {
        if (root == null) {
            return;
        }
        System.out.println(root.val);
        preOrder(root.left);
        preOrder(root.right);
    }

    public List<Integer> preOrderTraversal(TreeNode20241203 root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Stack<TreeNode20241203> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode20241203 tmp = stack.pop();
            res.add(tmp.val);
            if (tmp.right != null) {
                stack.push(tmp.right);
            }
            if (tmp.left != null) {
                stack.push(tmp.left);
            }
        }
        return res;
    }

    public List<Integer> preOrderTraversal2(TreeNode20241203 root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Stack<TreeNode20241203> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                res.add(root.val);
                root = root.left;
            }
            root = stack.pop();
            root = root.right;
        }
        return res;
    }

    // 中序 mid-order
    public void midOrder(TreeNode20241203 root) {
        if (root == null) {
            return;
        }
        midOrder(root.left);
        System.out.println(root.val);
        midOrder(root.right);
    }

    public List<Integer> midOrderTraversal(TreeNode20241203 root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Stack<TreeNode20241203> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            res.add(root.val);
            root = root.right;
        }
        return res;
    }

    // 后序 post-order
    public void postOrder(TreeNode20241203 root) {
        if (root == null) {
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        System.out.println(root.val);
    }

    public List<Integer> postOrderTraversal(TreeNode20241203 root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        // left right root
        Stack<TreeNode20241203> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode20241203 tmp = stack.pop();
            res.add(tmp.val);
            // reverse root right left
            if (root.left != null) {
                stack.push(root.left);
            }
            if (root.right != null) {
                stack.push(root.right);
            }
        }
        Collections.reverse(res);
        return res;
    }

    // left right root
    public List<Integer> postOrderTraversal2(TreeNode20241203 root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Stack<TreeNode20241203> stack = new Stack<>();
        // reverse root right left
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                res.add(root.val);
                stack.push(root);
                root = root.right;
            }
            root = stack.pop();
            root = root.left;
        }
        return res;
    }

    // 广度优先 bfs
    public List<List<Integer>> levelOrder(TreeNode20241203 root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode20241203> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int count = queue.size();
            List<Integer> inner = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                TreeNode20241203 node = queue.poll();
                inner.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            res.add(inner);
        }
        return res;
    }

    // 深度 depth
    // 最大深度
    public int maxDepthBfs(TreeNode20241203 root) {
        int depth = 0;
        if (root == null) {
            return depth;
        }
        Queue<TreeNode20241203> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            depth++;
            for (int i = 0; i < size; i++) {
                TreeNode20241203 node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return depth;
    }

    public int maxDepthDfs(TreeNode20241203 root) {
        if (root == null) {
            return 0;
        }
        int left = maxDepthDfs(root.left);
        int right = maxDepthDfs(root.right);
        return Math.max(left, right) + 1;
    }

    // 最小深度
    public int minDepthBfs(TreeNode20241203 root) {
        int depth = 0;
        if (root == null) {
            return depth;
        }
        Queue<TreeNode20241203> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            depth++;
            for (int i = 0; i < size; i++) {
                TreeNode20241203 node = queue.poll();
                if (node.left == null && node.right == null) {
                    return depth;
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return depth;
    }

    public int minDepthDfs(TreeNode20241203 root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null) {
            return minDepthDfs(root.right) + 1;
        }
        if (root.right == null) {
            return minDepthDfs(root.left) + 1;
        }
        int left = minDepthDfs(root.left);
        int right = minDepthDfs(root.right);
        return Math.min(left, right) + 1;
    }

    // 二叉树右视图

    // 平衡二叉树
    public boolean isBalanced(TreeNode20241203 root) {
        if (root == null) {
            return true;
        }
        int left = maxDepthDfs(root.left);
        int right = maxDepthDfs(root.right);
        return Math.abs(left - right) < 2 && isBalanced(root.left) && isBalanced(root.right);
    }

    // 对称二叉树
    public boolean isSymmetric(TreeNode20241203 root) {
        if (root == null || (root.left == null && root.right == null)) {
            return true;
        }
        return isMirror(root.left, root.right);
    }

    private boolean isMirror(TreeNode20241203 p, TreeNode20241203 q) {
        if (p == null && q == null) {
            return true;
        }
        if (p ==null || q ==null) {
            return false;
        }
        if (p.val != q.val) {
            return false;
        }
        return isMirror(p.left, q.right) && isMirror(p.right, q.left);
    }

    // 两棵树是否相同

    // 最近公共祖先

    public static class TreeNode20241203 {
        int val;
        TreeNode20241203 left;
        TreeNode20241203 right;
        /*getter setter*/
    }
}