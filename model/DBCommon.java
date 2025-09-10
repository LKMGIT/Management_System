package model;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

// 데이터베이스와 관련된 공통 작업을 정의 한다.
// 정보 입력, 삭제, 수정, 검색, 정렬 등의 기능의 표준을 제시한다.

public interface DBCommon<T, ID> {
    int input(T entity);
    int delete(T entity);
    int update(T entity);
    List<T> select_All() throws SQLException;
    Optional<T> select(ID id);
}
