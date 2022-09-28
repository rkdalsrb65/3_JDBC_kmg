package shopping.vo;

public class Com {

	private int comNo; 			// 댓글 번호
	private String comContent;	// 댓글 내용
	private int employeeNo; 	// 작성자 회원 번호
	private String employeeName;// 작성자 회원 이름
	private String createDate;	// 작성일
	private int shoppingNo;		// 작성된 게시글 번호(등록, 수정, 삭제 시 이용)	
	
	public Com() {}

	public int getComNo() {
		return comNo;
	}

	public void setComNo(int comNo) {
		this.comNo = comNo;
	}

	public String getComContent() {
		return comContent;
	}

	public void setComContent(String comContent) {
		this.comContent = comContent;
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

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public int getShoppingNo() {
		return shoppingNo;
	}

	public void setShoppingNo(int shoppingNo) {
		this.shoppingNo = shoppingNo;
	}

}