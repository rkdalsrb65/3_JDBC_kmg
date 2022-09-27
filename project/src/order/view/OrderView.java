package order.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import order.model.service.OrderService;

public class OrderView {

	private Scanner sc = new Scanner(System.in);	

	private OrderService oService = new OrderService();
	
	public void ordermenu() {
		
		int input = -1;
		
		do {
			try {
				
				System.out.println("\n ★ 주문 기능 ★ \n");
				System.out.println("1. 주문 목록 조회");
				System.out.println("2. 주문 상세 조회");
				System.out.println("3. 주문 게시글 작성");
				System.out.println("4. 주문 게시글 검색");
				System.out.println("0. 로그인메뉴로 이동");
				
				System.out.print("\n메뉴 선택 : ");
				input = sc.nextInt();
				sc.nextLine(); // 입력 버퍼 개행 문자 제거
				
				System.out.println();
				
				switch(input) {
				case 1: /*selectOrder();*/ break; 	// 주문 목록 조회
				case 2:  break; 	// 주문 상세 조회
				case 3:  break; 	// 주문 게시글 등록 (삽입)
				case 4:  break; 	// 주문 게시글 검색
				case 0: System.out.println("[로그인 메뉴로 이동합니다.]"); break;
				default : System.out.println("메뉴에 작성된 번호만 입력 해주세요.");
				}
				
				System.out.println();
				
			} catch (InputMismatchException e) {
				System.out.println("\n<<입력 형식이 올바르지 않습니다.>>\n");
				sc.nextLine(); // 입력 버퍼에 남아있는 잘못된 문자열 제거
			}
			
		} while(input != 0);		
		
	}

	
	
	
}