package edu.kh.jdbc.main.model.service;

import static edu.kh.jdbc.common.JDBCTemplate.*;

import java.sql.Connection;

import edu.kh.jdbc.common.JDBCTemplate;
import edu.kh.jdbc.main.model.dao.MainDAO;

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
		
		return 0;
		
	}
	
	
	
}