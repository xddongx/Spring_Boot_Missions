package site.xddongx;

public class Student extends AbstractPerson implements Person {

    public Student() {
    }

    public Student(String name, int age) {
        super(name, age);
    }


    @Override
    public void speak() {
        System.out.print("Student ");
        super.speak();
    }

}
