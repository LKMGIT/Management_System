package controller;

import model.StudentDAO.Student_DAO;
import vo.Student;

import java.sql.SQLException;
import java.util.List;

public class ControllerImpl implements Controller_interface {
    Student_DAO dao = new Student_DAO();

    @Override
    public void insert(Student s) throws SQLException {
        dao.insert(s);
    }

    @Override
    public void update(Student s) throws SQLException {
        dao.update(s);
    }

    @Override
    public void delete(String sno) throws SQLException {
        dao.delete(sno);
    }

    @Override
    public void Connect(List<Student> students) throws SQLException {
        dao.Connect(students);
    }

    @Override
    public void select(String sno) throws SQLException {
        dao.select_student(sno);
    }

    @Override
    public void select_All() throws SQLException {
        dao.select_All();
    }
}
