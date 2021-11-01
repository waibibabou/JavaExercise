package file;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class TCPTest1 {
    @Test
    public void client() throws IOException {
        InetAddress inetAddress=InetAddress.getByName("127.0.0.1");
        Socket socket=new Socket(inetAddress,8899);

        OutputStream outputStream=socket.getOutputStream();
        outputStream.write("hello".getBytes(StandardCharsets.UTF_8));
        outputStream.close();
        socket.close();

    }

    @Test
    public void server() throws IOException {
        ServerSocket serverSocket=new ServerSocket(8899);

        Socket socket=serverSocket.accept();
        InputStream inputStream=socket.getInputStream();

//        byte[] buffer=new byte[5];
//        int len;
//        while((len=inputStream.read(buffer))!=-1){
//            String str=new String(buffer,0,len);
//            System.out.print(str);
//
//        }
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        byte[] buffer=new byte[3];
        int len;
        while((len=inputStream.read(buffer))!=-1){
            byteArrayOutputStream.write(buffer,0,len);
        }
        System.out.println(byteArrayOutputStream.toString());

        byteArrayOutputStream.close();
        inputStream.close();
        socket.close();
        serverSocket.close();

    }

}
