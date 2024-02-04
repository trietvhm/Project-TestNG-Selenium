package javaTester;

public class Topic_08_Parameter {
    static String fullNameGlobal = "Automation Testing";

    public static void main(String[] args) {
        // Doi so
        setFullName("Manual Testing");
        System.out.println(getFullName());

    }
    public static void setFullName(String fullName) { // Tham so: Parameter
        fullNameGlobal = fullName;
    }

    public static String getFullName(){
        return fullNameGlobal;
    }

}
