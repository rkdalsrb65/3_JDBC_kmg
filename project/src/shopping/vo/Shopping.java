package shopping.vo;

public class Shopping {

	private int shoppingNo; 		// 상품 번호
	private String shoppingTitle; 	// 상품 제목
	private String shoppingContent; // 상품 내용
	private String createDate;		// 작성일
	private int readCount; 			// 조회수
	private int employeeNo; 		// 작성자 회원 번호
	private String employeeName; 	// 작성자 회원 이름
	private int commentCount;		// 댓글 수	
	
	// 기본 생성자
	public Shopping() { }

	// 매개 변수 생성자
	public Shopping(int shoppingNo, String shoppingTitle, String shoppingContent, String createDate, int readCount,
			int employeeNo, String employeeName, int commentCount) {
		super();
		this.shoppingNo = shoppingNo;
		this.shoppingTitle = shoppingTitle;
		this.shoppingContent = shoppingContent;
		this.createDate = createDate;
		this.readCount = readCount;
		this.employeeNo = employeeNo;
		this.employeeName = employeeName;
		this.commentCount = commentCount;
	}

	// getter / setter
	public int getShoppingNo() {
		return shoppingNo;
	}

	public void setShoppingNo(int shoppingNo) {
		this.shoppingNo = shoppingNo;
	}

	public String getShoppingTitle() {
		return shoppingTitle;
	}

	public void setShoppingTitle(String shoppingTitle) {
		this.shoppingTitle = shoppingTitle;
	}

	public String getShoppingContent() {
		return shoppingContent;
	}

	public void setShoppingContent(String shoppingContent) {
		this.shoppingContent = shoppingContent;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}

	public int getEmployeeNo() {
		return employeeNo;
	}

	public void setEmployeeNo(int employeeNo) {
		this.employeeNo = employeeNo;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
	
	
	
	
}
