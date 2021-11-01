package file;

import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileTest {
    @Test
    public void test1(){
        File file=new File("hello.txt");
        System.out.println(file);
        File file1=new File("C:\\Users\\lenovo\\Desktop","1");
        System.out.println(file1);
        File file2=new File(file1,"1.txt");
        System.out.println(file2);
    }

    @Test
    public void test2(){
        File file=new File("hello.txt");
        File file1=new File("C:\\Users\\lenovo\\Desktop");
        System.out.println(file.getAbsoluteFile());
        System.out.println(file.getPath());
        System.out.println(file.getName());
        System.out.println(file.length());

        String[] list=file1.list();
        for(int i=0;i<list.length;i++){
            System.out.println(list[i]);
        }
        //返回字节数
        System.out.println(file.length());


    }

    @Test
    public void test3() throws IOException {
        //文件的创建
        File file=new File("hello.txt");
        if(!file.exists()){
            file.createNewFile();
            System.out.println("创建成功");
        }
        else{
            file.delete();
            System.out.println("文件删除成功");
        }

        //文件目录的创建
        File file1=new File("C:\\Users\\lenovo\\Desktop\\1\\1");
        boolean a=file1.mkdir();
        System.out.println(a);


    }

    @Test
    public void test4() throws IOException {
        //在单元测试中是相较于module下的，但是在main方法中是相较于工程下的，写成"day01//hello.txt"
        File file=new File("hello.txt");

        FileReader fileReader=new FileReader(file);
        int a=fileReader.read();
        while(a!=-1){
            System.out.print((char)a);
            a=fileReader.read();
        }
        fileReader.close();


    }

}
