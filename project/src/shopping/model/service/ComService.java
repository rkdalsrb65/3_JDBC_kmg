package shopping.model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;

import shopping.model.dao.ComDAO;
import shopping.vo.Com;


public class ComService {

	private ComDAO dao = new ComDAO();

	/** 댓글 등록 서비스
	 * @param comment
	 * @return result
	 * @throws Exception
	 */
	public int insertCom(Com com) throws Exception{
		
		Connection conn = getConnection(); // 커넥션 생성		

		// DAO 메서드 호출 후 결과 반환 받기
		int result = dao.insertCom(conn, com);
		
		if(result > 0)  commit(conn);
		else			rollback(conn);
		
		close(conn); // 커넥션 반환		
		
		return result; // 결과 반환
	}

	/** 댓글 수정 서비스
	 * @param commentNo
	 * @param content
	 * @return result
	 * @throws Exception
	 */
	public int updateCom(int comNo, String content) throws Exception{
		
		Connection conn = getConnection(); // 커넥션 생성		

		// DAO 메서드 호출 후 결과 반환 받기
		int result = dao.updateCom(conn, comNo, content);
		
		if(result > 0)  commit(conn);
		else			rollback(conn);
		
		close(conn); // 커넥션 반환		
		
		return result; // 결과 반환
		
	}

	/** 댓글 삭제 서비스
	 * @param commentNo
	 * @return result
	 * @throws Exception
	 */
	public int deleteCom(int comNo) throws Exception{
		Connection conn = getConnection(); // 커넥션 생성		

		// DAO 메서드 호출 후 결과 반환 받기
		int result = dao.deleteCom(conn, comNo);
		
		if(result > 0)  commit(conn);
		else			rollback(conn);
		
		close(conn); // 커넥션 반환		
		
		return result; // 결과 반환
	}	
	
	
	
}
