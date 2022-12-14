package edu.kh.jdbc.main.model.service;

import static edu.kh.jdbc.common.JDBCTemplate.*;

import java.sql.Connection;

import edu.kh.jdbc.common.JDBCTemplate;
import edu.kh.jdbc.main.model.dao.MainDAO;
import edu.kh.jdbc.member.vo.Member;

// Service : 데이터 가공, 트랜잭션 제어 처리
public class MainService {
	
	private MainDAO dao = new MainDAO();

	/** 아이디 중복 검사
	 * @param memberId
	 * @return
	 */
	public int idDupCheck(String memberId) throws Exception {
		
		// 1. Connection 생성
		Connection conn = getConnection();
		
		// 2. DAO 메서드 호출 후 결과 반환 받기
		int result = dao.idDupCheck(conn, memberId);
		
		// 3. Connection 반환 (SELECT 구문은 트랜잭션 제어 필요 X)
		close(conn);
		
		// 4. 조회 결과 반환
		return result;
		
	}

	/** 회원 가입 서비스
	 * @param member
	 * @return result
	 * @throws Exception
	 */
	public int signUp(Member member) throws Exception {
		
		// 1. Connection 생성
		Connection conn = getConnection();
		
		// 2. DAO 메서드 호출 후 결과 반환 받기
		int result = dao.signUp(conn, member);
		
		// 3. 트랜잭션 제어 처리
		if(result > 0)commit(conn); // conn.commit();
		else 		rollback(conn);
		
		// 4. Connection 반환
		close(conn);
		// 5. 삽입 결과 반환
		return result;
	}

	/** 로그인 서비스
	 * @param memberId
	 * @param memberPw
	 * @return loginMember
	 * @throws Exception
	 */
	public Member login(String memberId, String memberPw) throws Exception {

		// 1. Connection 생성
		Connection conn = getConnection();

		// 2. DAO 메서드 호출 후 결과 반환 받기
		Member loginMember = dao.login(conn, memberId, memberPw);

		// 3. Connection 반환 (SELECT 구문은 트랜잭션 제어 필요 X)
		close(conn);

		// 4. 조회 결과 반환
		return loginMember;
	}
	
	
	
	/** 아이디 찾기
	 * @param userName
	 * @return userId
	 * @throws Exception
	 */
	public String findId(String userName) throws Exception {
		
		Connection conn = getConnection();
		
		String userId = dao.findId(conn, userName);
		
		close(conn);
		
		return userId;
	}
	
	/** 비밀번호 찾기
	 * @param userId
	 * @param userName
	 * @return userPw
	 * @throws Exception
	 */
	public String findPw(String userId, String userName) throws Exception {
		
	     Connection conn = getConnection();
	     
	     String userPw = dao.findPw(conn, userId, userName);
	     
	     close(conn);
	     
	     return userPw;
	  }


	
}