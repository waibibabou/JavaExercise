package stringtest;

import org.junit.Test;

import java.util.Scanner;

public class StringBufferBuilderTest {

    @Test
    public void test1(){
        Scanner scanner=new Scanner(System.in);

        StringBuffer stringBuffer=new StringBuffer("aa");
        StringBuffer stringBuffer1=new StringBuffer(scanner.next());
        stringBuffer1.reverse();
        System.out.println(stringBuffer1);
        //setCharAt方法不会像String一样返回一个新的String 而是直接改 因为其可变
        stringBuffer.setCharAt(1,'b');
        stringBuffer.append(1);
        System.out.println(stringBuffer);

        char[] arr=new char[5];
        arr[0]='a';
        System.out.println(arr.length);
        String s="abb";
        System.out.println(s.length());

    }

    @Test
    public void test2(){
        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append(1);
        stringBuffer.append('c');
        stringBuffer.delete(1,2);//涉及到开始与结束位置的都是左闭右开
        System.out.println(stringBuffer);
        stringBuffer.append(2);

    }





}
