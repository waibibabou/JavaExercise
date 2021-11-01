package file;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class TCPTest2 {

    @Test
    public void client() throws IOException {
        Socket socket=new Socket(InetAddress.getByName("localhost"),9090);
        OutputStream outputStream=socket.getOutputStream();

        FileInputStream fileInputStream=new FileInputStream("壁纸.jpg");

        byte[] buffer=new byte[1024];
        int len;
        while((len=fileInputStream.read(buffer))!=-1){
            outputStream.write(buffer,0,len);
        }
        socket.shutdownOutput();

        InputStream inputStream=socket.getInputStream();
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        byte[] buffer1=new byte[20];
        int len1;
        while((len1=inputStream.read(buffer1))!=-1){
            byteArrayOutputStream.write(buffer1,0,len1);
        }
        System.out.println(byteArrayOutputStream.toString());

        byteArrayOutputStream.close();
        fileInputStream.close();
        socket.close();

    }

    @Test
    public void server() throws IOException {
        ServerSocket serverSocket=new ServerSocket(9090);
        Socket socket=serverSocket.accept();
        InputStream inputstream=socket.getInputStream();

        FileOutputStream fileOutputStream=new FileOutputStream("temp.jpg");

        byte[] buffer=new byte[1024];
        int len;
        while((len=inputstream.read(buffer))!=-1){
            fileOutputStream.write(buffer,0,len);
        }

        OutputStream outputStream=socket.getOutputStream();
        outputStream.write("已收到".getBytes(StandardCharsets.UTF_8));


        outputStream.close();
        fileOutputStream.close();
        socket.close();

    }
}
