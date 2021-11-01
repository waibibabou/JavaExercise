package collection_and_map_test;

import org.junit.Test;

import java.util.*;

public class CollectionsTest {
    @Test
    public void test1(){
        //测试工具类Collections中的方法

        List list=new ArrayList();
        list.add(123);
        list.add(12);
        list.add(23);
        Collections.reverse(list);
        System.out.println(list);
        Collections.shuffle(list);//随机化
        System.out.println(list);

        Collections.sort(list);//对collection 中的list进行排序
        System.out.println(list);

        Collections.swap(list,0,1);
        System.out.println(list);

        Map map=new HashMap();
        map.put(1,0);
        map.put(2,1);
        map.put(3,2);
        System.out.println(Collections.max(list));
//        Collections.sort(map);

    }

    @Test
    public void test2(){
        List list=new ArrayList();
        list.add(123);
        list.add(234);
        list.add(345);
        List dest=Arrays.asList(new Object[list.size()]);
        System.out.println(dest.size());
        Collections.copy(dest,list);
        System.out.println(dest);

//        Object[] a=new Object[3];
//        System.out.println(a.length);

    }
}
