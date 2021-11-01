package file;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressTest {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress inetAddress1=InetAddress.getByName("www.baidu.com");
        System.out.println(inetAddress1);

        InetAddress inetAddress2=InetAddress.getByName("localhost");
        System.out.println(inetAddress2);

        System.out.println(inetAddress1.getHostAddress());
        System.out.println(inetAddress1.getHostName());
    }
}
