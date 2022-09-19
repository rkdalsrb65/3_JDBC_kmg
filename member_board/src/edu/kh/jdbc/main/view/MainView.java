package edu.kh.jdbc.main.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import edu.kh.jdbc.main.model.service.MainService;

// 메인 화면
public class MainView {
	
	private Scanner sc = new Scanner(System.in);
	
	private MainService service = new MainService();
	
	/**
	 * 메인 메뉴 출력 메서드
	 */
	public void mainMenu() {
		
		int input = -1;
		
		do {
			try {
				
				System.out.println("\n***** 회원제 게시판 프로그램 *****\n");
				System.out.println("1. 로그인");
				System.out.println("2. 회원 가입");
				System.out.println("0. 프로그램 종료");
				
				System.out.print("\n메뉴 선택 : ");
				input = sc.nextInt();
				sc.nextLine(); // 입력 버퍼 개행문자 제거
				
				System.out.println();
				
				switch(input) {
				case 1: break;
				case 2: signUp(); break;
				case 0: System.out.println("프로그램 종료");break;
				default : System.out.println("메뉴에 작성된 번호만 입력해주세요.");
				}
				
			} catch(InputMismatchException e) {
				System.out.println("\n<<입력 형식이 올바르지 않습니다.>>");
				sc.nextLine(); // 입력 버퍼에 남아있는 잘못된 문자열 제거
			}
			
		} while(input != 0);
		
	}

	/**
	 * 회원 가입 화면
	 */
	private void signUp() {
		System.out.println("[회원 가입]");
		
		String memberId = null;
		String memberPw1 = null;
		String memberPw2 = null;
		String memberName = null;
		String memberGender = null;
		
		// 아이디를 입력 받아 중복이 아닐 때 까지 반복
		
		while(true) {
			
			System.out.print("아이디 입력 : ");
			memberId = sc.next();
			
			// 입력 받은 아이디를 매개변수로 전달하여
			// 중복여부를 검사하는 서비스 호출 후 결과(1/0) 반환 받기
			int result = service.idDupCheck(memberId);
			
			
		}
		
		
	}
}