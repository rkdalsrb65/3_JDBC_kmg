package Employee.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import Employee.model.service.EmployeeService;
import Employee.vo.Employee;

public class EmployeeView {
	
	private Scanner sc = new Scanner(System.in);
	
	// 회원 관련 서비스를 제공하는 객체 생성
	private EmployeeService service = new EmployeeService();
	
	// 로그인 회원 정보 저장용 변수
	private Employee loginEmployee = null;
	
	//메뉴 번호를 입력 받기 위한 변수
	private int input = -1;
	
	
	public void mainMenu() {
		
		do {
			try {

				if (loginEmployee == null) {

					System.out.println("\n***** 게시판 프로그램 *****\n");
					System.out.println("1. 로그인");
					System.out.println("2. 회원 가입");
					System.out.println("3. 아이디 찾기");
					System.out.println("4. 비밀번호 찾기");
					System.out.println("0. 프로그램 종료");

					System.out.print("\n메뉴 선택 : ");
					input = sc.nextInt();
					sc.nextLine(); // 입력 버퍼 개행문자 제거

					System.out.println();

					switch (input) {
					case 1: login(); break; // 로그인
					case 2: break; // 회원 가입
					case 3: break; // 아이디 찾기
					case 4: break; // 비밀번호 찾기
					case 0: System.out.println("프로그램 종료"); break;
					default: System.out.println("메뉴에 작성된 번호만 입력해주세요.");
					}
				} else { // 로그인 O
					
					System.out.println("***** 로그인 메뉴 *****");
					System.out.println("1. 회원 기능");
					System.out.println("2. 쇼핑몰 기능");
					System.out.println("0. 로그아웃");
					System.out.println("99. 프로그램 종료");
					
					System.out.println("\n메뉴 선택 : ");
					input = sc.nextInt();

				System.out.println();

				switch (input) {
				// 회원 기능 서브 메뉴 출력
				case 1: break;
				case 2: break;
				case 0: break; 
				case 99: System.out.println("프로그램 종료");
//				input = 0; // -> do-while 조건식을 false로 만듦
				System.exit(0); // JVM(자바가상머신) 종료, 매개변수는 0, 아니면 오류를 의미
				break;
				default: System.out.println("메뉴에 작성된 번호만 입력해주세요.");
				}
				System.out.println();
				}
			} catch (InputMismatchException e) {
				System.out.println("\n<<입력 형식이 올바르지 않습니다.>>");
				sc.nextLine(); // 입력 버퍼에 남아있는 잘못된 문자열 제거
			}
		} while (input != 0);
		
	}
	
	/**
	 * 로그인 화면
	 */
	private void login() {
		System.out.println("[로그인]");

		System.out.println("아이디 : ");
		String EmployeeId = sc.next();

		System.out.println("비밀번호 : ");
		String EmployeePw = sc.next();

		try {
			// 로그인 서비스 호출 후 조회 결과를 loginMember에 저장
			loginEmployee = service.login(EmployeeId, EmployeePw);
			
			System.out.println();
			if (loginEmployee != null) { // 로그인 성공 시
				System.out.println(loginEmployee.getEmployeeName() + "님 환영합니다.");

			} else { // 로그인 실패 시
				System.out.println("[아이디 또는 비밀번호가 일치하지 않습니다.]");
			}
			System.out.println();
			
		} catch (Exception e) {
			System.out.println("\n<<로그인 중 예외 발생>>\n");
		}

	}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}