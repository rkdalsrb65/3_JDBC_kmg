package shopping.model.dao;

import static common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import shopping.vo.Shopping;

public class ShoppingDAO {
	
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Properties prop;
	
	public ShoppingDAO() {
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("shopping.xml"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 쇼핑몰 목록 조회 DAO
	 * @param conn
	 * @return shoppingList
	 * @throws Exception
	 */
	public List<Shopping> selectAllShopping(Connection conn) throws Exception {
		
		// 결과 저장용 변수 선언
		List<Shopping> shoppingList = new ArrayList<>();
		
		try {
			// SQL ~
			String sql = prop.getProperty("selectAllShopping");
			// Statement ~
			stmt = conn.createStatement();
			// SQL 수행 후~ ResultSet ~
			rs = stmt.executeQuery(sql);
			// ResultSet에 저장된 값을 List 옮겨 담기
			while(rs.next()) {
				// 컬럼 값을 얻어와 Member 객체에 저장 후 List에 추가

				int shoppingNo = rs.getInt("SHOPPING_NO");
				String shoppingTitle = rs.getString("SHOPPING_TITLE");
				String employeeName = rs.getString("EMPLOYEE_NM");
				int readCount = rs.getInt("READ_COUNT");
				String createDate = rs.getString("CREATE_DT");
				int comCount = rs.getInt("COM_COUNT");
				
				Shopping shopping = new Shopping();
				shopping.setShoppingNo(shoppingNo);
				shopping.setShoppingTitle(shoppingTitle);
				shopping.setEmployeeName(employeeName);
				shopping.setReadCount(readCount);
				shopping.setCreateDate(createDate);
				shopping.setComCount(comCount);
				
				shoppingList.add(shopping);// List 담기
			}			
			
		} finally {
			// JDBC 객체 자원 반환
			close(rs);
			close(stmt);
		}
		
		// 조회 결과 반환
		return shoppingList;		
		
	}

	/** 쇼핑몰 상세 조회 DAO
	 * @param conn
	 * @param shoppingNo
	 * @return shopping
	 * @throws Exception
	 */
	public Shopping selectShopping(Connection conn, int shoppingNo) throws Exception {

		// 결과 저장용 변수 선언
		Shopping shopping = null;
		
		try {
			String sql = prop.getProperty("selectShopping"); // SQL 얻어오기
			 
			pstmt = conn.prepareStatement(sql); // PreparedStatement 생성
			
			pstmt.setInt(1, shoppingNo); // ? 알맞은 값 대입
			
			rs = pstmt.executeQuery(); // SQL(SELECT) 수행 후 결과(ResultSet) 반환 받기
			
			if(rs.next()) { // 조회 결과가 있을 경우
				shopping = new Shopping(); // Shopping 객체 생성 == shopping는 null 아님
				
				shopping.setShoppingNo(		rs.getInt	("SHOPPING_NO"));
				shopping.setShoppingTitle(	rs.getString("SHOPPING_TITLE"));
				shopping.setShoppingContent(rs.getString("SHOPPING_CONTENT"));
				shopping.setEmployeeNo(		rs.getInt	("EMPLOYEE_NO"));
				shopping.setEmployeeName(	rs.getString("EMPLOYEE_NM"));
				shopping.setReadCount(		rs.getInt	("READ_COUNT"));
				shopping.setCreateDate(		rs.getString("CREATE_DT"));

			}
			
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return shopping; // 조회 결과
		
	}

	/** 조회 수 증가 DAO
	 * @param conn
	 * @param shoppingNo
	 * @return result
	 * @throws Exception
	 */
	public int increaseReadCount(Connection conn, int shoppingNo) throws Exception {
		// 결과 저장용 변수 선언
		int result = 0;
		
		try {
			String sql = prop.getProperty("increaseReadCount"); // SQL 얻어오기
			
			pstmt = conn.prepareStatement(sql); // PreparedStatement 생성
			pstmt.setInt(1, shoppingNo); // ? 알맞은 값 대입
			result = pstmt.executeUpdate(); // SQL(SELECT) 수행 후 결과(ResultSet) 반환 받기

		} finally {
			close(pstmt);
		}
		return result;
	}

	/** 게시글 수정 DAO
	 * @param conn
	 * @param shopping
	 * @return result
	 * @throws Exception
	 */
	public int updateShopping(Connection conn, Shopping shopping) throws Exception {
		
		int result = 0;
		
		try {
			String sql = prop.getProperty("updateShopping");
			
			pstmt = conn.prepareStatement(sql); // PreparedStatement 생성
			// ? 알맞은 값 대입
			pstmt.setString(1, shopping.getShoppingTitle());
			pstmt.setString(2, shopping.getShoppingContent());
			pstmt.setInt(3, shopping.getShoppingNo());
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		return result;
	}

	/** 게시글 삭제 DAO
	 * @param conn
	 * @param shoppingNo
	 * @return result
	 * @throws Exception
	 */
	public int deleteShopping(Connection conn, int shoppingNo) throws Exception {

		int result = 0;
		
		try {
			String sql = prop.getProperty("deleteShopping");
			
			pstmt = conn.prepareStatement(sql); // PreparedStatement 생성
			// ? 알맞은 값 대입
			pstmt.setInt(1, shoppingNo);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		return result;
	}

	/** 게시글 등록 DAO
	 * @param conn
	 * @param shopping
	 * @return result
	 * @throws Exception
	 */
	public int insertShopping(Connection conn, Shopping shopping) throws Exception {
		
		int result = 0;
		
		try {
			String sql = prop.getProperty("insertShopping");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, shopping.getShoppingNo()); // 추가
			pstmt.setString(2, shopping.getShoppingTitle());
			pstmt.setString(3, shopping.getShoppingContent());
			pstmt.setInt(4, shopping.getEmployeeNo());
			
			result = pstmt.executeUpdate();
			
		} finally {
			
			close(pstmt);
			
		}
		
		return result;		
		
	}

	/** 다음 게시글 번호 생성
	 * @param conn
	 * @return shoppingNo
	 * @throws Exception
	 */
	public int nextShoppingNo(Connection conn) throws Exception {
		
		int shoppingNo = 0;
		
		try {
			String sql = prop.getProperty("nextShoppingNo");
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery(); // select 수행
			
			if(rs.next()) { // 조회 결과 1행 밖에 없음
				shoppingNo = rs.getInt(1); // 첫 번째 컬럼값을 얻어와 boardNo 저장
			}
			
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return shoppingNo;
		
	}

	/** 게시글 검색
	 * @param conn
	 * @param condition
	 * @param query
	 * @return shoppingList
	 * @throws Exception
	 */
	public List<Shopping> searchShopping(Connection conn, int condition, String query) throws Exception {

		List<Shopping> shoppingList = new ArrayList<>();
		
		try {
			String sql = prop.getProperty("searchShopping1")
					+ prop.getProperty("searchShopping2_" + condition)
					+ prop.getProperty("searchShopping3");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,  query);
			
			// 3번 (제목+내용)은 ?가 2개 존재하기 때문에 추가 세팅 구문 작성
			if(condition == 3) pstmt.setString(2,  query);
			
			rs = pstmt.executeQuery();
			
			// ResultSet에 저장된 값을 List 옮겨 담기
			while(rs.next()) {
				// 컬럼 값을 얻어와 Member 객체에 저장 후 List에 추가

				int shoppingNo = rs.getInt("SHOPPING_NO");
				String shoppingTitle = rs.getString("SHOPPING_TITLE");
				String employeeName = rs.getString("EMPLOYEE_NM");
				int readCount = rs.getInt("READ_COUNT");
				String createDate = rs.getString("CREATE_DT");
				int comCount = rs.getInt("COM_COUNT");
				
				Shopping shopping = new Shopping();
				shopping.setShoppingNo(shoppingNo);
				shopping.setShoppingTitle(shoppingTitle);
				shopping.setEmployeeName(employeeName);
				shopping.setReadCount(readCount);
				shopping.setCreateDate(createDate);
				shopping.setComCount(comCount);
				
				shoppingList.add(shopping);// List 담기
			}
			
		} finally {
			close(rs);
			close(pstmt);
			
		}
		
		return shoppingList;		
		
	}
	
}