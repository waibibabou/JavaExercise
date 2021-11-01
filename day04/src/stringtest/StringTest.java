package stringtest;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class StringTest {

    @Test
    public void test1(){
        String s1="abc";
        String s2="abc";//放在方法区的常量池中 常量池中不会用相同的字符串
        System.out.println(s1==s2);

        String s3=new String("abc");
        String s4=new String("abc");//new的东西在堆空间中 堆中的values指向常量池中的abc串 返回的是堆空间的地址
        System.out.println(s3==s4);
        System.out.println(s1==s3);

        String s5="hello";
        String s6="world";
        String s7="helloworld";
        String s8="hello"+"worl"+"d";//只要有变量就是在堆空间中
        System.out.println(s7==s8);
        String s9=s5.concat("world");//有变量 同样在堆空间中
        System.out.println(s9==s7);

    }

    /**
     * String类中的常用方法测试
     */
    @Test
    public void test2(){
        String s1="helloworld";
        System.out.println(s1.length());
        System.out.println(s1.charAt(1));
        System.out.println(s1.isEmpty());

        String s2=s1.toUpperCase();//s1是不可变的
        System.out.println(s2+"\n"+s1);

        String s3="  hel  lo world ";
        String s4=s3.trim();//去除字符串前后空格
        System.out.println(s4);

        String s5="abc";
        String s6="arc";
        System.out.println(s5.compareTo(s6));

        String s7="hello";
        System.out.println(s7.contains("he"));
        System.out.println(s7.indexOf("ll"));


    }


    @Test
    public void test3(){
        String s1="hello world";
        String s2=s1.replace('l','a');
        System.out.println(s2);

    }

    /**
     * String与基本数据类型、包装类之间的转换
     */
    @Test
    public void test4(){
        String s1="123";
        int a=Integer.parseInt(s1);//不可以强转 只有子父类的关系才可以

        String s2=String.valueOf(12);

    }

    /**
     * String与char[]之间的转换
     */
    @Test
    public void test5(){
        String s1="hello";
        char[] charArray=s1.toCharArray();
        for(int i=0;i<s1.length();i++){
            System.out.print(charArray[i]);
        }

        char[] arr=new char[]{'1','s','d'};
        System.out.println(String.valueOf(arr));
//        String s2=Arrays.toString(arr);
//        System.out.println(s2);
//        String s3=new String(arr);
//        System.out.println(s3.toString());

    }

    /**
     * String与byte[]之间的转换
     */
    @Test
    public void test6() throws UnsupportedEncodingException {
        String str1="abc中国";
        byte[] bytes=str1.getBytes();//utf-8 编码
        System.out.println(Arrays.toString(bytes));
        byte[] bytes1=str1.getBytes("gbk");
        System.out.println(Arrays.toString(bytes1));

        String s1=new String(bytes);//默认utf-8 解码
        String s2=new String(bytes1,"gbk");
        System.out.println(s2);



    }

}
