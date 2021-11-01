package file;

import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileReaderWriterTest {
    @Test
    public void testFileReader() throws IOException {
        File file=new File("hello.txt");
        FileReader fr=new FileReader(file);
        int data = fr.read();
        while (data!=-1){
            System.out.print((char)data);
            data=fr.read();
        }
        fr.close();
    }

    /**
     * 使用read的重载方法
     */
    @Test
    public void testFileReader1(){
        FileReader fr= null;
        try {
            File file=new File("hello.txt");

            fr = new FileReader(file);
            char[] cbuf=new char[5];
            int len;
            while((len=fr.read(cbuf))!=-1){
                for(int i=0;i<len;i++){
                    System.out.print(cbuf[i]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fr!=null){
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }



    }

    @Test
    public void testFileWriter() throws IOException {
        FileWriter fileWriter= null;
        try {
            File file=new File("hello1.txt");
            fileWriter = new FileWriter(file,false);
            fileWriter.write("how are you我\n");
            fileWriter.write("hello world");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fileWriter!=null){
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }


    }

    @Test
    public void testFileReaderFileWriter() throws IOException {
        FileReader fileReader= null;
        FileWriter fileWriter= null;
        try {
            File fileInput=new File("hello.txt");
            File fileOutput=new File("hello1.txt");

            fileReader = new FileReader(fileInput);
            fileWriter = new FileWriter(fileOutput);

            char[] cbuf=new char[5];
            int len;
            while((len=fileReader.read(cbuf))!=-1){
                fileWriter.write(cbuf,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fileReader!=null){
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(fileWriter!=null){
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }

}
