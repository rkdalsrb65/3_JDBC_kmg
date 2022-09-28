package order.vo;

public class Order {

	private int orderNo;	 // 주문 번호
	private int ordePrice;	 // 주문 가격
	private String orderDate;// 주문 날짜
	private int employeeNo;	 // 회원 번호
	private int shoppingNo;	 // 쇼핑몰 번호
	private int comNo;		 // 댓글 번호
	
	private String employeeName;	// 회원 이름
	private String createDate;		// 작성일
	private String shoppingTitle;	// 상품 제목
	private int readCount;			// 조회수
	private String shoppingContent;	// 상품내용
	
	
	
	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getShoppingTitle() {
		return shoppingTitle;
	}

	public void setShoppingTitle(String shoppingTitle) {
		this.shoppingTitle = shoppingTitle;
	}

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}

	public String getShoppingContent() {
		return shoppingContent;
	}

	public void setShoppingContent(String shoppingContent) {
		this.shoppingContent = shoppingContent;
	}

	public Order(int orderNo, int ordePrice, String orderDate, int employeeNo, int shoppingNo, int comNo,
			String employeeName, String createDate, String shoppingTitle, int readCount, String shoppingContent) {
		super();
		this.orderNo = orderNo;
		this.ordePrice = ordePrice;
		this.orderDate = orderDate;
		this.employeeNo = employeeNo;
		this.shoppingNo = shoppingNo;
		this.comNo = comNo;
		this.employeeName = employeeName;
		this.createDate = createDate;
		this.shoppingTitle = shoppingTitle;
		this.readCount = readCount;
		this.shoppingContent = shoppingContent;
	}

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