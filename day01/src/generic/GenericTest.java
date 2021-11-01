package generic;

import org.junit.Test;

import java.util.*;

public class GenericTest {
    @Test
    public void test1(){
        List<Integer> list=new ArrayList<Integer>();
        list.add(12);
        list.add(22);
        for(int i=0;i<list.size();i++){
            int a=list.get(i);
            System.out.println(a);
        }
    }

    @Test
    public void test2(){
        Map<String,Integer>map=new HashMap<String,Integer>();
        map.put("1",1);
        map.put("2",2);
        Set<Map.Entry<String,Integer>> set=map.entrySet();
        Iterator<Map.Entry<String,Integer>> iterator=set.iterator();
        while(iterator.hasNext()){
            Map.Entry<String,Integer> e=iterator.next();
            String a=e.getKey();
            int b=e.getValue();
            System.out.println(a+b);

        }

    }

    @Test
    public void test3(){
        //如果不使用泛型则默认为Object类型
        Order<String> order=new Order<>();
        order.setOrderT("12");
        Order<Integer> order1=new Order<>();
        order1.setOrderT(12);
        System.out.println(order1);
        List<Integer> list = order.copy(new Integer[]{3, 2, 1});
        System.out.println(list);
//        Order<String>[] a=new Order<String>[10];
        List<Order<String>> a=new ArrayList<>();
    }


    @Test
    public void test4(){
        Object[] a=new Object[5];
        Integer[] b=new Integer[5];
        a=b;
        a[0]='a';
        System.out.println(b[0]);

        List<Object> list1=new ArrayList<>();
        List<String> list2=new ArrayList<>();
//        list1=list2;List<Object>与List<String>是并列结构，没有子父类关系
        // 通配符的使用
        print(list1);
        print(list2);

    }

    public void print(List<?> list){
        Iterator<?> iterator=list.iterator();
        while(iterator.hasNext()){
            Object o=iterator.next();
            System.out.println(o);
        }
    }
}
