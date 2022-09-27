package order.vo;

public class Order {

	private int orderNo;	 // 주문 번호
	private int ordePrice;	 // 주문 가격
	private String orderDate;// 주문 날짜
	private int employeeNo;	 // 회원 번호
	private int shoppingNo;	 // 쇼핑몰 번호
	private int comNo;		 // 댓글 번호
	
	public Order() { }

	public Order(int orderNo, int ordePrice, String orderDate, int employeeNo, int shoppingNo, int comNo) {
		super();
		this.orderNo = orderNo;
		this.ordePrice = ordePrice;
		this.orderDate = orderDate;
		this.employeeNo = employeeNo;
		this.shoppingNo = shoppingNo;
		this.comNo = comNo;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public int getOrdePrice() {
		return ordePrice;
	}

	public void setOrdePrice(int ordePrice) {
		this.ordePrice = ordePrice;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public int getEmployeeNo() {
		return employeeNo;
	}

	public void setEmployeeNo(int employeeNo) {
		this.employeeNo = employeeNo;
	}

	public int getShoppingNo() {
		return shoppingNo;
	}

	public void setShoppingNo(int shoppingNo) {
		this.shoppingNo = shoppingNo;
	}

	public int getComNo() {
		return comNo;
	}

	public void setComNo(int comNo) {
		this.comNo = comNo;
	}
	
	

}