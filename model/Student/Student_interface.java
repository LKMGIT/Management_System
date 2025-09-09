package model.Student;

import vo.Student;

public interface Student_interface {
    public void cal_total(Student student);
    // 평균 구하기
    public void cal_average(Student student);
    //학점 구하기
    public void cal_grade(Student student);
}
