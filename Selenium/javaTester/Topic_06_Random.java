package javaTester;

import java.util.Random;

public class Topic_06_Random {
    // Java Builtin (Cung cap san - chi viec lay ra su dung )
    //Java Libraries (do 1 cá nhân/ to chuc ho tu viet)
    // java faker

    public static void main(String[] args) {
        Random rand = new Random();
        System.out.println(rand.nextInt());
        System.out.println(rand.nextInt());

        System.out.println(rand.nextDouble());
        System.out.println(rand.nextFloat());
        System.out.println(rand.nextBoolean());

        String emailAddress = "automation" + rand.nextInt(9999) + "@gmail.net";
        System.out.println("automation" + rand.nextInt(9999) + "@gmail.net");
        System.out.println("automation" + rand.nextInt(9999) + "@gmail.net");
        System.out.println("automation" + rand.nextInt(9999) + "@gmail.net");

    }
}
