package Employee.model.dao;

import static Employee.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import Employee.vo.Employee;

public class EmployeeDAO {

	// 필드 (== 멤버 변수)
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private Properties prop = null;
	
	public EmployeeDAO() { // 기본 생성자
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("employee.xml"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 로그인 DAO
	 * @param conn
	 * @param employeeId
	 * @param employeePw
	 * @return loginEmployee
	 * @throws Exception
	 */
	public Employee login(Connection conn, String employeeId, String employeePw) throws Exception {
		
		// 1. 결과 저장용 변수
		Employee loginEmployee = null;

		try {

			// 2. SQL 얻어오기(employee.xml 파일에 작성된 SQL)
			String sql = prop.getProperty("login");

			// 3. PreaparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);

			// 4. ?에 알맞은 값 대입
			pstmt.setString(1, employeeId);
			pstmt.setString(2, employeePw);

			// 5. SQL(SELECT) 수행 후 결과(ResultSet) 반환 받기
			rs = pstmt.executeQuery();

			// 6. 조회 결과가 있을 경우
			// 컬럼 값을 모두 얻어와
			// Member 객체를 생성해서 loginMember 반환 받기
			if(rs.next()) {
				
				loginEmployee = new Employee();
				
				loginEmployee.setEmployeeNo(rs.getInt("EMPLOYEE_NO"));				// 회원 번호
				loginEmployee.setEmployeeName(rs.getString("EMPLOYEE_NM"));			// 회원 이름
				loginEmployee.setEmployeeId(employeeId);							// 회원 아이디
				loginEmployee.setEmployeePw(employeePw);							// 회원 비밀번호
				loginEmployee.setEmployeeGender(rs.getString("EMPLOYEE_GENDER"));	// 회원 성별
				loginEmployee.setEmployeeSsn(rs.getString("EMPLOYEE_SSN"));			// 주민 등록 번호
				loginEmployee.setEmployeePhone(rs.getString("EMPLOYEE_PHONE"));		// 핸드폰 번호
				loginEmployee.setEmployeeSsn(rs.getString("EMPLOYEE_ADDRESS"));		// 주소
				loginEmployee.setEnrollDate(rs.getString("ENROLL_DATE"));			// 회원 가입일
				loginEmployee.setSecessionFlag(rs.getString("SECESSION_FL"));		// 회원 탈퇴 여부
			
			}
	
		} finally {
			// 7. 사용한 JDBC 객체 자원 반환
			close(rs);
			close(pstmt);

		}
		// 8. 조회 결과 반환
		return loginEmployee;
	}	
	
	
	
}