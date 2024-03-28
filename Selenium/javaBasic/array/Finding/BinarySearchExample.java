package javaBasic.array.Finding;

public class BinarySearchExample {
    // Hàm tìm kiếm nhị phân
    public static int binarySearch(int[] arr, int target) {
        int left_index = 0;
        int right_index = arr.length - 1;

        while (left_index <= right_index) {
            int mid_index = (left_index + right_index)  / 2;

            // Nếu target nằm ở giữa mảng nếu bằng với target thì trả ve chi so cua mid_index luon
            if (arr[mid_index] == target) {
                return mid_index;
            }

            // Nếu target nhỏ hơn phần tử ở giữa mảng, ta chỉ cần tìm kiếm bên trái
            if (arr[mid_index] > target) {
                right_index = mid_index - 1;
            }
            // Nếu target lớn hơn phần tử ở giữa mảng, ta chỉ cần tìm kiếm bên phải
            else {
                left_index = mid_index + 1;
            }
        }

        // Nếu không tìm thấy target, trả về -1
        return -1;
    }
    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 6, 9, 10, 11, 12, 13};
        int target = 5;

        // Gọi hàm binarySearch để tìm kiếm target trong mảng arr
        int result = binarySearch(arr, target);

        // In ra kết quả
        if (result != -1) {
            System.out.println("Phần tử " + target + " được tìm thấy tại chỉ số " + result);
        } else {
            System.out.println("Phần tử " + target + " không tồn tại trong mảng");
        }
    }
}
