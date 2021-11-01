package file;

import org.junit.Test;

import java.io.*;

/**
 * 缓冲流
 */
public class BufferedTest {
    @Test
    public void BufferedStreamTest() throws IOException {
        BufferedInputStream bufferedInputStream= null;
        BufferedOutputStream bufferedOutputStream= null;
        try {
            File file=new File("壁纸.jpg");
            File file1=new File("壁纸1.jpg");
            //造节点流
            FileInputStream fileInputStream=new FileInputStream(file);
            FileOutputStream fileOutputStream=new FileOutputStream(file1);
            //造处理流
            bufferedInputStream = new BufferedInputStream(fileInputStream);
            bufferedOutputStream = new BufferedOutputStream(fileOutputStream);

            byte[] buffer=new byte[10];
            int len;
            while((len=bufferedInputStream.read(buffer))!=-1){
                bufferedOutputStream.write(buffer,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            if(bufferedInputStream!=null){
                try {
                    //关闭流 先关闭外层的 再关内层的 关外层流时内层自动关闭
                    bufferedInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(bufferedOutputStream!=null){
                try {
                    bufferedOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }





    }
}
