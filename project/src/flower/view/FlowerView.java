package flower.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import flower.model.service.FlowerService;
import flower.vo.Flower;

public class FlowerView {
	
	private Scanner sc = new Scanner(System.in);
	
	// 회원 관련 서비스를 제공하는 객체 생성
	private FlowerService service = new FlowerService();
	
	// 로그인 회원 정보 저장용 변수
	private Flower loginFlower = null;
	
	//메뉴 번호를 입력 받기 위한 변수
	private int input = -1;
	
	
	public void mainMenu() {
		
		do {
			try {

				if (loginFlower == null) {

					System.out.println("\n***** 게시판 프로그램 *****\n");
					System.out.println("1. 로그인");
					System.out.println("2. 회원 가입");
					System.out.println("3. 비회원 로그인");
					System.out.println("0. 프로그램 종료");

					System.out.print("\n메뉴 선택 : ");
					input = sc.nextInt();
					sc.nextLine(); // 입력 버퍼 개행문자 제거

					System.out.println();

					switch (input) {
					case 1: break; // 로그인
					case 2: break;// 회원 가입
					case 3: break;// 비회원 로그인
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}