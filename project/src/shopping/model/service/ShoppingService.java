package shopping.model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import shopping.model.dao.ComDAO;
import shopping.model.dao.ShoppingDAO;
import shopping.vo.Com;
import shopping.vo.Shopping;

public class ShoppingService {
	
	private ShoppingDAO dao = new ShoppingDAO();

	private ComDAO cDao = new ComDAO();
	
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
			
			// 조회된 댓글 목록을 shopping에 저장
			shopping.setComList(comList);
			
			// 3. 조회 수 증가
			// 단, 로그인한 회원과 게시글 작성자가 다를 경우에만 증가
			if(employeeNo != shopping.getEmployeeNo()) {
				int result = dao.increaseReadCount(conn, shoppingNo);
				
				// 트랜잭션 제어
				if(result > 0) {
					commit(conn);
					// 미리 조회된 shopping의 조회 수를
					// 증가된 DB의 조회 수와 동일 한 값을 가지도록 동기화
					shopping.setReadCount(shopping.getReadCount() + 1);
				}
				else			rollback(conn);
			}
		}
		close(conn); // 커넥션 반환		
		
		return shopping; // 조회 결과 반환
	}

	/** 게시글 수정 서비스
	 * @param shopping
	 * @return result
	 * @throws Exception
	 */
	public int updateShopping(Shopping shopping) throws Exception {
		
		Connection conn = getConnection(); // 커넥션 생성
		
		// 1. 게시글 수정 DAO 호출
		int result = dao.updateShopping(conn, shopping);
		
		if(result > 0)  commit(conn);
		else 			rollback(conn);
		
		close(conn);
		
		return result;
	}

	/** 게시글 삭제 서비스
	 * @param shoppingNo
	 * @return result
	 * @throws Exception
	 */
	public int deleteShopping(int shoppingNo) throws Exception {
		
		Connection conn = getConnection(); // 커넥션 생성
		
		// 1. 게시글 삭제 DAO 호출
		int result = dao.deleteShopping(conn, shoppingNo);
		
		if(result > 0)  commit(conn);
		else 			rollback(conn);
		
		close(conn);
		
		return result;
	}

	/** 게시글 등록 서비스
	 * @param shopping
	 * @return result
	 * @throws Exception
	 */
	public int insertShopping(Shopping shopping) throws Exception {
		
		Connection conn = getConnection(); // 커넥션 생성
		
		// 게시글 번호 생성 dao 호출
		// 왜? 동시에 여러 사람이 게시글을 등록하면
		// 시퀀스가 한번에 증가하여 CURRVAL 구문을 이용하면 문제가 발생함
		// -> 게시글 등록 서비스를 호출한 순서대로
		// 미리 게시글 번호를 생성해서 얻어온 다음 이를 이용해 INSERT 진행
		int shoppingNo = dao.nextShoppingNo(conn);
		
		shopping.setShoppingNo(shoppingNo); // 얻어온 다음 번호를 shopping에 세팅
		// -> 다음 게시글 번호, 제목, 내용, 회원 번호
		
		// 게시글 등록 DAO 호출
		int result = dao.insertShopping(conn, shopping);
		
		if(result > 0) {
			commit(conn); // 커밋할지
			
			result = shoppingNo;
			// INSERT 성공 시 생성된 게시글 번호(shoppingNo)를 결과로 반환
		}
		else 			rollback(conn); // 롤백할지
		
		close(conn); // 커넥션 반환
		
		return result;
	}

	/** 게시글 검색
	 * @param condition
	 * @param query
	 * @return shoppingList
	 * @throws Exception
	 */
	public List<Shopping> searchShopping(int condition, String query) throws Exception {
		
		Connection conn = getConnection(); // 커넥션 생성
		
		List<Shopping>shoppingList = dao.searchShopping(conn, condition, query);
		
		close(conn);
		
		return shoppingList;		
		
	}	
	
}