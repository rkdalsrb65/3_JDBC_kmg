package order.model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;

import order.model.dao.OrderDAO;
import order.vo.Order;
import shopping.model.dao.ComDAO;
import shopping.model.dao.ShoppingDAO;

public class OrderService {
	
	private OrderDAO oDao = new OrderDAO();
	
	private ShoppingDAO dao = new ShoppingDAO();

	private ComDAO cDao = new ComDAO();
	
	/** 주문 신청 서비스
	 * @param orderNo
	 * @param employeeNo
	 * @return order
	 * @throws Exception
	 */
	public Order requestOrder(int orderNo, int employeeNo) throws Exception {
		
		Connection conn = getConnection(); // 커넥션 생성
		
		Order order = oDao.requestOrder(conn, orderNo);
		
		int result = dao.increaseReadCount(conn, orderNo);
		
		if(result > 0)  commit(conn);
		else 			rollback(conn);		
		
		close(conn); // 커넥션 반환	
		
		return order;
	}

}
