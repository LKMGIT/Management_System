package view;

import controller.fulltime_controller.Fulltime_ControllerImpl;
import vo.Fulltime;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Fulltime_View {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static Fulltime_ControllerImpl fulltime_controller = new Fulltime_ControllerImpl();

    public static void fulltime_page() throws IOException {
        int num = 0;
        boolean flag = true;
        while (flag) {
            System.out.println("");
            System.out.print("""
                    =============[정직원 관리 시스템]==============
                    ================[메뉴 페이지]=================
                    
                     1. 정직원 정보 입력       2. 정직원 정보 수정
                     3. 정직원 정보 삭제       4. 정직원 정보 전체 조회
                     5. 정직원 단일 정보 조회   6. 종료 
                    
                    ==========================================
                    [번호를 입력해주세요]: """);

            num = Integer.parseInt(br.readLine());

            switch (num) {
//                case 1 -> input_fulltime();
//                case 2 -> update_fulltime();
//                case 3 -> delete_fulltime();
//                case 4 -> select_All();
//                case 5 -> select_fulltime();
                case 6 -> {
                    System.out.println("정직원 관리 프로그램 종료");
                    flag = false;
                }
            }
        }

    }

    public static void input_fulltime() throws IOException, SQLException {
        List<Fulltime> fulltimes = new ArrayList<>();
        Fulltime fulltime = new Fulltime();

        System.out.println("[정직원 정보를 입력하세요]");
        System.out.print("직원 번호: (0 입력시 저장 후 종료)");
        String empno = br.readLine();
        if (!fulltimes.isEmpty())
            if (empno.equals("0")) {
                for (Fulltime f : fulltimes) {
                    fulltime_controller.insert(f);
                }
            }
        System.out.print("작원 이름: ");
        String name = br.readLine();
        System.out.print("직원 성과급: ");
        int result = Integer.parseInt(br.readLine());
        System.out.print("직원 월급: ");
        int basicsalary = Integer.parseInt(br.readLine());

        fulltime.setEmpno(empno);
        fulltime.setName(name);
        fulltime.setResult(result);
        fulltime.setBasicsalary(basicsalary);

        fulltimes.add(fulltime);

    }

}
