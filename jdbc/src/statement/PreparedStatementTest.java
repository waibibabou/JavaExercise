package statement;

import bean.Student;
import org.junit.Test;
import util.JDBCUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.sql.*;

public class PreparedStatementTest {

    //通用的增删改操作
    public void update(String sql,Object ...args)throws Exception{
        Connection conn=JDBCUtils.getConnection();

        PreparedStatement ps=conn.prepareStatement(sql);
        //填充占位符
        for(int i=0;i<args.length;i++){
            ps.setObject(i+1,args[i]);
        }
        ps.execute();
        JDBCUtils.closeResource(conn,ps);

    }

    //考虑事务后的增删改操作
    public void update(Connection conn,String sql,Object ...args)throws Exception{

        PreparedStatement ps=conn.prepareStatement(sql);
        //填充占位符
        for(int i=0;i<args.length;i++){
            ps.setObject(i+1,args[i]);
        }
        ps.execute();
        JDBCUtils.closeResource(null,ps);

    }

    @Test
    public void testCommonUpdate() throws Exception {
        String sql="delete from student where id=?";
        update(sql,4);
    }

    @Test
    public void testQuery1() throws Exception{
        Connection conn=JDBCUtils.getConnection();

        String sql="select* from student where name=?";
        PreparedStatement ps=conn.prepareStatement(sql);
        ps.setString(1,"zyk");
        //执行sql语句
        ResultSet resultSet= ps.executeQuery();
        //处理结果集
        while(resultSet.next()){//如果下一条有数据则返回true 并且指针下移
            int id=resultSet.getInt(1);
            String name=resultSet.getString(2);
            String password=resultSet.getString(3);

            Student student=new Student(id,name,password);
            System.out.println(student);
        }

        JDBCUtils.closeResource(conn,ps,resultSet);
    }

    //通用的查询操作
    public Student queryForStudent(String sql,Object...args) throws Exception {
        Connection conn= JDBCUtils.getConnection();

        PreparedStatement ps=conn.prepareStatement(sql);
        for(int i=0;i<args.length;i++){
            ps.setObject(i+1,args[i]);
        }
        ResultSet rs=ps.executeQuery();

        //获取resultSet的列数
        ResultSetMetaData rsmd=rs.getMetaData();
        int columnCount=rsmd.getColumnCount();

        Student student=new Student();
        if(rs.next()){
            for(int i=0;i<columnCount;i++){
                Object object= rs.getObject(i+1);

                //获取列的别名
                String columnName=rsmd.getColumnLabel(i+1);
                //通过反射对对象中的指定属性赋值
                Field field=student.getClass().getDeclaredField(columnName);
                field.setAccessible(true);
                field.set(student,object);
            }

        }
        JDBCUtils.closeResource(conn,ps,rs);
        return student;

    }

    @Test
    public void testCommonQuery() throws Exception {
        String sql="select id,name from student where name=?";
        System.out.println(queryForStudent(sql,"zyk"));
    }

    @Test
    public void test() throws Exception {
        FileInputStream fileInputStream=new FileInputStream(new File("壁纸.jpg"));
        FileOutputStream fileOutputStream=new FileOutputStream(new File("壁纸1.jpg"));
        byte[] buffer=new byte[1024];
        int len;
        while((len=fileInputStream.read(buffer))!=-1){
            fileOutputStream.write(buffer,0,len);
        }
        fileInputStream.close();
        fileOutputStream.close();

    }

    /**
     * 批量插入数据
     * @throws Exception
     */
    @Test
    public void insertTest() throws Exception {
        Connection conn=JDBCUtils.getConnection();
        String sql="insert into student(name,password) values(?,?)";
        PreparedStatement ps=conn.prepareStatement(sql);
        for(int i=0;i<100;i++){
            ps.setObject(1,"name_"+i);
            ps.setObject(2,"password_"+i);
            ps.addBatch();
            if(i%10==0){//批处理减少与数据库交互时间
                ps.executeBatch();

                ps.clearBatch();
            }
        }
        JDBCUtils.closeResource(conn,ps);

    }

    @Test
    public void testUpdateWithTx() throws Exception {
        Connection conn= null;
        try {
            conn = JDBCUtils.getConnection();

            //取消数据的自动提交
            conn.setAutoCommit(false);

            String sql1="update student set name='0'where id=?";
            update(conn,sql1,1);

            System.out.println(1/0);

            String sql2="update student set name='00'where id=?";
            update(conn,sql2,2);

            //提交数据
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } finally {
            conn.setAutoCommit(true);
            JDBCUtils.closeResource(conn,null);
        }


    }


}
