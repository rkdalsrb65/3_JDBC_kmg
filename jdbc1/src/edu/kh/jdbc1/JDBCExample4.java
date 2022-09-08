package edu.kh.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.kh.jdbc1.model.vo.Employee;

public class JDBCExample4 {
	public static void main(String[] args) {
		
		// 직급명, 급여를 입력 받아 해당 직급에서
		// 입력 받은 급여보다 많이 받는 사원의
		// 이름, 직급명, 급여, 연봉 출력
		
		// 단, 조회 결과가 없으면 "조회 결과 없음" 출력
		// 조회 결과가 있으면 아래와 같이 출력
		// 선동일 / 대표 / 8000000 / 96000000
		// 송종기 / 부사장 / 6000000 / 72000000
		// .....
		
		Scanner sc = new Scanner(System.in);
		
		Connection conn = null;
		Statement stmt  = null;
		ResultSet rs = null;
		
		try {
			System.out.print("직급명 입력 : ");
			String input = sc.nextLine();
			
			System.out.print("급여 입력 : ");
			String input2 = sc.nextLine();
			// JDBC 참조 변수에 알맞은 객체 대입
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String type = "jdbc:oracle:thin:@";
			String ip = "localhost";
			String port = ":1521";
			String sid = ":XE";
//			String url = "jdbc:oracle:thin:@localhost:1521:XE";
			String user = "kh_kmg";
			String pw = "kh1234";
			
			conn = DriverManager.getConnection(type + ip + port + sid, user, pw);
			// jdbc.oracle:thin:@localhost:1521:XE == url
			
			String sql = "SELECT EMP_NAME, JOB_NAME, SALARY, (SALARY*12)"
					+ " FROM EMPLOYEE"
					+ " NATURAL JOIN JOB"
					+ " WHERE JOB_NAME = '"+input+"'"+" AND"+" SALARY >" + input2;
			// (중요!)
			// Java에서 작성되는 SQL에 문자열(String) 변수를 추가(이어쓰기)할 경우
			// '' (DB 문자열 리터럴)이 누락되지 않도록 신경써야한다!
			
			// 만약 '' 미작성 시 String값은 컬럼명으로 인식되어 부적합한 식별자 오류가 발생한다!
			
			stmt = conn.createStatement(); // Statement 객체 생성
			
			// Statement 객체를 이용해서 SQL을 DB에 전달하여 실행한 후
			// ResultSet을 변환 받아 rs 변수에 대입
			
			rs = stmt.executeQuery(sql);
			
			// 조회 결과(rs)를 List에 옮겨 담기
			List<Employee> list = new ArrayList<>();
			
			while(rs.next()) { 
			// 다음행으로 이동해서 해당 행에 데이터가 있으면 True 없으면 false
				
				// 현재 행에 존재하는 컬럼 값 얻어오기
				String empName = rs.getString("EMP_NAME");
				String jobName = rs.getString("JOB_NAME");
				int salary = rs.getInt("SALARY");
				int annualIncome = rs.getInt("(SALARY*12)");
				
				// Emp 객체를 생성하여 컬럼 값 담기
				Employee employee = new Employee(empName, jobName, salary, annualIncome);
				
				// 생성된 Emp객체를 list에 추가
				list.add(employee);
				
//				list.add(new Employee(empName, jobName, salary, annualIncome);
			}
			
			// 만약 List에 추가된 Emp 객체가 없다면 "조회 결과가 없음"
			// 있다면 순차적으로 출력
			
			if(list.isEmpty()) { // List가 비어있을 경우
				// isEmpty() : 비어있으면 true
				System.out.println("조회 결과가 없음");
				
			} else {
				
				// 향상된 for문
				for(Employee employee : list) {
					System.out.println(employee);
				}
			}
			
		} catch(Exception e) {
			// 예외 최상위 부모인 Exception을 catch문에 작성하여
			// 발생하는 모든 예외를 처리
			e.printStackTrace();
		} finally {
			
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}		
		
	}

}
