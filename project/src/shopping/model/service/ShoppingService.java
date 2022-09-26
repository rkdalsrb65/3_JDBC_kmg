package shopping.model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import shopping.model.dao.ShoppingDAO;
import shopping.vo.Shopping;

public class ShoppingService {
	
	private ShoppingDAO dao = new ShoppingDAO();

	/** 쇼핑몰 목록 조회 서비스
	 * @return shoppingList
	 * @throws Exception
	 */
	public List<Shopping> selectAllShopping() throws Exception {
		
		Connection conn = getConnection(); // 커넥션 생성
		
		// DAO 메서드 호출 후 결과 반환 받기
		List<Shopping> shoppingList = dao.selectAllShopping(conn);
		
		close(conn); // 커넥션 반환
		
		return shoppingList; // 조회 결과 반환
	}

}
