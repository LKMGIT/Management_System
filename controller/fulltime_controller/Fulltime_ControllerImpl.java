package controller.fulltime_controller;

import model.FulltimeDAO.Fulltime_DAO;
import vo.Fulltime;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class Fulltime_ControllerImpl implements Fulltime_Controller_Interface {

    Fulltime_DAO fulltime_DAO = new Fulltime_DAO();

    @Override
    public int insert(Fulltime entity) throws SQLException {
        return fulltime_DAO.input(entity);
    }

    @Override
    public int update(Fulltime entity) throws SQLException {
        return fulltime_DAO.update(entity);
    }

    @Override
    public int delete(Fulltime entity) throws SQLException {
        return fulltime_DAO.delete(entity);
    }

    @Override
    public List<Fulltime> select_All() throws SQLException {
        return fulltime_DAO.select_All();
    }

    @Override
    public Optional<Fulltime> select(String s) throws SQLException {
        return fulltime_DAO.select(s);
    }
}
