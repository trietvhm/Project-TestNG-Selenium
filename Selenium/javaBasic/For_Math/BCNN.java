package javaBasic.For_Math;

public class BCNN {
    public static int findGCD(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    // Phương thức tính BCNN (bội chung nhỏ nhất)
    public static int findLCM(int a, int b) {
        return (a * b) / BCNN.findGCD(a, b);
    }

    public static void main(String[] args) {
        int number1 = 24;
        int number2 = 36;

        int lcm = findLCM(number1, number2);
        System.out.println("BCNN của " + number1 + " và " + number2 + " là: " + lcm);
    }
}
