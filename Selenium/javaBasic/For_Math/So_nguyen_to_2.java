package javaBasic.For_Math;

public class So_nguyen_to_2 {
    /*public static boolean check (int number){
        int count = 0;
        if (number <= 1) return false;
        for (int i = 2; i <= Math.sqrt(number);i++){
            if (number % i == 0) count++;
        }
        if (count == 0) return true;
        else return false;
    }*/

    public static boolean check (int number){
        if (number <= 1) return false;
        for (int i = 2; i <= Math.sqrt(number);i++){
            if (number % i == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.print("List so nguyen to tu 1 - 100 la: ");
        for (int i = 1; i<=100;i++){
            if (check(i)) System.out.print(i+ " ");;
        }
    }
}
