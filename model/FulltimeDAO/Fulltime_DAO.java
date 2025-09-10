package model.FulltimeDAO;

import util.DBUtil;
import vo.Fulltime;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class Fulltime_DAO implements Fulltime_Interface {

    @Override
    public int input(Fulltime fulltime) {
        String sql = "insert into fulltime(empno, name, result, basicsalary) values(?,?,?,?)";

        try(Connection conn = DBUtil.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, fulltime.getEmpno());
            ps.setString(2, fulltime.getName());
            ps.setInt(3, fulltime.getResult());
            ps.setInt(4, fulltime.getBasicsalary());

            return ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int delete(Fulltime entity) {
        return 0;
    }

    @Override
    public int update(Fulltime entity) {
        return 0;
    }

    @Override
    public List<Fulltime> select_All() throws SQLException {
        return List.of();
    }

    @Override
    public Optional<Fulltime> select(String s) {
        return Optional.empty();
    }
}
