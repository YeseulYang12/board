package service;

import java.util.List;
import java.util.Map;

//제너릭 인터페이스
public interface DaoService<T> {
	void close(); //데이터베이스연결시 해제
	List<T> selectList(Map map); //전체글(전체목록) 가지고오기
	int getTotalRecordCount(Map map); //전체레코드수
	int insert(T dto); //입력용
	T selectOne(String ... one); //상세보기용(글 하나)
	int update(T dto); //수정용
	int delete(String no); //삭제용
	
}
