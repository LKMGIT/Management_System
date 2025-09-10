package view;

import controller.student_controller.Student_ControllerImpl;
import vo.Student;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Student_View {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static Student_ControllerImpl controller = new Student_ControllerImpl();


    public static void student_page() throws IOException, SQLException {
        int num = 0;
        boolean flag = true;
        while (flag) {
            System.out.println("");
            System.out.print("""
                    =============[학생 관리 시스템]==============
                    ===============[메뉴 페이지]================
                    
                     1. 학생 정보 입력       2. 학생 정보 수정
                     3. 학생 정보 삭제       4. 학생 정보 전체 조회
                     5. 학생 단일 정보 조회   6. 종료 
                    
                    ==========================================
                    [번호를 입력해주세요]: """);

            num = Integer.parseInt(br.readLine());

            switch (num) {
                case 1 -> input_student();
                case 2 -> update_student();
                case 3 -> delete_student();
                case 4 -> select_All();
                case 5 -> select_student();
                case 6 -> {
                    System.out.println("학생 관리 프로그램 종료");
                    flag = false;
                }
            }
        }
    }

    public static void input_student() throws IOException {
        List<Student> students = new ArrayList<>();
        while (true) {
            /// 학생 객체 생성
            Student s = new Student();
            /// 점수를 저장하기 위한 리스트 생성
            List<Integer> recode = new ArrayList<>();

            System.out.println("[학생 정보를 입력하세요]");
            System.out.println("[학번에 0 입력시 학생 관리 프로그램 종료]");

            /// 학생 학번 입력
            System.out.print("학번: ");
            String sno = br.readLine();

            if (sno == null) return;                 // EOF면 종료
            sno = sno.trim();                        // 공백 제거

            if ("0".equals(sno)) {
                System.out.println("입력을 종료합니다.");
                saveData(students);
                return;
            }
            if (sno.isEmpty()) {                     // 빈 값 방지
                System.out.println("학번은 비울 수 없습니다. 다시 입력하세요.");
                continue;
            }

            s.setS_number(sno);

            ///  이름 및 점수 입력
            System.out.print("이름: ");
            String name = br.readLine();
            s.setName(name);

            System.out.print("국어: ");
            int len_score = Integer.parseInt(br.readLine());
            if (!checkScore(len_score)) {
                continue;
            }
            System.out.print("영어: ");
            int english_score = Integer.parseInt(br.readLine());
            if (!checkScore(english_score)) {
                continue;
            }
            System.out.print("수학: ");
            int math_score = Integer.parseInt(br.readLine());
            if (!checkScore(math_score)) {
                continue;
            }

            System.out.print("과학: ");
            int science_score = Integer.parseInt(br.readLine());
            if (!checkScore(science_score)) {
                continue;
            }

            recode.add(len_score);
            recode.add(english_score);
            recode.add(math_score);
            recode.add(science_score);
            s.setRecode(recode);

            students.add(s);

        }

    }

    // 컨트롤러 불러와서 DAO를 통해 DB에 저장시키는 메소드
    public static void saveData(List<Student> students) {
        int ok = 0, fail = 0;
        for (Student student : students) {
            try {
                controller.insert(student);
                ok++;
            } catch (Exception e) {
                fail++;
                System.out.println("[에러] " + student.getName() + " 저장 실패: " + e.getMessage());
            }
        }
        System.out.println("[완료] " + ok + "명 DB 저장 성공" + (fail > 0 ? (", 실패 " + fail + "명") : ""));
    }


    public static void select_student() throws SQLException, IOException {
        Optional<Student> student = null;
        List<Integer> recode = null;

        String sno = "";
        System.out.println("[학생 정보 단일 조회 메뉴]");
        System.out.print("조회할 학생의 학번을 입력하세요: ");
        sno = br.readLine();
        student = controller.select(sno);
        recode = student.get().getRecode();

        System.out.println("학번: " + student.get().getS_number()
                + " " + "이름: " + student.get().getName()
                + " " + "국어성적: " + recode.get(0)
                + " " + "영어성적: " + recode.get(1)
                + " " + "수학성적: " + recode.get(2)
                + " " + "과학성적: " + recode.get(3)
                + " " + "총합: " + student.get().getTotal()
                + " " + "평균 : " + student.get().getAverage());


    }

    public static void select_All() throws SQLException {
        List<Integer> recode = null;

        List<Student> students = controller.select_All();

        for (Student student : students) {
            recode = student.getRecode();
            System.out.println("학번: " + student.getS_number()
                    + " " + "이름: " + student.getName()
                    + " " + "국어성적: " + recode.get(0)
                    + " " + "영어성적: " + recode.get(1)
                    + " " + "수학성적: " + recode.get(2)
                    + " " + "과학성적: " + recode.get(3)
                    + " " + "총합: " + student.getTotal()
                    + " " + "평균 : " + student.getAverage());
        }

    }

    public static void delete_student() throws SQLException, IOException {
        Student student = new Student();
        System.out.println("[학생 정보 삭제 메뉴]");
        System.out.print("삭제할 학생의 학번을 입력하세요: ");
        String sno = br.readLine();
        student.setS_number(sno);

        if (controller.delete(student) > 0) {
            System.out.println("삭제 성공!");
        } else {
            System.out.println("삭제 실패");
        }
    }

    public static void update_student() throws SQLException, IOException {
        Student student = new Student();

        System.out.println("[학생 정보 수정 메뉴]");
        System.out.print("수정할 학생의 학번을 입력하세요: ");
        String sno = br.readLine();
        student.setS_number(sno);

        // 그 학번을 타겟에 할당해서 수정한 점수를 저장하고
        while (true) {
            System.out.print("국어: ");
            int len_score = Integer.parseInt(br.readLine());
            if (!checkScore(len_score)) {
                continue;
            }
            System.out.print("영어: ");
            int english_score = Integer.parseInt(br.readLine());
            if (!checkScore(english_score)) {
                continue;
            }
            System.out.print("수학: ");
            int math_score = Integer.parseInt(br.readLine());
            if (!checkScore(math_score)) {
                continue;
            }

            System.out.print("과학: ");
            int science_score = Integer.parseInt(br.readLine());
            if (!checkScore(science_score)) {
                continue;
            }

            List<Integer> recode = new ArrayList<>();
            recode.add(len_score);
            recode.add(english_score);
            recode.add(math_score);
            recode.add(science_score);
            student.setRecode(recode);
            break;
        }

        // 그 객체를 controller.update(target)에 넣으면 끝인데
        if (controller.update(student) > 0) {
            System.out.println("수정 성공");
        } else {
            System.out.println("수정 실패");
        }

    }


    public static boolean checkScore(int score) {
        if (score < 0 || score > 100) {
            System.out.println("점수는 0 ~ 100 사이여야 합니다.");
            return false;
        } else {
            return true;
        }
    }


}
