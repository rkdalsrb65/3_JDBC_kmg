package mem.model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import Employee.vo.Employee;
import mem.model.dao.MemDAO;

public class MemService {
	
	private MemDAO dao = new MemDAO();

	/**
	 * @return employeeList
	 * @throws Exception
	 */
	public List<Employee> selectAll() throws Exception {
		
		Connection conn = getConnection(); // 커넥션 생성
		
		// DAO 메서드 호출 후 결과 반환 받기
		List<Employee> employeeList = dao.selectAll(conn);
		
		close(conn); // 커넥션 반환
		
		// 조회 결과 반환
		return employeeList;
		
	}

	/**
	 * @param employee
	 * @return result
	 * @throws Exception
	 */
	public int updateEmployee(Employee employee) throws Exception {
		
		//커넥션 생성
		Connection conn = getConnection();
		// DAO 메서드 호출 후 결과 반환 받기
		int result = dao.updateEmployee(conn, employee);
		
		// 트랜잭션 제어 처리
		if(result > 0) commit(conn);
		else		   rollback(conn);
		
		// 커넥션 반환
		close(conn);
		
		// 수정 결과 반환
		return result;
	}

	/**
	 * @param currentPw
	 * @param newPw1
	 * @param employeeNo
	 * @return result
	 * @throws Exception
	 */
	public int updatePw(String currentPw, String newPw1, int employeeNo) throws Exception {
		
		//커넥션 생성
		Connection conn = getConnection();
		// DAO 메서드 호출 후 결과 반환 받기
		int result = dao.updatePw(conn, currentPw, newPw1, employeeNo);
		
		// 트랜잭션 제어 처리
		if(result > 0) commit(conn);
		else		   rollback(conn);
		
		// 커넥션 반환
		close(conn);
		
		// 수정 결과 반환
		return result;
	}

	/**
	 * @param employeePw
	 * @param employeeNo
	 * @return result
	 * @throws Exception
	 */
	public int secession(String employeePw, int employeeNo) throws Exception {
		
		Connection conn = getConnection();
		
		int result = dao.secession(conn, employeePw, employeeNo);
		
		if(result > 0) commit(conn);
		else		   rollback(conn);
		
		close(conn);
		
		return result;
	}

}
