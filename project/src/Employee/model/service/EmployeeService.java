package Employee.model.service;

import static Employee.common.JDBCTemplate.*;

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

}
