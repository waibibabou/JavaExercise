package comparable;

import org.junit.Test;

import java.util.*;

public class ComparableTest {
    @Test
    public void test1(){
        int[][] a=new int[][]{{3,4},{5,6},{2,5}};
        Arrays.sort(a, new Comparator<int[]>() {//定制排序
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0];
            }
        });
        Arrays.sort(a, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return 0;
            }
        });

        List<int[]> list=new ArrayList<int[]>();
        list.add(new int[]{1,2});
        list.add(new int[]{0,5});
        list.add(new int[]{2,2});
        Collections.sort(list, new Comparator<int[]>() {
            public int compare(int[] o1,int[] o2){
                return o1[0]-o2[0];
            }
        });
        for(int i=0;i<list.size();i++){
            System.out.println(Arrays.toString(list.get(i)));
        }

    }


    @Test
    public void test2(){
        Goods[] arr=new Goods[2];
        arr[0]=new Goods("a",10);
        arr[1]=new Goods("b",8);
        Arrays.sort(arr);//调用compareTo方法
        System.out.println(arr[0]);

        String str=System.getProperty("java.version");
        System.out.println(str);

    }

}

class Goods implements Comparable{
    private String name;
    private double price;

    /**
     * 在此方法中指明按照什么顺序排序
     * @param o
     * @return
     */
    @Override
    public int compareTo(Object o) {
        if(o instanceof Goods){
            Goods goods=(Goods) o;
            if(price>goods.price)return 1;
            else if(price==goods.price)return 0;
            else return -1;
        }

        return 0;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Goods(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Goods() {
    }

}


