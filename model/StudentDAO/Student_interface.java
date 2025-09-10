package model.StudentDAO;

import model.DBCommon;
import vo.Student;

public interface Student_interface extends DBCommon<Student, String> {
    int update(Student s);

    public void cal_total(Student student);
    // 평균 구하기
    public void cal_average(Student student);
    //학점 구하기
    public void cal_grade(Student student);
}
