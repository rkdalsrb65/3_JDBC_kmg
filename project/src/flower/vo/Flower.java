package flower.vo;

// VO(Value Object) : 값을 저장하는 용도의 객체
// - JDBC에서는 테이블의 한 행의 조회 결과 또는
// 삽입, 수정을 위한 데이터를 저장하는 용도의 객체
public class Flower {
	
	private int flowerNo;		  	 // 회원 번호
	private String flowerId;	  	 // 회원 아이디
	private String flowerPw;	  	 // 회원 비밀번호
	private String flowerName;	  	 // 회원 이름
	private String flowerGender;     // 회원 성별
	private String flowerTier;		 // 회원 등급
	private String enrollDate;   	 // 회원 가입일
	private String secessionFlag;	 // 탈퇴 여부

	// 기본 생성자
	public Flower() { }

	// 매개변수 생성자
	public Flower(int flowerNo, String flowerId, String flowerPw, String flowerName, String flowerGender,
			String flowerTier, String enrollDate, String secessionFlag) {
		super();
		this.flowerNo = flowerNo;
		this.flowerId = flowerId;
		this.flowerPw = flowerPw;
		this.flowerName = flowerName;
		this.flowerGender = flowerGender;
		this.flowerTier = flowerTier;
		this.enrollDate = enrollDate;
		this.secessionFlag = secessionFlag;
	}

	// getter / setter
	public int getFlowerNo() {
		return flowerNo;
	}

	public void setFlowerNo(int flowerNo) {
		this.flowerNo = flowerNo;
	}

	public String getFlowerId() {
		return flowerId;
	}

	public void setFlowerId(String flowerId) {
		this.flowerId = flowerId;
	}

	public String getFlowerPw() {
		return flowerPw;
	}

	public void setFlowerPw(String flowerPw) {
		this.flowerPw = flowerPw;
	}

	public String getFlowerName() {
		return flowerName;
	}

	public void setFlowerName(String flowerName) {
		this.flowerName = flowerName;
	}

	public String getFlowerGender() {
		return flowerGender;
	}

	public void setFlowerGender(String flowerGender) {
		this.flowerGender = flowerGender;
	}

	public String getFlowerTier() {
		return flowerTier;
	}

	public void setFlowerTier(String flowerTier) {
		this.flowerTier = flowerTier;
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