package site.xddongx;

public class Lecturer extends AbstractPerson implements Person {
    public Lecturer() {
    }

    public Lecturer(String name, int age) {
        super(name, age);
    }

    @Override
    public void speak() {
        System.out.print("Lecturer ");
        super.speak();
    }
}
