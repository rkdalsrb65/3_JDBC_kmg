package Employee.vo;

// VO(Value Object) : 값을 저장하는 용도의 객체
// - JDBC에서는 테이블의 한 행의 조회 결과 또는
// 삽입, 수정을 위한 데이터를 저장하는 용도의 객체
public class Employee {
	
	private int employeeNo;		  	 // 회원 번호
	private String employeeName;	 // 회원 이름
	private String employeeId;	  	 // 회원 아이디
	private String employeePw;	  	 // 회원 비밀번호
	private String employeeGender;   // 회원 성별
	private String employeeSsn;		 // 주민 등록 번호
	private String employeePhone;	 // 핸드폰 번호
	private String employeeAddress;	 // 주소
	private String enrollDate;   	 // 회원 가입일
	private String secessionFlag;	 // 회원 탈퇴 여부

	// 기본 생성자
	public Employee() { }

	// 매개변수 생성자
	public Employee(int employeeNo, String employeeName, String employeeId, String employeePw, String employeeGender,
			String employeeSsn, String employeePhone, String employeeAddress, String enrollDate, String secessionFlag) {
		super();
		this.employeeNo = employeeNo;
		this.employeeName = employeeName;
		this.employeeId = employeeId;
		this.employeePw = employeePw;
		this.employeeGender = employeeGender;
		this.employeeSsn = employeeSsn;
		this.employeePhone = employeePhone;
		this.employeeAddress = employeeAddress;
		this.enrollDate = enrollDate;
		this.secessionFlag = secessionFlag;
	}

	// getter / setter
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

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeePw() {
		return employeePw;
	}

	public void setEmployeePw(String employeePw) {
		this.employeePw = employeePw;
	}

	public String getEmployeeGender() {
		return employeeGender;
	}

	public void setEmployeeGender(String employeeGender) {
		this.employeeGender = employeeGender;
	}

	public String getEmployeeSsn() {
		return employeeSsn;
	}

	public void setEmployeeSsn(String employeeSsn) {
		this.employeeSsn = employeeSsn;
	}

	public String getEmployeePhone() {
		return employeePhone;
	}

	public void setEmployeePhone(String employeePhone) {
		this.employeePhone = employeePhone;
	}

	public String getEmployeeAddress() {
		return employeeAddress;
	}

	public void setEmployeeAddress(String employeeAddress) {
		this.employeeAddress = employeeAddress;
	}

	public String getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(String enrollDate) {
		this.enrollDate = enrollDate;
	}

	public String getSecessionFlag() {
		return secessionFlag;
	}

	public void setSecessionFlag(String secessionFlag) {
		this.secessionFlag = secessionFlag;
	}
	

	
	
	
	
	
}