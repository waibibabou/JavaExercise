package file;

import org.junit.Test;

import java.io.*;

/**
 * 对象流的使用
 */
public class ObjectInputOutputStreamTest {
    /**
     * 序列化过程
     */
    @Test
    public void testObjectOutputStream() throws IOException {
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(new FileOutputStream("hello.dat"));
        objectOutputStream.write(11);
        objectOutputStream.writeObject(new String("hhh"));
        objectOutputStream.flush();//刷新操作
        objectOutputStream.close();

    }

    @Test
    public void testObjectInputStream() throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream=new ObjectInputStream(new FileInputStream("hello.dat"));
        Object object1=objectInputStream.read();
        Object object =objectInputStream.readObject();
        String str=(String) object;
        System.out.println(str+""+object1);
        objectInputStream.close();
    }



}
