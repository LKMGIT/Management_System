package model.FulltimeDAO;

import util.DBUtil;
import vo.Fulltime;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        String sql = "delete from fulltime where empno = ?";

        try(Connection conn = DBUtil.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, entity.getEmpno());

            return ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(Fulltime entity) {
        String sql = "Update fulltime set basicsalary = ? where empno = ?";

        try(Connection conn = DBUtil.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, entity.getBasicsalary());
            ps.setString(2, entity.getEmpno());

            return ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Fulltime> select_All() throws SQLException {
        List<Fulltime> list = new ArrayList<>();
        String sql = "select * from fulltime";
        try(Connection conn = DBUtil.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Fulltime fulltime = new Fulltime();
                fulltime.setEmpno(rs.getString("empno"));
                fulltime.setName(rs.getString("name"));
                fulltime.setResult(rs.getInt("result"));
                fulltime.setBasicsalary(rs.getInt("basicsalary"));
                list.add(fulltime);
            }

            return list;
        }

    }

    @Override
    public Optional<Fulltime> select(String s) {
        String sql = "select * from fulltime where empno = ?";

        try(Connection conn = DBUtil.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, s);

            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                Fulltime fulltime = new Fulltime();
                fulltime.setEmpno(rs.getString("empno"));
                fulltime.setName(rs.getString("name"));
                fulltime.setResult(rs.getInt("result"));
                fulltime.setBasicsalary(rs.getInt("basicsalary"));
                return Optional.of(fulltime);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }
}
