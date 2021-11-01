package dbutils;

import bean.Student;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;
import util.JDBCUtils;

import java.sql.Connection;
import java.util.List;

public class QueryRunnerTest {
    @Test
    public void testInsert() throws Exception {
        QueryRunner runner=new QueryRunner();
        Connection conn= JDBCUtils.getConnection();
        String sql="insert into student(name,password) values(?,?)";

        int insertCount=runner.update(conn,sql,"111","111");
        System.out.println(insertCount);
        //使用DbUtils关闭资源
        DbUtils.closeQuietly(conn);

    }

    @Test
    public void testQuery() throws Exception {
        QueryRunner runner=new QueryRunner();
        Connection conn= JDBCUtils.getConnection();
        String sql="select* from student";
        BeanListHandler<Student> handler=new BeanListHandler<>(Student.class);
        List<Student> student= runner.query(conn,sql,handler);
        System.out.println(student);
    }

}
