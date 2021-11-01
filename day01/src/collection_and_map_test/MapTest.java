package collection_and_map_test;

import org.junit.Test;

import java.util.*;

public class MapTest {
    @Test
    public void test1(){
        HashMap hashMap=new HashMap();
        hashMap.put(1,1);
        hashMap.put(2,2);
        hashMap.put("a","a");
        hashMap.put("a","b");
//        System.out.println(hashMap.toString());
        System.out.println(hashMap);

        HashMap hashMap1=new HashMap();
        hashMap1.put(3,3);
        hashMap1.put(4,4);
        hashMap.putAll(hashMap1);
        System.out.println(hashMap);
        hashMap.remove(1);
        System.out.println(hashMap);

        System.out.println(hashMap.get("a")+" "+hashMap.containsKey(2)+" "+hashMap.containsValue("a"));



        hashMap.clear();//将其中的数据清空 不是执行hashMap=null
        System.out.println(hashMap.size());

    }

    @Test
    public void test2(){
        Map map=new HashMap();
        map.put(1,1);
        map.put("a","aa");
        Set set=map.keySet();
        Iterator iterator=set.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
//        List list=map.values();
        Collection collection=map.values();//map中keys是返回set values返回collection
        Iterator iterator1=collection.iterator();
        while(iterator1.hasNext()){
            System.out.println(iterator1.next());
        }


        Collection entry=map.entrySet();
        Iterator iterator2=entry.iterator();
        while (iterator2.hasNext()){
            Map.Entry entry1=(Map.Entry) iterator2.next();
            System.out.println(entry1.getKey()+" "+entry1.getValue());
        }

        Set set1=map.keySet();
        Iterator iterator3=set1.iterator();
        while(iterator3.hasNext()){
            Object key=iterator3.next();
            Object values=map.get(key);
            System.out.println(key+"** "+values);
        }


    }


}
