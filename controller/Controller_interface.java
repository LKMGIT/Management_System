package controller;

import vo.Student;

import java.sql.SQLException;
import java.util.List;

public interface Controller_interface {
    // View에서 요청한 insert 를 Service에 전달하기 위한 메서드
    public void insert(Student s) throws SQLException;
    public void update(Student s) throws SQLException;
    public void delete(String sno) throws SQLException;
    public void select_All() throws SQLException;
    public void select(String sno) throws SQLException;
    public void Connect(List<Student> students) throws SQLException;
}
