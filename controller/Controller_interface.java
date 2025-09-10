package controller;

import vo.Student;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface Controller_interface <T, ID>{
    // View에서 요청한 insert 를 Service에 전달하기 위한 메서드
    public int insert(T entity) throws SQLException;
    public int update(T entity) throws SQLException;
    public int delete(T entity) throws SQLException;
    public List<T> select_All() throws SQLException;
    public Optional<T> select(ID id) throws SQLException;
}
