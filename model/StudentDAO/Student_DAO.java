package model.StudentDAO;

import util.DBUtil;
import vo.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Student_DAO implements Student_interface {

    @Override
    public int input(Student s) {
        String sql = "INSERT INTO student (sno, name, korean, english, math, science) VALUES (?,?,?,?,?,?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            List<Integer> r = s.getRecode(); //리스트 받아오기
            ps.setString(1, s.getS_number());
            ps.setString(2, s.getName());
            ps.setInt(3, r.get(0));  // 국어
            ps.setInt(4, r.get(1));  // 영어
            ps.setInt(5, r.get(2));  // 수학
            ps.setInt(6, r.get(3));  // 과학

            return ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int delete(Student s) {
        String sql = "DELETE FROM student WHERE sno = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, s.getS_number());

            return ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(Student s) {
        String sql = "UPDATE student SET korean=?, english=?, math=?, science=? WHERE sno=?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            List<Integer> r = s.getRecode();
            ps.setInt(1, r.get(0));
            ps.setInt(2, r.get(1));
            ps.setInt(3, r.get(2));
            ps.setInt(4, r.get(3));
            ps.setString(5, s.getS_number());

            return ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Student> select_All() throws SQLException {
        List<Student> list = new ArrayList<>();

        String sql = "SELECT * FROM student";

        try(Connection conn = DBUtil.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)){

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Student student = new Student();
                List<Integer> recode = new ArrayList<>();
                student.setS_number(rs.getString("sno"));
                student.setName(rs.getString("name"));

                recode.add(rs.getInt("korean"));
                recode.add(rs.getInt("english"));
                recode.add(rs.getInt("math"));
                recode.add(rs.getInt("science"));
                student.setRecode(recode);

                cal_total(student);
                cal_average(student);
                cal_grade(student);

                list.add(student);
            }

            return list;

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Student> select(String s) {
        List<Integer> recode = new ArrayList<>();
        String sql = "SELECT * FROM student WHERE sno = ? order by sno asc";

        try(Connection conn = DBUtil.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setString(1, s);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                Student student = new Student();
                student.setS_number(rs.getString("sno"));
                student.setName(rs.getString("name"));
                recode.add(rs.getInt("korean"));
                recode.add(rs.getInt("english"));
                recode.add(rs.getInt("math"));
                recode.add(rs.getInt("science"));
                student.setRecode(recode);
                cal_total(student);
                cal_average(student);
                cal_grade(student);
                return Optional.of(student);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }

    // 총합 구하기
    public void cal_total(Student o) {
        int total = 0;
        for (int score : o.getRecode()) {
            total += score;
            o.setTotal(total);
        }
    }

    // 평균 구하기
    public void cal_average(Student o) {
        int result = o.getTotal() / o.getRecode().size();
        o.setAverage(result);
    }

    //학점 구하기
    public void cal_grade(Student o) {
        int score = (int) o.getAverage();
        if (score >= 90) {
            o.setGrade("A");
        } else if (score >= 80) {
            o.setGrade("B");
        } else if (score >= 70) {
            o.setGrade("C");
        } else if (score >= 60) {
            o.setGrade("D");
        } else {
            o.setGrade("F");
        }
    }
}
