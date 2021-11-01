package connection;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionTest {
    //方式一
    @Test
    public void testConnection1() throws SQLException {
        //获取driver实现类对象
        Driver driver=new com.mysql.jdbc.Driver();
        //操作mysql中的test数据库
        String url="jdbc:mysql://localhost:3306/test";
        //用户名和密码
        Properties info=new Properties();
        info.setProperty("user","root");
        info.setProperty("password","2560313");
        Connection connection=driver.connect(url,info);
        System.out.println(connection);

    }

    //方式二 在如下程序中不包含第三方api 有较好的移植性
    @Test
    public void testConnection2() throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {
        //使用反射获取Driver的实现类的对象
        Class clazz=Class.forName("com.mysql.jdbc.Driver");
        Driver driver= (Driver) clazz.newInstance();

        String url="jdbc:mysql://localhost:3306/test";
        Properties properties=new Properties();
        properties.setProperty("user","root");
        properties.setProperty("password","2560313");
        Connection conn= driver.connect(url,properties);
        System.out.println(conn);

    }

    //方式三 使用DriverManager替换Driver
    @Test
    public void testConnection3() throws Exception, IllegalAccessException, InstantiationException {
        //使用反射获取Driver的实现类的对象
        Class clazz=Class.forName("com.mysql.jdbc.Driver");
        Driver driver= (Driver) clazz.newInstance();

        String url="jdbc:mysql://localhost:3306/test";
        String user="root";
        String password="2560313";
        //注册驱动
        DriverManager.registerDriver(driver);

        //获取连接
        Connection conn=DriverManager.getConnection(url,user,password);
        System.out.println(conn);

    }

    //方式四 不需要显示注册驱动了 Driver中的静态代码块帮忙做了
    @Test
    public void testConnection4() throws Exception, IllegalAccessException, InstantiationException {
        String url="jdbc:mysql://localhost:3306/test";
        String user="root";
        String password="2560313";

        //使用反射获取Driver的实现类的对象
        Class.forName("com.mysql.jdbc.Driver");
//        Driver driver= (Driver) clazz.newInstance();
//
//        //注册驱动
//        DriverManager.registerDriver(driver);

        //获取连接
        Connection conn=DriverManager.getConnection(url,user,password);
        System.out.println(conn);

    }

    //方式五 将基本信息声明在配置文件中
    @Test
    public void testConnection5() throws IOException, ClassNotFoundException, SQLException {
        InputStream inputStream= ConnectionTest.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties properties=new Properties();
        properties.load(inputStream);

        String user=properties.getProperty("user");
        String password=properties.getProperty("password");
        String url=properties.getProperty("url");
        String driverClass=properties.getProperty("driverClass");

        //加载驱动
        Class.forName(driverClass);

        Connection conn=DriverManager.getConnection(url,user,password);
        System.out.println(conn);


    }



}
