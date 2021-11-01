package dao;

import util.JDBCUtils;

import java.lang.reflect.Field;
import java.sql.*;

/**
 * 封装了对数据表的增删改查的方法
 */
public abstract class BaseDAO {

    //考虑事务后的增删改操作
    public void update(Connection conn, String sql, Object ...args)throws Exception{

        PreparedStatement ps=conn.prepareStatement(sql);
        //填充占位符
        for(int i=0;i<args.length;i++){
            ps.setObject(i+1,args[i]);
        }
        ps.execute();
        JDBCUtils.closeResource(null,ps);

    }

    //通用的查询操作 返回一个对象 考虑了事务
    public <T>T queryForStudent(Connection conn,Class<T>clazz,String sql,Object...args) throws Exception {

        PreparedStatement ps=conn.prepareStatement(sql);
        for(int i=0;i<args.length;i++){
            ps.setObject(i+1,args[i]);
        }
        ResultSet rs=ps.executeQuery();

        //获取resultSet的列数
        ResultSetMetaData rsmd=rs.getMetaData();
        int columnCount=rsmd.getColumnCount();

        T t=clazz.newInstance();
        if(rs.next()){
            for(int i=0;i<columnCount;i++){
                Object object= rs.getObject(i+1);

                //获取列的别名
                String columnName=rsmd.getColumnLabel(i+1);
                //通过反射对对象中的指定属性赋值
                Field field=t.getClass().getDeclaredField(columnName);
                field.setAccessible(true);
                field.set(t,object);
            }

        }
        JDBCUtils.closeResource(null,ps,rs);
        return t;
    }

    public <E>E getValue(Connection conn,String sql,Object...args) throws SQLException {
        PreparedStatement ps= null;
        ResultSet rs= null;
        try {
            ps = conn.prepareStatement(sql);
            for(int i=0;i<args.length;i++){
                ps.setObject(i+1,args[i]);
            }
            rs = ps.executeQuery();
            if(rs.next()){
                return (E)rs.getObject(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtils.closeResource(null,ps,rs);
        }
        return null;
    }

}
