package Employee.model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;

import Employee.model.dao.EmployeeDAO;
import Employee.vo.Employee;

public class EmployeeService {
	
	private EmployeeDAO dao = new EmployeeDAO();

	/** 로그인 서비스
	 * @param employeeId
	 * @param employeePw
	 * @return loginEmployee
	 * @throws Exception
	 */
	public Employee login(String employeeId, String employeePw) throws Exception {
		
		// 1. Connection 생성
		Connection conn = getConnection();

		// 2. DAO 메서드 호출 후 결과 반환 받기
		Employee loginEmployee = dao.login(conn, employeeId, employeePw);

		// 3. Connection 반환 (SELECT 구문은 트랜잭션 제어 필요 X)
		close(conn);

		// 4. 조회 결과 반환
		return loginEmployee;
	}

	/** 회원 가입 서비스
	 * @param employee
	 * @return result
	 * @throws Exception
	 */
	public int signUp(Employee employee) throws Exception {
		
		// 1. Connection 생성
		Connection conn = getConnection();
		
		// 2. DAO 메서드 호출 후 결과 반환 받기
		int result = dao.signUp(conn, employee);
		
		// 3. 트랜잭션 제어 처리
		if(result > 0)commit(conn); // conn.commit();
		else 		rollback(conn);
		
		// 4. Connection 반환
		close(conn);
		// 5. 삽입 결과 반환
		return result;
	}

	/** 아이디 중복 확인(체크) 서비스
	 * @param employeeId
	 * @return result
	 * @throws Exception
	 */
	public int idDupCheck(String employeeId) throws Exception { 
		
		// 1. Connection 생성
		Connection conn = getConnection();
		
		// 2. DAO 메서드 호출 후 결과 반환 받기
		int result = dao.idDupCheck(conn, employeeId);
		
		// 3. Connection 반환 (SELECT 구문은 트랜잭션 제어 필요 X)
		close(conn);
		
		// 4. 조회 결과 반환
		return result;
	}

	/** 이름으로 아이디 찾기 서비스
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

	/** 아이디 + 이름으로 비밀번호 찾기 서비스
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
