package javaBasic.For_Math;

import java.util.Scanner;

public class Ex3_Tam_giac_vuong_can {
    public static void main(String[] args) {
        Scanner input =  new Scanner(System.in);
        System.out.println("Nhap n: ");
        int n = input.nextInt();
        for(int i = 1; i <= n;i++){
            for (int j = 1;j<=i;j++){
                System.out.print("* ");
            }
            System.out.print("\n");
        }
    }
}
