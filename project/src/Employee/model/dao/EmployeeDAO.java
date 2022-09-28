package Employee.model.dao;

import static common.JDBCTemplate.*;

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
				loginEmployee.setEmployeeId(rs.getString("EMPLOYEE_ID"));			// 회원 아이디
				loginEmployee.setEmployeeGender(rs.getString("EMPLOYEE_GENDER"));	// 회원 성별
				loginEmployee.setEnrollDate(rs.getString("ENROLL_DATE"));			// 회원 가입일
				
			}
			
		} finally {
			// 7. 사용한 JDBC 객체 자원 반환
			close(rs);
			close(pstmt);
			
		}
		// 8. 조회 결과 반환
		return loginEmployee;
	}

	/** 회원 가입 DAO
	 * @param conn
	 * @param employee
	 * @return result
	 * @throws Exception
	 */
	public int signUp(Connection conn, Employee employee) throws Exception {
		
		// 1. 결과 저장용 변수
		int result = 0;
		
		try {
			
			// 2. SQL 얻어오기
			String sql = prop.getProperty("signUp");
			
			// 3. PreaparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			// 4. ?에 알맞은 값 대입
			pstmt.setString(1, employee.getEmployeeId());
			pstmt.setString(2, employee.getEmployeePw());
			pstmt.setString(3, employee.getEmployeeName());
			pstmt.setString(4, employee.getEmployeeGender());
			
			// 5. SQL 수행 후 결과 반환 받기
			result = pstmt.executeUpdate();
			
		} finally {
			// 6. 사용한 JDBC 객체 자원 반환
				close(pstmt);
				
			}
		// 7. 결과 반환
		return result;
		
	}

	/** 아이디 중복 확인(체크) DAO
	 * @param conn
	 * @param employeeId
	 * @return result
	 * @throws Exception
	 */
	public int idDupCheck(Connection conn, String employeeId) throws Exception {
		
		// 1. 결과 저장용 변수
		int result = 0;
		
		try {
			// 2. SQL 얻어오기
			String sql = prop.getProperty("idDupCheck");
			
			// 3. PreaparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			// 4. ? 알맞은 값 세팅
			pstmt.setString(1, employeeId);
			
			// 5. SQL 수행 후 결과 반환 받기
			rs = pstmt.executeQuery();
			
			// 6. 조회 결과 옮겨 담기
			// 1행 조회 -> if
			// N행 조회 -> while
			if(rs.next()) {
				// result = rs.getInt("COUNT(*)"); // 컬럼명
				result = rs.getInt(1); // 컬럼순서
			}
			
		} finally {
			// 7. 사용한 JDBC 객체 자원 반환
			close(rs);
			close(pstmt);
			
		}
		
		// 8. 결과 반환
		return result;
	}

	/** 이름으로 아이디 찾기 DAO
	 * @param conn
	 * @param userName
	 * @return employeeId
	 * @throws Exception
	 */
	public String findId(Connection conn, String userName) throws Exception {
		
	     String employeeId = null;
	     
//	     String sql = "SELECT EMPLOYEE_ID"
//	           + " FROM EMPLOYEE"
//	           + " WHERE EMPLOYEE_NM = ?";
	     
	     String sql = prop.getProperty("findId");
	     
	     pstmt = conn.prepareStatement(sql);
	     pstmt.setString(1, userName);
	     rs = pstmt.executeQuery();
	     
	     if(rs.next()) {
	        employeeId = rs.getString("EMPLOYEE_ID");
	     }		
		
		return employeeId;		
	}

	/** 아이디 + 이름으로 비밀번호 찾기
	 * @param conn
	 * @param userId
	 * @param userName
	 * @return memberPw
	 * @throws Exception
	 */
	public String findPw(Connection conn, String userId, String userName) throws Exception {

	     String employeePw = null;
	     
//	     String sql = "SELECT EMPLOYEE_PW"
//	           + " FROM EMPLOYEE"
//	           + " WHERE EMPLOYEE_ID = ?"
//	           + " AND EMPLOYEE_NM = ?";
	     
	     String sql = prop.getProperty("findPw");
	     
	     pstmt = conn.prepareStatement(sql);
	     pstmt.setString(1, userId);
	     pstmt.setString(2, userName);
	     rs = pstmt.executeQuery();
	     
	     if(rs.next()) {
	        employeePw = rs.getString("EMPLOYEE_PW");
	     }
	     return employeePw;
	}	
	
}