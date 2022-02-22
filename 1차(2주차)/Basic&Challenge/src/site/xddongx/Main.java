package site.xddongx;

public class Main {
    public static void main(String[] args) {
        Person student = new Student("김동현", 30);
        student.speak();

        Person lecturer = new Lecturer("김동현", 30);
        lecturer.speak();

    }
}
