package shopping.view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

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
				case 2:  break; // 쇼핑몰 상세 조회
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

					// 3 | 샘플 제목3[4] | 유저삼 | 3시간전 | 10
					System.out.printf("%d | %s[%d] | %s | %s | %d\n", s.getShoppingNo(), s.getShoppingTitle(),
							s.getCommentCount(), s.getEmployeeName(), s.getCreateDate(), s.getReadCount());
				}
			}

		} catch (Exception e) {
			System.out.println("\n<<게시글 목록 조회 중 예외 발생>>\n");
			e.printStackTrace();
		}

	}
	
	
	
	
	
	
	
	
	
	
}
