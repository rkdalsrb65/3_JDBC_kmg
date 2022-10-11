package view;

import java.util.InputMismatchException;
import java.util.Scanner;

import main.MainMain;
import service.MainService;

public class MainView {
	

	private Scanner sc = new Scanner(System.in);
	
	private MainService service = new MainService();
	
	public static MainMain loginMember = null;
	
	public void mainMenu() {
		
		int input = -1;
		
		do {
			try {
				
				if (loginMember == null) {
					
					System.out.println("메인 홈페이지");
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
//					case 1: login(); break; // 로그인
					case 2: signUp(); break; // 회원 가입
//					case 3: findId(); break; // 아이디 찾기
//					case 4: findPw(); break; // 비밀번호 찾기
					case 0: System.out.println("프로그램 종료"); break;
					default: System.out.println("메뉴에 작성된 번호만 입력해주세요.");
					}
				} else { // 로그인 O
					
					System.out.println("***** 로그인 메뉴 *****");
					System.out.println("1. 회원 기능");
					System.out.println("2. 게시판 기능");
					System.out.println("0. 로그아웃");
					System.out.println("99. 프로그램 종료");
					
					System.out.println("\n메뉴 선택 : ");
					input = sc.nextInt();

				System.out.println();					
					
				switch (input) {
				// 회원 기능 서브 메뉴 출력
//				case 1: memberView.memberMenu(loginMember); break;
				
				// -> 회원 정보가 필요한 경우 static에서 얻어와 사용할 예정
//				case 2: boardView.boardMenu(); break;
				
				case 0: loginMember = null; System.out.println("\n[로그아웃 되었습니다.]");
				input = -1; // do-while문이 종료되지 않도록 0이 아닌 값으로 변경 
				break; // 로그아웃 == loginMember가 참조하는 객체 없음(== null)
				// 로그인 == loginMember가 참조하는 객체 존재
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
	
	private void signUp() {
		System.out.println("[회원 가입]");
		
		String memberId = null;
		String memberPw1 = null;
		String memberPw2 = null;
		String memberName = null;
		String memberGender = null;
		
		try {
			
			while(true) {
				
				System.out.print("아이디 입력 : ");
				memberId = sc.next();
				
				int result = service.idDupCheck(memberId);
				
				if(result == 0) {
					System.out.println("[사용 가능한 아이디]");
					break;
				} else {
					System.out.println("[이미 사용중인 아이디 입니다.]");
				}
				System.out.println();
			}
			
			
			
			while(true) {
				System.out.print("비밀번호 : ");
				memberPw1 = sc.next();
				
				System.out.print("비밀번호 확인 : ");
				memberPw2 = sc.next();
				
				System.out.println();
				if(memberPw1.equals(memberPw2)) {
					System.out.println("[일치합니다]");
					break;
				} else {
					System.out.println("[비밀번호가 일치하지 않습니다. 다시 입력 해주세요.]");
				}
				System.out.println();
				
			}
			
			System.out.print("이름 입력 : ");
			memberName = sc.next();
			
			while(true) {
				System.out.println("성별 입력(M/F) : ");
				memberGender = sc.next().toUpperCase();
				
				System.out.println();
				
				if(memberGender.equals("M") || memberGender.equals("F")) {
					break;
				} else {
					System.out.println(" 대문자만 입력하세용 ");
				}
				System.out.println();
				}
			
			MainMain mainMain = new MainMain(memberId, memberPw2, memberName, memberGender);
			
			int result = service.signUp(mainMain);
			
			System.out.println();
			
			if(result > 0) {
				System.out.println("[회원 가입 성공]");
				
			} else {
				System.out.println("[회원 가입 실패]");
			}
			System.out.println();
			
		} catch (Exception e) {
			System.out.println("회원 가입 중 예외 발생");
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
}
