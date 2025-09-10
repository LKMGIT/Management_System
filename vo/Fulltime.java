package vo;


import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Fulltime extends Employee {

    private int result;         /// 성과
    private int basicsalary;    /// 월급

}
