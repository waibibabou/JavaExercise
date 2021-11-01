package file;

import org.junit.Test;

import java.io.*;

public class InputStreamReaderTest {
    @Test
    public void test1() throws IOException {
        FileInputStream fileInputStream=new FileInputStream("hello.txt");
        FileOutputStream fileOutputStream=new FileOutputStream("hello3.txt");

        InputStreamReader inputStreamReader=new InputStreamReader(fileInputStream,"utf-8");
        OutputStreamWriter outputStreamWriter=new OutputStreamWriter(fileOutputStream,"GBK");
        char[] buffer=new char[4];
        int len;
        while((len=inputStreamReader.read(buffer))!=-1){
            outputStreamWriter.write(buffer,0,len);
        }

        inputStreamReader.close();
        outputStreamWriter.close();

    }

}
