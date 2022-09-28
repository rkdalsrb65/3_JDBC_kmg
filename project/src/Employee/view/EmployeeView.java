package Employee.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import Employee.model.service.EmployeeService;
import Employee.vo.Employee;
import order.view.OrderView;
import shopping.view.ShoppingView;

public class EmployeeView {
	
	private Scanner sc = new Scanner(System.in);
	
	// 회원 관련 서비스를 제공하는 객체 생성
	private EmployeeService service = new EmployeeService();
	
	// 로그인 회원 정보 저장용 변수
//	private Employee loginEmployee = null;
	public static Employee loginEmployee = null;
	
	// 쇼핑 기능 메뉴 객체 생성
	private ShoppingView shoppingView = new ShoppingView();
	
	// 경매 기능 메뉴 객체 생성
	private OrderView orderView = new OrderView();
	
	//메뉴 번호를 입력 받기 위한 변수
	private int input = -1;
	
	
	public void mainMenu() {
		
		do {
			try {

				if (loginEmployee == null) {

					System.out.println("\n ★ 메인 메뉴 ★ \n");
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
					case 2: signUp(); break; // 회원 가입
					case 3: findId(); break; // 아이디 찾기
					case 4: findPw(); break; // 비밀번호 찾기
					case 0: System.out.println("프로그램 종료"); break;
					default: System.out.println("메뉴에 작성된 번호만 입력해주세요.");
					}
				} else { // 로그인 O
					
					System.out.println("\n ★ 로그인 메뉴 ★ \n");
					System.out.println("1. 쇼핑몰 기능");
					System.out.println("2. 주문 기능");
					System.out.println("0. 로그아웃");
					System.out.println("99. 프로그램 종료");
					
					System.out.println("\n메뉴 선택 : ");
					input = sc.nextInt();

				System.out.println();

				switch (input) {
				// 회원 기능 서브 메뉴 출력
				case 1: shoppingView.shoppingmenu(); break;
				case 2: orderView.ordermenu(); break;
				case 0: loginEmployee = null; System.out.println("\n[로그아웃 되었습니다.]");
				input = -1; // do-while문이 종료되지 않도록 0이 아닌 값으로 변경
				break; 		// 로그아웃 == loginEmployee가 참조하는 객체 없음(== null)
							// 로그인 	== loginEmployee가 참조하는 객체 존재(!= null)
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
		String employeeId = sc.next();

		System.out.println("비밀번호 : ");
		String employeePw = sc.next();

		try {
			// 로그인 서비스 호출 후 조회 결과를 loginEmployee에 저장
			loginEmployee = service.login(employeeId, employeePw);
			
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
	
	/**
	 * 회원 가입 화면
	 */
	private void signUp() {
		
		System.out.println("[회원 가입]");
		
		String employeeName = null;
		String employeeId = null;
		String employeePw1 = null;
		String employeePw2 = null;
		String employeeGender = null;
		
		try {
			
			// 이름 입력
			System.out.print("이름 입력 : ");
			employeeName = sc.next();
			
			// 아이디를 입력 받아 중복이 아닐 때 까지 반복
		while(true) {
			
			System.out.print("아이디 입력 : ");
			employeeId = sc.next();
			
			// 입력 받은 아이디를 매개변수로 전달하여
			// 중복여부를 검사하는 서비스 호출 후 결과(1/0) 반환 받기
			int result = service.idDupCheck(employeeId);
			
			// 중복이 아닌 경우
			if(result == 0) {
				System.out.println("[사용 가능한 아이디 입니다.]");
				break;
			} else { // 중복인 경우
				System.out.println("[이미 사용중인 아이디 입니다.]");
			}
			System.out.println();
		}
		
		// 비밀번호 입력
		// 비밀번호/비밀번호 확인이 일치 할 때 까지 무한 반복
		while(true) {
			
			System.out.print("비밀번호 : ");
			employeePw1 = sc.next();
			
			System.out.print("비밀번호 확인 : ");
			employeePw2 = sc.next();
			
			System.out.println();
			if(employeePw1.equals(employeePw2)) { // 일치할 경우
				System.out.println("[일치합니다]");
				break;
			} else { // 일치하지 않을 경우
				System.out.println("[비밀번호가 일치하지 않습니다. 다시 입력 해주세요.]");
			}
			System.out.println();
			
		}
		
		// 성별
		// M 또는 F가 입력 될 때 까지 무한 반복
		while(true) {
			System.out.print("성별 입력(M/F) : ");
			employeeGender = sc.next().toUpperCase(); // 입력 받자마자 대문자로 변경
			
			System.out.println();
			
			if(employeeGender.equals("M") || employeeGender.equals("F")) {
				break;
				} else {
					System.out.println("[M 또는 F만 입력 해주세요.]");
					}
			System.out.println();
			}
		
		// -- 아이디, 비밀번호, 이름, 성별 입력 완료 --
		// -> 하나의 VO에 담아서 서비스 호출 후 결과 반환 받기
		
		Employee employee = new Employee(employeeName, employeeId, employeePw1, employeeGender);
		
		int result = service.signUp(employee);
		
		// 서비스 처리 결과에 따른 출력 화면 제어
		System.out.println();
		if(result > 0) {
			System.out.println("★회원 가입 성공★");
		} else {
			System.out.println("<<회원 가입 실패>>");
		}
		System.out.println();
		
		} catch (Exception e) {
			System.out.println("\n<<회원 가입 중 예외 발생>>");
			e.printStackTrace();
		}
		
	}
	
	private void findId() {
		
		System.out.println("\n[아이디 찾기]\n");
	     try {
	    	 System.out.print("이름 : ");
	    	 String userName = sc.next();
	    	 
	    	 String userId = service.findId(userName);
	    	 
	    	 if(userId == null) {
	    		 System.out.println("\n[개인 정보가 일치하지 않습니다.]\n");
	    		 } else {
	    			 System.out.printf("\n[%s님의 아이디 => %s]\n", userName, userId);
	    			 }	    	 
	     } catch(Exception e) {
		        System.out.println("\n<<아이디 찾기 중 예외 발생>>\n");
		        e.printStackTrace();
		     }
	     
	}
	
	private void findPw() {
		
	     System.out.println("\n[비밀번호 찾기]\n");
	     
	     try {
	        System.out.print("아이디 : ");
	        String userId = sc.next();
	       
	        System.out.print("이름 : ");
	        String userName = sc.next();
	       
	        String userPw = service.findPw(userId, userName);  
	       
	        if(userPw == null) {
	           System.out.println("\n[개인 정보가 일치하지 않습니다.]\n");
	        } else {
	           System.out.printf("\n[%s님의 비밀번호 => %s]\n", userId, userPw);
	        }
	     }catch(Exception e) {
	        System.out.println("\n<<비밀번호 찾기 중 예외 발생>>\n");
	        e.printStackTrace();
	     }
		
	}
	
	
	
	
	
	
	
	
	
	
}