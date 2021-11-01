package util;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * 工具类
 */
public class JDBCUtils {

    public static Connection getConnection() throws Exception {
        InputStream inputStream= ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");
        Properties properties=new Properties();
        properties.load(inputStream);

        String user=properties.getProperty("user");
        String password=properties.getProperty("password");
        String url=properties.getProperty("url");
        String driverClass=properties.getProperty("driverClass");

        //加载驱动
        Class.forName(driverClass);

        Connection conn= DriverManager.getConnection(url,user,password);
        return conn;
    }

    public static void closeResource(Connection conn, Statement ps) throws SQLException {

        try {
            if(conn!=null){
                conn.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            if(ps!=null){
                ps.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void closeResource(Connection conn, Statement ps, ResultSet resultSet) throws SQLException {
        try {
            if(conn!=null)
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            if(ps!=null)
            ps.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            if(resultSet!=null)
            resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}
