package javaBasic.array;

public class Check {
    public static void main(String[] args) {
        int[] array = new int[5];

        // Kiểm tra xem biến array có phải là một mảng không
        if (array instanceof int[]) {
            System.out.println("Biến array là một mảng.");
        } else {
            System.out.println("Biến array không phải là một mảng.");
        }
    }

}
