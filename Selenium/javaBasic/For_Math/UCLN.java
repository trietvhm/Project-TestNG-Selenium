package javaBasic.For_Math;

public class UCLN {
    // Method to find GCD using Euclidean algorithm
    public static int findGCD(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static void main(String[] args) {
        int number1 = 24;
        int number2 = 36;

        int gcd = findGCD(number1, number2);
        System.out.println("GCD of " + number1 + " and " + number2 + " is: " + gcd);
    }
}
