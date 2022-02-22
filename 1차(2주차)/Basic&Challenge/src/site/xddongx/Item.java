package site.xddongx;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Item{
    private List<Person> personList;
    private Set<Person> personSet;
    private Iterator personIterator;

    public Item(List<Person> personList) {
        this.personList = personList;
        if (this.personList.isEmpty()) System.out.println("No Elements");
    }

    public Item(Set<Person> personSet) {
        this.personSet = personSet;
        if (this.personSet.isEmpty()) System.out.println("No Elements");
    }

    public Item(Iterator personIterator) {
        this.personIterator = personIterator;
    }

    public Person get(int i){
        return personList.get(i);
    }

    public int listSize() {
        return this.personList.size();
    }

    public boolean hasNext() {
        return this.personIterator.hasNext();
    }

    public Person next() {
        return (Person) this.personIterator.next();
    }

    @Override
    public String toString() {
        return this.personList.toString();
    }

//    @Override
//    public String toString() {
//        return this.personIterator.toString();
//    }
}
