package search;

public class SearchDemo {
    // 二分查找，迭代
    public int binarySearch(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = (high - low) / 2 + low;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    // 递归
    public int binarySearch2(int[] arr, int target) {
        return bs(arr, 0, arr.length - 1, target);
    }

    private int bs(int[] arr, int low, int high, int target) {
        if (low > high) {
            return -1;
        }
        int mid = (high - low) / 2 + low;
        if (arr[mid] > target) {
            return bs(arr, low, mid - 1, target);
        } else if (arr[mid] < target) {
            return bs(arr, mid + 1, high, target);
        } else {
            return mid;
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 10, 19, 20, 21, 26};
        SearchDemo searchDemo = new SearchDemo();
        System.out.println(searchDemo.binarySearch(arr, 21));
        System.out.println(searchDemo.binarySearch2(arr, 21));
    }
}
