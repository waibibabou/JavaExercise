package collection_and_map_test;

import org.junit.Test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SetTest {
    @Test
    public void test1(){

        //set接口中没有定义新的方法 与collection中的方法相同
        Set set=new HashSet();//set接口的主要实现类

        set.add(123);
        set.add("add");//add时候的添加位置是通过计算该元素的hashcode得到的 体现了无序性
        //如果Person1类中没有重写hashcode方法 Object类中的该方法返回一个随机数 则下面两个元素放在了数组的不同位置
        set.add(new Person1(21,"zzz"));
        set.add(new Person1(21,"zzz"));
        Iterator iterator=set.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }

    }
}
