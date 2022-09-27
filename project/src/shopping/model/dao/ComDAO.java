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

import shopping.vo.Com;

public class ComDAO {

	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Properties prop;
	
	public ComDAO() {
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("com.xml"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	/** 댓글 목록 조회 DAO
	 * @param conn
	 * @param shoppingNo
	 * @return comList
	 * @throws Exception
	 */
	public List<Com> selectComList(Connection conn, int shoppingNo) throws Exception {
		List<Com> comList = new ArrayList<>();
		
		try {
			String sql = prop.getProperty("selectComList"); // SQL 얻어오기
			
			pstmt = conn.prepareStatement(sql); // PreparedStatement 생성
			pstmt.setInt(1, shoppingNo); // ? 알맞은 값 대입
			rs = pstmt.executeQuery(); // SQL(SELECT) 수행 후 결과(ResultSet) 반환 받기
					
			while(rs.next()) { // 조회 결과가 있을 경우
				
				int comNo = rs.getInt("COM_NO");
				String comContent = rs.getString("COM_CONTENT");
				int employeeNo = rs.getInt("EMPLOYEE_NO");
				String employeeName = rs.getString("EMPLOYEE_NM");
				String createDate = rs.getString("CREATE_DT");
				
				Com com = new Com();
				com.setComNo(comNo);
				com.setComContent(comContent);
				com.setEmployeeNo(employeeNo);
				com.setEmployeeName(employeeName);
				com.setCreateDate(createDate);
				
				comList.add(com);// List 담기
			}

		} finally {
			close(rs);
			close(pstmt);
		}
		
		return comList;
	}

	/** 댓글 등록 DAO
	 * @param conn
	 * @param com
	 * @return result
	 * @throws Exception
	 */
	public int insertCom(Connection conn, Com com) throws Exception {
		
		int result = 0; 
		
		try {
			
			String sql = prop.getProperty("insertCom"); // SQL 얻어오기
			
			pstmt = conn.prepareStatement(sql); // PreparedStatement 생성
			
			 // ? 알맞은 값 대입
			pstmt.setString(1, com.getComContent());
			pstmt.setInt(2, com.getEmployeeNo());
			pstmt.setInt(3, com.getShoppingNo());		
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);			
		}

		return result;
	}

	/** 댓글 수정 DAO
	 * @param conn
	 * @param comNo
	 * @param content
	 * @return result
	 * @throws Exception
	 */
	public int updateCom(Connection conn, int comNo, String content) throws Exception{
		
		int result = 0; 
		
		try {
			
			String sql = prop.getProperty("updateCom"); // SQL 얻어오기
			
			pstmt = conn.prepareStatement(sql); // PreparedStatement 생성
			
			 // ? 알맞은 값 대입
			
			pstmt.setString(1, content);
			pstmt.setInt(2, comNo);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);			
		}

		return result;
	}

	/** 댓글 삭제 DAO
	 * @param conn
	 * @param comNo
	 * @return result
	 * @throws Exception
	 */
	public int deleteCom(Connection conn, int comNo) throws Exception {

		int result = 0; 
		
		try {
			
			String sql = prop.getProperty("deleteCom"); // SQL 얻어오기
			
			pstmt = conn.prepareStatement(sql); // PreparedStatement 생성
			
			 // ? 알맞은 값 대입
			
			pstmt.setInt(1, comNo);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);			
		}

		return result;
	}	
	
	
}
