package collection_and_map_test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ListTest {
    public static void main(String[] args) {
        List list=new ArrayList();
        list.add(123);
        list.add("asd");
        list.add(0,"aa");
        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i));
        }
        Iterator iterator=list.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }

        List list1= Arrays.asList(1,2,4,6);//将数字转化为list
        Iterator iterator1=list1.iterator();
        while(iterator1.hasNext()){
            System.out.println(iterator1.next());
        }
        System.out.println(list1.indexOf(4));//首次出现的位置 找不到返回-1
        System.out.println(list1.lastIndexOf(1));//最后一次出现的位置 找不到返回-1

        list.remove(0);

        System.out.println(list);
        list.add(1);
        list.add(2);
//        list.remove((Object) 1);
//        list.remove(new Integer(1));
        System.out.println(list);
        list.set(0,"qq");
        System.out.println(list.get(0));//返回list中的某位置元素

    }

}
