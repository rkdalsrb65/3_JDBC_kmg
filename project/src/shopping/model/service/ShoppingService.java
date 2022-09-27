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

	/** 쇼핑몰 상세 조회 서비스
	 * @param shoppingNo
	 * @param employeeNo
	 * @return shopping
	 * @throws Exception
	 */
	public Shopping selectShopping(int shoppingNo, int employeeNo) throws Exception {

		Connection conn = getConnection(); // 커넥션 생성
		
		// 1. 게시글 상세 조회 DAO 호출
		Shopping shopping = dao.selectShopping(conn, shoppingNo);
		// -> 조회 결과가 없으면 null, 있으면 null 아님
		
		if(shopping != null) { // 게시글이 존재 한다면
			// 2. 댓글 목록 조회 DAO 호출
			List<Com> comList = cDao.selectComList(conn, shoppingNo);
			
			// 조회된 댓글 목록을 board에 저장
			shopping.setComList(comList);
			
			// 3. 조회 수 증가
			// 단, 로그인한 회원과 게시글 작성자가 다를 경우에만 증가
			if(employeeNo != shopping.getEmployeeNo()) {
				int result = dao.increaseReadCount(conn, shoppingNo);
				
				// 트랜잭션 제어
				if(result > 0) {
					commit(conn);
					// 미리 조회된 board의 조회 수를
					// 증가된 DB의 조회 수와 동일 한 값을 가지도록 동기화
					shopping.setReadCount(shopping.getReadCount() + 1);
				}
				else			rollback(conn);
			}
		}
		
		close(conn); // 커넥션 반환		
		
		return shopping; // 조회 결과 반환
		
	}

}
