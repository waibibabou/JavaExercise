package connection;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class C3P0Test {
    @Test
    public void testC3P0() throws Exception {
        ComboPooledDataSource cpds=new ComboPooledDataSource();
        cpds.setDriverClass("com.mysql.jdbc.Driver");
        cpds.setJdbcUrl("jdbc:mysql://localhost:3306/test");
        cpds.setUser("root");
        cpds.setPassword("2560313");

        //设置初始连接池中的连接数
        cpds.setInitialPoolSize(10);
        Connection conn=cpds.getConnection();
        System.out.println(conn);
    }

    @Test
    public void testC3P01() throws SQLException {
        ComboPooledDataSource cpds=new ComboPooledDataSource("helloc3p0");
        Connection conn=cpds.getConnection();


    }

}
