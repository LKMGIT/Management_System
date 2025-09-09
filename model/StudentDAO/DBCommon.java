package model.StudentDAO;

// 데이터베이스와 관련된 공통 작업을 정의 한다.
// 정보 입력, 삭제, 수정, 검색, 정렬 등의 기능의 표준을 제시한다.
public interface DBCommon {
    /*
    새로운 데이터를 입력하는 메서드
    @param Object
     */
    void input(Object o);
    void delete(Object o);
    void uppdate(Object o);
    void select_All(String sno);
    void sort(int sortNum);
}
