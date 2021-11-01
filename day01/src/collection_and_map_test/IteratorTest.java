package collection_and_map_test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;

public class IteratorTest {

    @Test
    public void test1(){
        Collection collection=new ArrayList();
        collection.add(13);
        collection.add("ad");
        collection.add(new Person1(21,"z"));
        Iterator iterator=collection.iterator();
        //System.out.println(iterator.next());

        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }

        Iterator iterator1=collection.iterator();
        while(iterator1.hasNext()){
            if(iterator1.next().equals(13)){
                iterator1.remove();
            }
        }
        Iterator iterator2=collection.iterator();
        while(iterator2.hasNext()){
            System.out.println(iterator2.next());
        }

    }
}

class Person1{
    private int age;
    private String name;

    Person1(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person1{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person1 person1 = (Person1) o;
        return age == person1.age && Objects.equals(name, person1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, name);
    }
}
