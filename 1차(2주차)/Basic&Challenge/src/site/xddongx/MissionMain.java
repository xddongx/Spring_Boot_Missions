package site.xddongx;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MissionMain {
    public static void main(String[] args) {
//        List<Person> arrayList = new ArrayList<>();
//        List<Person> linkedList = new LinkedList<>();
//        List<Person> vector = new Vector<>();
        Set<Person> hashSet = new HashSet<>();

        for (int i=0; i< 100; i++) {
            Person student = new Student("김동현: "+i, 30);
//            arrayList.add(student);
//            linkedList.add(student);
//            vector.add(student);
            hashSet.add(student);
        }

//        Item arrayItem = new Item(arrayList);
//        Item linkedItem = new Item(linkedList);
//        Item vectorItem = new Item(vector);

        Iterator iter = hashSet.iterator();
        Item hashIter = new Item(iter);


//        // ArrayList, LinkedList, Vector
//        System.out.println("idx\titem");
//        for (int j=0; j < vectorItem.listSize(); j++) {
//            System.out.printf("%d\t%s %d\n", j, vectorItem.get(j).toString(), (j+1));
//        }

        // HashSet
        int k = 0;
        while (hashIter.hasNext()){
            System.out.printf("%d\t%s %d\n", k, hashIter.next(), (k+1));
            k++;
        }
    }
}

