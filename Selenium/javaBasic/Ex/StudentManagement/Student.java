package javaBasic.Ex.StudentManagement;

public class Student {
    private String name;
    private int age;
    private int ID;
    private float average;

    public Student(String name, int age, int ID, float average) {
        this.name = name;
        this.age = age;
        this.ID = ID;
        this.average = average;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public float getAverage() {
        return average;
    }

    public void setAverage(float average) {
        this.average = average;
    }
}
