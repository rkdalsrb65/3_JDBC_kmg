package service;

import static common.JDBCTemplate.*;

import java.sql.Connection;

import dao.MainDAO;
import main.MainMain;

public class MainService {


		private MainDAO dao = new MainDAO();
		
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
		
		
		
		
		public int signUp(MainMain mainMain) throws Exception {
			
			// 1. Connection 생성
			Connection conn = getConnection();
			
			// 2. DAO 메서드 호출 후 결과 반환 받기
			int result = dao.signUp(conn, mainMain);
			
			// 3. 트랜잭션 제어 처리
			if(result > 0)commit(conn); // conn.commit();
			else 		rollback(conn);
			
			// 4. Connection 반환
			close(conn);
			// 5. 삽입 결과 반환
			return result;
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}


