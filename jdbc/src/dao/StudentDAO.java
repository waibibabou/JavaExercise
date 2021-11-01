package dao;

import bean.Student;

import java.sql.Connection;
import java.sql.SQLException;

public interface StudentDAO {
    void insert(Connection conn, Student student) throws Exception;
    void deleteById(Connection conn,int id) throws Exception;
    void updateById(Connection conn,Student student) throws Exception;
    Student getStudentById(Connection conn,int id) throws Exception;
    Long getCount(Connection conn) throws SQLException;
}
