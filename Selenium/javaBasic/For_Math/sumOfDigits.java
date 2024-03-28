package javaBasic.For_Math;

public class sumOfDigits {
    public static int sumOfDigits(int number) {
        int sum = 0;

        // Duyệt từng chữ số của số nguyên dương
        while (number > 0) {
            // Lấy chữ số cuối cùng bằng phép chia lấy dư cho 10
            int digit = number % 10;
            // Cộng chữ số này vào tổng
            sum += digit;
            // Loại bỏ chữ số cuối cùng bằng cách chia số cho 10
            number /= 10;
            System.out.println(number);
        }

        return sum;
    }

    public static void main(String[] args) {
        int number = 12345;
        int sum = sumOfDigits(number);
        System.out.println("Tổng các chữ số của " + number + " là: " + sum);
    }
}
