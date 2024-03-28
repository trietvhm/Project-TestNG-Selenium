package javaBasic.For_Math;

import java.util.Scanner;

public class So_nguyen_to {
    // so nguyen to nho hon 100
    public static void main(String[] args) {
        /*
        int temp = 0;
        Scanner input =  new Scanner(System.in);
        System.out.println("Nhap n: ");
        int n = input.nextInt();
        for (int i = 2; i <= Math.sqrt(n); i++){
            if (n % i == 0) {
                temp++;
            }
        }
        if (temp==0) System.out.println("n la so nguyen to");
        else System.out.println("n khong phai la so nguyen to");*/


        //int temp = 0;
        //Scanner input = new Scanner(System.in);
        // System.out.println("Nhap n: ");
        //int n = input.nextInt();
        for (int i = 1; i <= 100; i++) {
            int temp = 0;
            for (int j = 1; j <= i; j++) {
                if (i % j == 0) {
                    temp++;
                }
            }
            if (temp == 2) System.out.print(i+" ");
        }
    }
}
