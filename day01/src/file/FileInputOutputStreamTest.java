package file;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileInputOutputStreamTest {
    @Test
    public void testFileInputStream() {
        FileInputStream fileInputStream= null;
        try {
            File file=new File("hello.txt");

            fileInputStream = new FileInputStream(file);

            byte[] buffer=new byte[5];
            int len;
            while((len=fileInputStream.read(buffer))!=-1){
                String str=new String(buffer,0,len);
                System.out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fileInputStream!=null){
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
