package javaBasic.array.Finding;

import java.util.Scanner;

public class Sequential_Search {
    public static int sequentialSearch(int[] arr, int target) {
        // Duyệt qua từng phần tử của mảng
        for (int i = 1; i <= arr.length; i++) {
            // Nếu phần tử hiện tại trùng với phần tử cần tìm, trả về chỉ số của phần tử
            if (arr[i] == target) {
                return i;
            }
        }
        // Nếu không tìm thấy phần tử, trả về -1
        return -1;
    }

    public static void main(String[] args) {
        //int[] arr = {1, 3, 5, 6, 9};
        Scanner input = new Scanner(System.in);

        int[] arr = new int[5];
        for (int i = 1; i <= arr.length;i++){
            arr[i] = input.nextInt();
        }
        int target = 5;

        // Gọi hàm sequentialSearch để tìm kiếm target trong mảng arr
        int result = sequentialSearch(arr, target);

        // In ra kết quả
        if (result != -1) {
            System.out.println("Phần tử " + target + " được tìm thấy tại chỉ số " + result);
        } else {
            System.out.println("Phần tử " + target + " không tồn tại trong mảng");
        }
    }
}
