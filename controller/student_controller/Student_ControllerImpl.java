package controller.student_controller;

import model.StudentDAO.Student_DAO;
import vo.Student;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class Student_ControllerImpl implements Student_Controller_Interface {
    Student_DAO dao = new Student_DAO();

    @Override
    public int insert(Student entity) throws SQLException {
        return dao.input(entity);
    }

    @Override
    public int update(Student entity) throws SQLException {
        return dao.update(entity);
    }

    @Override
    public int delete(Student entity) throws SQLException {
        return dao.delete(entity);
    }

    @Override
    public List<Student> select_All() throws SQLException {
        return dao.select_All();
    }

    @Override
    public Optional<Student> select(String s) throws SQLException {
        return dao.select(s);
    }
}
