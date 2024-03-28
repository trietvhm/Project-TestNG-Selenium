package javaBasic.For_Math;

import java.util.Scanner;

public class Ex2_GIAITHUA {
    public static void main(String[] args) {
        int factorial = 1;
        Scanner input =  new Scanner(System.in);
        System.out.println("Nhap giai thua n: ");
        int n = input.nextInt();

        for (int i = 0; i < n;i++){
            factorial *= (n-i);
        }
        System.out.println("Giai thua cua n: " + factorial);

    }

}
