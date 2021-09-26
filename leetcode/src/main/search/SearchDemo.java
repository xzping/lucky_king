package search;

public class SearchDemo {
    // 二分查找
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

    public static void main(String[] args) {
        int[] arr = {1, 10, 19, 20, 21, 26};
        SearchDemo searchDemo = new SearchDemo();
        System.out.println(searchDemo.binarySearch(arr, 20));
    }
}
