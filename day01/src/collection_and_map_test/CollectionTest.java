package collection_and_map_test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class CollectionTest {

    @Test
    public void test(){
        String[] a=new String[3];

    }



    @Test
    public void test1(){
        //ArrayList是Collection的实现类，所以可以创建对象
        Collection collection=new ArrayList();
        //collection下边有list与set list有序 set无序

        collection.add(123);//自动装箱
        collection.add("abc");
        System.out.println(collection.size());
        Collection collection1=new ArrayList();
        collection1.add(12);
        collection1.add(14);
        collection.addAll(collection1);
        System.out.println(collection.toString());

        ArrayList arrayList=new ArrayList();
        arrayList.add(189);
        System.out.println(arrayList.toString()+arrayList.isEmpty());
        arrayList.clear();
        System.out.println(arrayList.isEmpty());
        System.out.println("************************");

        Collection collection2=new ArrayList();
        collection2.add("asd");
        collection2.add(145);
        collection2.add(145);
        collection2.add(new Person(20,"zyk"));
        System.out.println(collection2.contains(145));
        //调用的是equals进行比较，String里面重写过从Object类继承的equals方法
        System.out.println(collection2.contains(new String("asd"))+""+collection2.contains(145));
        //Person自定义类里面没有重写equals方法,重写之后为true
        System.out.println(collection2.contains(new Person(20,"zyk")));

        collection2.remove(145);
        System.out.println(collection2);//调用toString()方法
        System.out.println(collection2.hashCode());//计算当前对象的hash值 从Object类继承来的
        Object[] arr=collection2.toArray();
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }


    }


    @Test
    public void test2(){
        Collection collection=new ArrayList();
        collection.add(11);
        collection.add(22);
        collection.add(new int[]{123,456});
//        collection.remove(22);
        System.out.println(collection.contains(11));
        Iterator iterator=collection.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

}


class Person{
    private int age;
    private String name;


    Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if(o==this)return true;
        if(o==null||o.getClass()!=getClass())return false;

        Person person=(Person)o;
        return age==person.age&&name.equals(person.name);


//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Person person = (Person) o;
//        return age == person.age && Objects.equals(name, person.name);
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
