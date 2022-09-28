package order.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import Employee.view.EmployeeView;
import order.model.service.OrderService;
import order.vo.Order;
import shopping.vo.Com;
import shopping.vo.Shopping;

public class OrderView {

	private Scanner sc = new Scanner(System.in);	

	private OrderService oService = new OrderService();
	
	public void orderMenu() {
		
		int input = -1;
		
		do {
			try {
				
				System.out.println("\n ★ 주문 기능 ★ \n");
				System.out.println("1. 주문 신청");
				System.out.println("2. 주문 취소");
				System.out.println("0. 로그인메뉴로 이동");
				
				System.out.print("\n메뉴 선택 : ");
				input = sc.nextInt();
				sc.nextLine(); // 입력 버퍼 개행 문자 제거
				
				System.out.println();
				
				switch(input) {
				case 1: requestOrder(); break; 	// 주문 신청
				case 2: cancelOrder(); break; 	// 주문 취소
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
	
	private void requestOrder() {
		
		System.out.println("\n[주문 신청]\n");	
		
		try {
			System.out.println("주문 번호 입력 : ");
			int orderNo = sc.nextInt();
			sc.nextLine();
			
			// 주문 신청 서비스 호출 후 결과 반환 받기
			Order order = oService.requestOrder(orderNo, EmployeeView.loginEmployee.getEmployeeNo());
											//게시글번호, 로그인한 회원의 회원번호
											//			-> 자신의 글 조회수 증가 X​
	        if (order != null) {
	            System.out.println("--------------------------------------------------------");
	            System.out.printf("글번호 : %d \n제목 : %s\n", order.getShoppingNo(), order.getShoppingTitle());
	            System.out.printf("작성자 : %s | 작성일 : %s  \n조회수 : %d\n",
	            		order.getEmployeeName(), order.getCreateDate(), order.getReadCount());
	           System.out.println("--------------------------------------------------------\n");
	           System.out.println(order.getShoppingContent());
	           System.out.println("\n--------------------------------------------------------");
	            
	        } else {
	           System.out.println("\n[주문 할 수 없는 번호 입니다.]\n");
	        }	
		} catch (Exception e) {
			System.out.println("\n<<주문 신청 중 예외 발생>>\n");
			e.printStackTrace();
		}		
		
	}
	
	private void cancelOrder() {
		
		System.out.println("\n[주문 취소]\n");	
		
		
		
	}
}