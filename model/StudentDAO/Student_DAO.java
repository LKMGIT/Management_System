package model.StudentDAO;

import model.Student.Student_interface;
import util.DBUtil;
import vo.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Student_DAO implements Student_interface {

    // 학생 객체를 받아와 DB에 저장하는 메소드 + 평균, 총점, 학점 계산
    public void insert(Student s) throws SQLException {
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

            cal_total(s);
            cal_average(s);
            cal_grade(s);

            int result = ps.executeUpdate();

            if (result == 1) {
                System.out.println("저장 성공");
            } else {
                System.out.println("저장 실패");
            }
        }
    }

    //학생 객체를 받아와 원하는 학번의 정보를 수정함
    public void update(Student s) throws SQLException {
        String sql = "UPDATE student SET korean=?, english=?, math=?, science=? WHERE sno=?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            List<Integer> r = s.getRecode();
            ps.setInt(1, r.get(0));
            ps.setInt(2, r.get(1));
            ps.setInt(3, r.get(2));
            ps.setInt(4, r.get(3));
            ps.setString(5, s.getS_number());

            int result = ps.executeUpdate();

            if (result == 1) {
                System.out.println("수정 완료");
            }else{
                System.out.println("수정 실패");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 학번을 인자로 받아 해당 학번에 해당하는 컬럼 제거
    public void delete(String s_number) throws SQLException {
        String sql = "DELETE FROM student WHERE sno = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, s_number);

            int result = ps.executeUpdate();

            if (result == 1) {
                System.out.println("삭제 성공");
            }else{
                System.out.println("삭제 실패");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //초기, 수정된 DB 상태를 불러오기 위한 Connect, 리스트안에 학생들의 정보를 저장
    public void Connect(List<Student> students) throws SQLException {
        String sql = "SELECT * FROM student";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Student s = new Student();
                List<Integer> r = new ArrayList<>();
                s.setS_number(rs.getString("sno"));
                s.setName(rs.getString("name"));
                r.add(rs.getInt("korean"));
                r.add(rs.getInt("english"));
                r.add(rs.getInt("math"));
                r.add(rs.getInt("science"));
                s.setRecode(r);
                students.add(s);
                Collections.sort(students);
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 학생 한 명의 정보를 출력하는 메소드 학번을 인자값으로 받아 해당하는 학생의 정보를 출력
    public void select_student(String sno) throws SQLException {
        String sql = "SELECT * FROM student WHERE sno = ?";

        try (Connection conn = DBUtil.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, sno);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println("");
                System.out.println("학번: " + rs.getString("sno")
                        + " " +"이름: " + rs.getString("name")
                        + " " + "국어성적: " + rs.getString("korean")
                        + " " + "영어성적: " + rs.getString("english")
                        + " " + "수학성적: " + rs.getString("math")
                        + " " + "과학성적: " + rs.getString("science"));
            }
        }
    }

    // 모든 학생의 정보를 출력하는 메소드
    public void select_All() throws SQLException {
        String sql = "SELECT * FROM student";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                System.out.println("학번: " + rs.getString("sno")
                + " " +"이름: " + rs.getString("name")
                + " " + "국어성적: " + rs.getString("korean")
                + " " + "영어성적: " + rs.getString("english")
                + " " + "수학성적: " + rs.getString("math")
                + " " + "과학성적: " + rs.getString("science"));
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
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
    public void cal_average(Student o)
    {
        int result = o.getTotal() / o.getRecode().size();
        o.setAverage(result);
    }

    //학점 구하기
    public void cal_grade(Student o) {
        int score = (int) o.getAverage();
        if (score >= 90) {
            o.setGrade("A");
        }else if (score >= 80) {
            o.setGrade("B");
        }else if (score >= 70) {
            o.setGrade("C");
        }else if (score >= 60) {
            o.setGrade("D");
        }else{
            o.setGrade("F");
        }
    }
}
