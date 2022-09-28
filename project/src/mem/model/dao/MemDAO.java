package mem.model.dao;

import static common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import Employee.vo.Employee;

public class MemDAO {

	// 필드 (== 멤버 변수)
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Properties prop;
	
	public MemDAO() {
		
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("employee.xml"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Employee> selectAll(Connection conn) throws Exception {
		// 결과 저장용 변수 선언
		List<Employee> employeeList = new ArrayList<>();
		
		try {
			// SQL 얻어오기
			String sql = prop.getProperty("selectAll");
			// Statement 객체 생성
			stmt = conn.createStatement();
			// SQL(SELECT) 수행 후 결과(ResultSet) 반환 받기
			rs = stmt.executeQuery(sql);
			// 반복문(while)을 이용해서 조회 결과의 각 행에 접근 후
			while(rs.next()) {
				// 컬럼 값을 얻어와 Member 객체에 저장 후 List에 추가
				String employeeId = rs.getString("EMPLOYEE_ID");
				String employeeName = rs.getString("EMPLOYEE_NM");
				String employeeGender = rs.getString("EMPLOYEE_GENDER");
				
				Employee employee = new Employee();
				employee.setEmployeeId(employeeId);
				employee.setEmployeeName(employeeName);
				employee.setEmployeeGender(employeeGender);
				
				employeeList.add(employee);// List 담기
			}
			
		} finally {
			// JDBC 객체 자원 반환
			close(rs);
			close(stmt);
		}
		
		// 조회 결과를 옮겨 담은 List 반환
		return employeeList;
	}

	public int updateEmployee(Connection conn, Employee employee) throws Exception {
		
		
		// 결과 저장용 변수 선언
		int result = 0; // UPDATE 반영 결과 행의 개수(정수형)를 저장하기 위한 변수
		
		try {
			// SQL 얻어오기
			String sql = prop.getProperty("updateEmployee");
			
			// PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			// ? 알맞은 값 대입
			pstmt.setString(1, employee.getEmployeeName());
			pstmt.setString(2, employee.getEmployeeGender());
			pstmt.setInt(3, employee.getEmployeeNo());
			
			// SQL 수행 후 결과 반환 받기
			result = pstmt.executeUpdate();
			
		} finally {
			// JDBC 객체 자원 반환
			close(pstmt);
		}
		
		
		// 수정 결과 반환
		return result;
	}

	public int updatePw(Connection conn, String currentPw, String newPw1, int employeeNo) throws Exception {
		
		// 결과 저장용 변수 선언
		int result = 0; // UPDATE 반영 결과 행의 개수(정수형)를 저장하기 위한 변수
		
		try {
			// SQL 얻어오기
			String sql = prop.getProperty("updatePw");
			
			// PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			// ? 알맞은 값 대입
			pstmt.setString(1, newPw1);
			pstmt.setInt(2, employeeNo);
			pstmt.setString(3, currentPw);
			
			// SQL 수행 후 결과 반환 받기
			result = pstmt.executeUpdate();
			
		} finally {
			// JDBC 객체 자원 반환
			close(pstmt);
		}
		
		
		// 수정 결과 반환
		return result;
	}

	public int secession(Connection conn, String employeePw, int employeeNo) throws Exception {
		
		// 결과 저장용 변수 선언
		int result = 0;
		
		try {
			// SQL 얻어오기
			String sql = prop.getProperty("secession");
			
			// PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			// ? 알맞은 값 대입
			pstmt.setInt(1, employeeNo);
			pstmt.setString(2, employeePw);
			
			// SQL 수행 후 결과 반환 받기
			result = pstmt.executeUpdate();
			
		} finally {
			// JDBC 객체 자원 반환
			close(pstmt);
		}
		// 탈퇴 결과 반환
		return result;
	}

}
