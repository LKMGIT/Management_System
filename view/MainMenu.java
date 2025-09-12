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

public class MainMenu {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static boolean flag = true;


    public static void main(String[] args) throws IOException, SQLException {
        while (flag) {
            printUsage();
        }
    }

    public static void printUsage() throws IOException, SQLException {
        int num = 0;
        System.out.println("");
        System.out.print("""
                ================== [관리 프로그램] ===================
                =================== [메뉴 페이지] ===================
                
                     1. 학생 관리 시스템          2. 정규직 관리 시스템 
                
                     3. 알바 관리 시스템          4. 프로그램 종료
                
                ====================================================
                [번호를 입력하세요]: """);
        num = Integer.parseInt(br.readLine());

        switch (num) {
            case 1 -> Student_View.student_page();
            case 2 -> Fulltime_View.fulltime_page();
           // case 3 -> Parttime_View.parttime_page();
            case 4 -> {
                System.out.println("프로그램 종료");
                flag = false;
            }
        }
    }



}
