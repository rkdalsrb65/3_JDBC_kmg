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
				int commentCount = rs.getInt("COMMENT_COUNT");
				
				Shopping shopping = new Shopping();
				shopping.setShoppingNo(shoppingNo);
				shopping.setShoppingTitle(shoppingTitle);
				shopping.setEmployeeName(employeeName);
				shopping.setReadCount(readCount);
				shopping.setCreateDate(createDate);
				shopping.setCommentCount(commentCount);
				
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

}
