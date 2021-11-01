package dao;

import bean.Student;
import org.junit.Test;
import util.JDBCUtils;

import java.sql.Connection;

public class StudentDAOImpTest {
    StudentDAOImp studentDAOImp=new StudentDAOImp();
    @Test
    public void test1() throws Exception {
        Connection conn= JDBCUtils.getConnection();
        Student student=new Student(1,"1","1");
        studentDAOImp.insert(conn,student);
        JDBCUtils.closeResource(conn,null);
    }

    @Test
    public void test2() throws Exception {
        Connection conn=JDBCUtils.getConnection();
        studentDAOImp.deleteById(conn,1);
    }
    @Test
    public void test3() throws Exception {
        Connection conn=JDBCUtils.getConnection();
        Student student=new Student(2,"1","1");
        studentDAOImp.updateById(conn,student);
    }
    @Test
    public void test4() throws Exception {
        Connection conn=JDBCUtils.getConnection();
        System.out.println(studentDAOImp.getStudentById(conn,2));
    }

    @Test
    public void test5() throws Exception {
        Connection conn=JDBCUtils.getConnection();
        System.out.println(studentDAOImp.getCount(conn));
    }

}
