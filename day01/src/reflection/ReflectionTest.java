package reflection;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionTest {

    //获取Class的实例的方式
    @Test
    public void test() throws ClassNotFoundException {
        Class clazz1=Person.class;
        System.out.println(clazz1);

        Person person=new Person();
        Class clazz2=person.getClass();
        System.out.println(clazz2);

        Class clazz3=Class.forName("reflection.Person");
        System.out.println(clazz3);

        System.out.println(clazz1==clazz3);
        System.out.println(clazz2==clazz3);

        System.out.println(Person.class.getClassLoader());

    }

    //通过反射创建对应的运行时类的对象
    @Test
    public void test1() throws IllegalAccessException, InstantiationException {
        Class clazz=Person.class;
        Person person= (Person) clazz.newInstance();

    }

    //获取当前运行时类的属性结构
    @Test
    public void test2(){
        Class clazz=Person.class;
        Field[] fields=clazz.getFields();
        for(Field f:fields){
            System.out.println(f);
        }
        System.out.println();

        Field[] declaredFields=clazz.getDeclaredFields();
        for(Field f:declaredFields){
            System.out.println(f);
        }
    }

    //调用运行时类中指定的属性
    @Test
    public void test3() throws Exception {
        Class clazz=Person.class;
        Person person=(Person)clazz.newInstance();
        Field age= clazz.getField("age");

        age.set(person,10);

        System.out.println(age.get(person));

        Field name=clazz.getDeclaredField("name");
        name.setAccessible(true);//保证当前属性是可以访问的
        name.set(person,"zyk");
        System.out.println(name.get(person));
    }

    //调用运行时类中指定的方法
    @Test
    public void test4() throws Exception, InstantiationException {
        Class clazz=Person.class;
        Person person=(Person) clazz.newInstance();
        Method showNation= clazz.getDeclaredMethod("showNation",String.class);
        showNation.setAccessible(true);//保证当前方法是可访问的
        String nation=(String) showNation.invoke(person,"aa");
        System.out.println(nation);

        //调用静态方法
        Method ss=clazz.getDeclaredMethod("ss");
        ss.setAccessible(true);
        //如果没有返回值则invoke返回null
        Object returnVal= ss.invoke(Person.class);
        System.out.println(returnVal);

    }

    //调用指定的构造器
    @Test
    public void test5() throws Exception {
        Class clazz=Person.class;

        Constructor constructor= clazz.getDeclaredConstructor(String.class);
        constructor.setAccessible(true);
        Person person=(Person) constructor.newInstance("zyk");
        System.out.println(person);


    }



}

class Person{
    private String name;
    public int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    private Person(String name){
        this.name=name;
    }

    public Person() {
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

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
    public void show(String str){
        System.out.println("我是一个人");
    }
    private String showNation(String nation){
        System.out.println("我的国籍是"+nation);
        return nation;
    }

    private static void ss(){
        System.out.println("这是一个静态方法");
    }

}
