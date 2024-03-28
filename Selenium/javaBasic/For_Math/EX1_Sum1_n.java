package javaBasic.For_Math;

import java.util.Scanner;

public class EX1_Sum1_n {
    public static void main(String[] args) {
        int sum = 0;
        Scanner input =  new Scanner(System.in);
        System.out.println("Nhap n: ");
        int n = input.nextInt();

        for (int i = 0;i<=n;i++){
            sum += i ;
        }
        System.out.println("Sum: " +sum);
    }
}
