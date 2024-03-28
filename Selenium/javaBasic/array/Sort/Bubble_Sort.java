package javaBasic.array.Sort;


public class Bubble_Sort {

    // Hàm thực hiện Bubble Sort
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        // Vòng lặp chính, duyệt qua từng phần tử trong mảng
        for (int i = 0; i < n - 1; i++) {
            // Vòng lặp lồng nhau, so sánh và hoán đổi các phần tử nếu cần
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) { // Nếu phần tử hiện tại lớn hơn phần tử kế tiếp
                    // Hoán đổi phần tử
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            // Kiểm tra xem có hoán đổi nào được thực hiện không
            // Nếu không có hoán đổi nào, mảng đã được sắp xếp, kết thúc thuật toán
            if (isSorted(arr)) {
                break;
            }
        }
    }

    // Hàm kiểm tra xem mảng có được sắp xếp không
    public static boolean isSorted(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }
        return true;
    }

    // Hàm main để kiểm tra
    public static void main(String[] args) {
        int[] arr = {64, 34, 25, 12, 22, 11, 90};
        bubbleSort(arr);
        System.out.println("Sorted array:");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}


