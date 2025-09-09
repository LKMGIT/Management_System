package vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Student extends Person implements Comparable<Student>{
    List<Integer> recode = new ArrayList<>();               // 점수 배열
    private String s_number;
    private int total;                  // 총합 점수
    private double average;              // 점수 평균
    private String grade;              // 학생 학점

    @Override
    public String toString() {
        return super.getName() + "(총점 =" + total + ", " + "평균 =" + average + ", " + "학점 = " + grade + ")";
    }

    @Override
    public boolean equals(Student o) {
        if(this.s_number.equals(o.getS_number())){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public int compareTo(Student o) {
       return this.s_number.compareTo(o.getS_number());
    }
}
