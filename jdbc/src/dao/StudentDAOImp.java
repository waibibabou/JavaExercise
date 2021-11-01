package dao;

import bean.Student;

import java.sql.Connection;
import java.sql.SQLException;

public class StudentDAOImp extends BaseDAO implements StudentDAO{
    @Override
    public void insert(Connection conn, Student student) throws Exception {
        String sql="insert into student(name,password) values(?,?)";
        update(conn,sql,student.getName(),student.getPassword());

    }

    @Override
    public void deleteById(Connection conn, int id) throws Exception {
        String sql="delete from student where id=?";
        update(conn,sql,id);
    }

    @Override
    public void updateById(Connection conn, Student student) throws Exception {
        String sql="update student set name=?, password=? where id=?";
        update(conn,sql,student.getName(),student.getPassword(),student.getId());
    }

    @Override
    public Student getStudentById(Connection conn, int id) throws Exception {
        String sql="select *from student where id=?";
        return queryForStudent(conn,Student.class,sql,id);
    }

    @Override
    public Long getCount(Connection conn) throws SQLException {
        String sql="select count(*)from student";
        return getValue(conn,sql);
    }

}
