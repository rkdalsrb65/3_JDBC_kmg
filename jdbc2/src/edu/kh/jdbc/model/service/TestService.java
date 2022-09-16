package edu.kh.jdbc.model.service;

import java.sql.Connection;

import edu.kh.jdbc.common.JDBCTemplate;
import edu.kh.jdbc.model.dao.TestDAO;
import edu.kh.jdbc.model.vo.TestVO;

// Service : 비즈니스 로직(데이터 가공, 트랜잭션 제어) 처리
// -> 실제 프로그램이 제공하는 기능을 모아놓은 클래스

// 하나의 Service 메서드에서 n개의 DAO 메서드(지정된 SQL 수행)를 호출하여
// 이를 하나의 트랜잭션 단위로 취급하여 한번에 commit, rollback을 수행할 수 있다. 

// * 여러 DML을 수행하지 않는 경우(단일 DML, SELECT 등)라도
// 코드의 통일성을 지키기 위해서 Service에서 Connection 객체를 생성한다.
// -> Connection 객체가 commit, rollback 메서드 제공

public class TestService {

	private TestDAO dao = new TestDAO();
	
	/** 1행 삽입 메서드
	 * @param vo1
	 * @return result
	 */
	public int insert(TestVO vo1) {
		
		// 커넥션 생성
		
		Connection conn = JDBCTemplate.getConnection();
							//클래스명.메서드명
		// INSERT DAO를 메서드를 호출하여 수행 후 결과 반환 받기
		// -> Service에서 생성한 Connection 객체를 반드시 같이 전달해야한다!!!!
		int result = dao.insert(conn, vo1);
		// result == SQL 수행 후 반영된 결과 행의 개수
		
		if(result > 0) JDBCTemplate.commit(conn);
		else		   JDBCTemplate.rollback(conn);
		
		// 커넥션 반환(close)
		JDBCTemplate.close(conn);
		
		return result;
	}
	// 아메리카노 제조
	// 콜드브루 제조
	// 카페라떼 제조
}