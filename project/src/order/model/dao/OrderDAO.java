package order.model.dao;

import static common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import order.vo.Order;
import shopping.vo.Shopping;

public class OrderDAO {
	
	// 필드 (== 멤버 변수)
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private Properties prop = null;
	
	public OrderDAO() {
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("order.xml"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Order requestOrder(Connection conn, int orderNo) throws Exception {
		
		// 1. 결과 저장용 변수
		Order order = null;
		
		try {
			// 2. SQL 얻어오기
			String sql = prop.getProperty("requestOrder");
			
			pstmt = conn.prepareStatement(sql);
			
//			pstmt.setInt(1, shoppingNo);
			
			rs = pstmt.executeQuery();
			

			if(rs.next()) {
				order = new Order(); // Order 객체 생성 == order는 null 아님
				
				order.setShoppingNo(		rs.getInt	("SHOPPING_NO"));
				order.setShoppingTitle(	rs.getString("SHOPPING_TITLE"));
				order.setShoppingContent(rs.getString("SHOPPING_CONTENT"));
				order.setEmployeeNo(		rs.getInt	("EMPLOYEE_NO"));
				order.setEmployeeName(	rs.getString("EMPLOYEE_NM"));
				order.setReadCount(		rs.getInt	("READ_COUNT"));
				order.setCreateDate(		rs.getString("CREATE_DT"));				
			}
			
		} finally {
			close(rs);
			close(pstmt);
		}
		return order;
	}

}
