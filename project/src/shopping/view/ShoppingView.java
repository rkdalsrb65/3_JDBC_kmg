package shopping.view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import Employee.view.EmployeeView;
import shopping.model.service.ShoppingService;
import shopping.vo.Shopping;

public class ShoppingView {

	private Scanner sc = new Scanner(System.in);
	
	private ShoppingService sService = new ShoppingService();
	
	public void shoppingmenu() {
	
		int input = -1;
		
		do {
			try {
				
				System.out.println("\n ★ 쇼핑몰 기능 ★ \n");
				System.out.println("1. 쇼핑몰 목록 조회");
				System.out.println("2. 쇼핑몰 상세 조회");
				System.out.println("3. 게시글 작성");
				System.out.println("4. 게시글 검색");
				System.out.println("0. 로그인메뉴로 이동");
				
				System.out.print("\n메뉴 선택 : ");
				input = sc.nextInt();
				sc.nextLine(); // 입력 버퍼 개행 문자 제거
				
				System.out.println();
				
				switch(input) {
				case 1: selectAllShopping(); break; // 쇼핑몰 목록 조회
				case 2: selectShopping(); break; // 쇼핑몰 상세 조회
				case 3:  break; 
				case 4:  break;
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
	
	/**
	 * 쇼핑몰 목록 조회
	 */
	private void selectAllShopping() {
		
		System.out.println("\n[쇼핑몰 목록 조회]\n");

		try {
			List<Shopping> shoppingList = sService.selectAllShopping();
			// -> DAO에서 new ArrayList<>(); 구문으로 인해
			// 반환되는 조회 결과는 null이 될 수 없다!!!

			if (shoppingList.isEmpty()) { // 조회 결과가 없을 경우
				System.out.println("게시글이 존재하지 않습니다.");

			} else {
				for (Shopping s : shoppingList) {

					// 번호 | 제목 | 이름 | 시간(3시간전) | 조회수
					System.out.printf("%d | %s[%d] | %s | %s | %d\n", s.getShoppingNo(), s.getShoppingTitle(),
							s.getCommentCount(), s.getEmployeeName(), s.getCreateDate(), s.getReadCount());
				}
			}

		} catch (Exception e) {
			System.out.println("\n<<쇼핑몰 목록 조회 중 예외 발생>>\n");
			e.printStackTrace();
		}

	}
	
	
	private void selectShopping() {
		
		System.out.println("\n[쇼핑몰 상세 조회]\n");
		
		try {
			System.out.println("게시글 번호 입력 : ");
			int shoppingNo = sc.nextInt();
			sc.nextLine();
			
			// 게시글 상세 조회 서비스 호출 후 결과 반환 받기
			Shopping shopping = sService.selectShopping(shoppingNo, EmployeeView.loginEmployee.getEmployeeNo());
											//게시글번호, 로그인한 회원의 회원번호
											//			-> 자신의 글 조회수 증가 X​
	        if (shopping != null) {
	            System.out.println("--------------------------------------------------------");
	            System.out.printf("글번호 : %d \n제목 : %s\n", shopping.getShoppingNo(), shopping.getShoppingTitle());
	            System.out.printf("작성자 : %s | 작성일 : %s  \n조회수 : %d\n",
	            		shopping.getEmployeeName(), shopping.getCreateDate(), shopping.getReadCount());
	           System.out.println("--------------------------------------------------------\n");
	           System.out.println(shopping.getShoppingContent());
	           System.out.println("\n--------------------------------------------------------");
	           
	           // 댓글 목록
	           if(!shopping.getComList().isEmpty()) {
	              for(Com c : shopping.getComList()) {
	                 System.out.printf("댓글번호: %d   작성자: %s  작성일: %s\n%s\n",
	                       c.getComNo(), c.getEmployeeName(), c.getCreateDate(), c.getComContent());
	                 System.out.println(" --------------------------------------------------------");
	              }
	           }
	           
	           // 댓글 등록, 수정, 삭제
	           // 수정/삭제 메뉴
	           subShoppingMenu(shopping);
	           
	           
	        } else {
	           System.out.println("\n[해당 번호의 게시글이 존재하지 않습니다.]\n");
	        }	
		} catch (Exception e) {
			System.out.println("\n<<게시글 상세 조회 중 예외 발생>>\n");
			e.printStackTrace();
		}		
		
		
		
		
	}
	
	
	
	
	
	
	
}
