package shopping.view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import Employee.view.EmployeeView;
import shopping.model.service.ComService;
import shopping.model.service.ShoppingService;
import shopping.vo.Com;
import shopping.vo.Shopping;

public class ShoppingView {

	private Scanner sc = new Scanner(System.in);
	
	private ShoppingService sService = new ShoppingService();
	
	private ComService cService = new ComService();	
	
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
				case 2: selectShopping(); break; 	// 쇼핑몰 상세 조회
				case 3: insertShopping(); break; 	// 게시글 등록 (삽입)
				case 4: searchShopping(); break; 	// 게시글 검색
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
							s.getComCount(), s.getEmployeeName(), s.getCreateDate(), s.getReadCount());
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
	
	/** 게시글 상세조회 시 출력되는 서브 메뉴
	 * @param board(상세조회된 게시글 + 작성자 번호 + 댓글 목록)
	 */
	private void subShoppingMenu(Shopping shopping) {
		
		int input = -1;
		
		try {
			System.out.println("1) 댓글 등록");
			System.out.println("2) 댓글 수정");
			System.out.println("3) 댓글 삭제");
			
			// 로그인한 회원과 게시글 작성자가 같은 경우에만 출력되는 메뉴
			if(EmployeeView.loginEmployee.getEmployeeNo() == shopping.getEmployeeNo()) {
				System.out.println("4) 게시글 수정");
				System.out.println("5) 게시글 삭제");
			}
			
			System.out.println("0) 게시판 메뉴로 돌아가기");
			
			System.out.print("\n서브 메뉴 선택 : ");
			input = sc.nextInt();
			sc.nextLine();
			
			int employeeNo = EmployeeView.loginEmployee.getEmployeeNo();
			
			switch(input) {
			case 1 : insertCom(shopping.getShoppingNo(), employeeNo); break;
			case 2 : updateCom(shopping.getComList(), employeeNo); break;
			case 3 : deleteCom(shopping.getComList(), employeeNo); break;
			case 0 : System.out.println("\n[게시판 메뉴로 돌아갑니다...]\n"); break;
			
			case 4 : case 5 : // 4 또는 5 입력 시
				
				// 4 또는 5를 입력한 회원이 게시글 작성자인 경우
				if(EmployeeView.loginEmployee.getEmployeeNo() == shopping.getEmployeeNo()) {
					if(input == 4) {
						// 게시글 수정 호출
						updateShopping(shopping.getShoppingNo());
					}
					if(input == 5) {
						// 게시글 삭제 호출
						deleteShopping(shopping.getShoppingNo());
					}
					break; // switch 문 종료
				}
			default : System.out.println("\n[메뉴에 작성된 번호만 입력해주세요.]\n");
			}
			
			// 댓글 등록, 수정, 삭제 선택 시 각각의 서비스 
			// 메서드 종료 후 다시 서브메뉴 메서드 호출
			if(input > 0 && input < 5) {
				
				try {
					shopping = sService.selectShopping(shopping.getShoppingNo(), EmployeeView.loginEmployee.getEmployeeNo());
		 
		              System.out.println(" --------------------------------------------------------");
		              System.out.printf("글번호 : %d | 제목 : %s\n", shopping.getShoppingNo(), shopping.getShoppingTitle());
		              System.out.printf("작성자ID : %s | 작성일 : %s | 조회수 : %d\n",
		            		  shopping.getEmployeeName(), shopping.getCreateDate().toString(), shopping.getReadCount());
		              System.out.println(" --------------------------------------------------------");
		              System.out.println(shopping.getShoppingContent());
		              System.out.println(" --------------------------------------------------------");
		 
		              // 댓글 목록
		              if(!shopping.getComList().isEmpty()) {
		                 for(Com c : shopping.getComList()) {
		                    System.out.printf("댓글번호: %d   작성자: %s  작성일: %s\n%s\n",
		                          c.getComNo(), c.getEmployeeName(), c.getCreateDate(), c.getComContent());
		                    System.out.println(" --------------------------------------------------------");
		                 }
		              }
		           }catch (Exception e) {
		              e.printStackTrace();
		           }	
				subShoppingMenu(shopping);
			}
		} catch (InputMismatchException e) {
			System.out.println("\n<<입력 형식이 올바르지 않습니다.>>\n");
			sc.nextLine(); // 입력 버퍼에 남아있는 잘못된 문자열 제거			
		}	
	}	
	
	/** 댓글 등록
	 * @param sNo
	 * @param eNo
	 */
	private void insertCom(int sNo, int eNo) {
		
		try {
			System.out.println("\n[댓글 등록]\n");
			
			String content = inputContent();
			
			// DB INSERT 시 필요한 값을 하나의 Comment 객체에 저장
			Com com = new Com();
			com.setComContent(content);
			com.setShoppingNo(sNo);
			com.setEmployeeNo(eNo);
			
			// 댓글 삽입 서비스 호출 후 결과 반환 받기
			int result = cService.insertCom(com);
			
			if(result > 0) {
				System.out.println("\n[댓글 등록 성공]\n");
			} else {
				System.out.println("\n[댓글 등록 실패...]\n");
			}
		} catch (Exception e) {
			System.out.println("\n<<댓글 등록 중 예외 발생>>\n");
			e.printStackTrace();
		}
	}
	
	/** 
	 * 내용 입력
	 * @return content
	 */
	private String inputContent() {
		String content = ""; // 빈 문자열
		
		String input = null; // 참조하는 객체가 없음
		
		System.out.println("입력 종료 시 ($exit) 입력");
		
		while(true) {
			input = sc.nextLine();
			
			if(input.equals("$exit")) {
				break;
			}
			
			// 입력된 내용을 content에 누적
			content += input + "\n";
			
		}
		return content;
	}
	
	/** 댓글 수정
	 * @param comList
	 * @param employeeNo
	 */
	private void updateCom(List<Com> comList, int employeeNo) {
		
		// 댓글 번호를 입력 받아
		// 1) 해당 댓글이 commentList에 있는지 검사
		// 2) 있다면 해당 댓글이 로그인한 회원이 작성한 글인지 검사
			
		try {
			System.out.println("\n[댓글 수정]\n");
			System.out.print("수정할 댓글 번호 입력 : ");
			int comNo = sc.nextInt();
			sc.nextLine();
			
			boolean flag = true;
			
			for(Com c : comList) {
				if(c.getComNo() == comNo) { // 댓글 번호 일치
					flag = false;
					
					if(c.getEmployeeNo() == employeeNo) { // 회원 번호 일치
						
						// 수정할 댓글 내용 입력 받기
						String content = inputContent();
						
						// 댓글 수정 서비스 호출
						int result = cService.updateCom(comNo, content);
						
						if(result > 0) {
							System.out.println("\n[댓글 수정 성공]\n");
						} else {
							System.out.println("\n[댓글 수정 실패...]\n");
						}
						
						} else {
							System.out.println("\n[자신의 댓글만 수정할 수 있습니다.]\n");
						}
					break; // 더 이상의 검사 불필요
					}
				} // for end
			if(flag) {
				System.out.println("\n[번호가 일치하는 댓글이 없습니다.]\n");
			}
		} catch (Exception e) {
			System.out.println("\n<<댓글 수정 중 예외 발생>>\n");
			e.printStackTrace();
		}
	}
	
	/** 댓글 삭제
	 * @param comList
	 * @param employeeNo
	 */
	private void deleteCom(List<Com> comList, int employeeNo) {
		
		// 댓글 번호를 입력 받아
		// 1) 해당 댓글이 commentList에 있는지 검사
		// 2) 있다면 해당 댓글이 로그인한 회원이 작성한 글인지 검사
			
		try {
			System.out.println("\n[댓글 삭제]\n");
			System.out.print("삭제할 댓글 번호 입력 : ");
			int comNo = sc.nextInt();
			sc.nextLine();
			
			boolean flag = true;
			
			for(Com c : comList) {
				if(c.getComNo() == comNo) { // 댓글 번호 일치
					flag = false;
					
					if(c.getEmployeeNo() == employeeNo) { // 회원 번호 일치
						
						// 정말 삭제? y/n
						// y인 경우 댓글 삭제 서비스 호출
						
						System.out.print("정말 삭제 하시겠습니까? (Y/N) : ");
						char ch = sc.next().toUpperCase().charAt(0);
						
						if(ch == 'Y') {
							int result = cService.deleteCom(comNo);
							
							if(result > 0) {
								System.out.println("\n[댓글 삭제 성공]\n");
								} else {
									System.out.println("\n[댓글 삭제 실패...]\n");
							}
							
						} else {
							System.out.println("\n[취소 되었습니다.]\n");
						}
						
						} else {
							System.out.println("\n[자신의 댓글만 삭제할 수 있습니다.]\n");
						}
					break; // 더 이상의 검사 불필요
					}
				} // for end
			if(flag) {
				System.out.println("\n[번호가 일치하는 댓글이 없습니다.]\n");
			}
		} catch (Exception e) {
			System.out.println("\n<<댓글 삭제 중 예외 발생>>\n");
			e.printStackTrace();
		}
	}	
	
	/** 게시글 수정
	 * @param shoppingNo
	 */
	private void updateShopping(int shoppingNo) {
		
		try {
			System.out.println("\n[게시글 수정]\n");
			
			System.out.print("수정할 제목 : ");
			String shoppingTitle = sc.nextLine();
			
			System.out.println("수정할 내용");
			String shoppingContent = inputContent();
			
			// 수정된 제목/내용 + 게시글 번호를 한 번에 전달하기위한 Board 객체 생성
			Shopping shopping = new Shopping();
			shopping.setShoppingNo(shoppingNo);
			shopping.setShoppingTitle(shoppingTitle);
			shopping.setShoppingContent(shoppingContent);
			
			// 수정 서비스 호출
			int result = sService.updateShopping(shopping);
			
			if(result > 0) {
				System.out.println("\n[게시글 수정 성공]\n");
			} else {
				System.out.println("\n[게시글 수정 실패...]\n");
			}
			
		} catch (Exception e) {
			System.out.println("\n<<게시글 수정 중 예외 발생>>\n");
			e.printStackTrace();
		}
	}
	
	/** 게시글 삭제
	 * @param shoppingNo
	 */
	private void deleteShopping(int shoppingNo) {
		
		try {
			System.out.println("\n[게시글 삭제]\n");
			
			System.out.print("정말 삭제 하시겠습니까? (Y/N) : ");
			char ch = sc.next().toLowerCase().charAt(0); // 소문자로 변환하여 첫 글자
			
			if(ch == 'y') {
				// 삭제 서비스 호출
				int result = sService.deleteShopping(shoppingNo);
				
				if(result > 0) {
					System.out.println("\n[게시글 삭제 성공]\n");
					} else {
						System.out.println("\n[게시글 삭제 실패...]\n");
				}
			} else {
				System.out.println("\n[삭제 취소]\n");
			}
		} catch (Exception e) {
			System.out.println("\n<<게시글 수정 중 예외 발생>>\n");
			e.printStackTrace();		
		}	
	}
	
	
	/**
	 *  게시글 등록(삽입)
	 */
	private void insertShopping() {
		
		try {
			System.out.println("\n[게시글 등록]\n");
			
			System.out.println("제목 : ");
			String shoppingTitle = sc.nextLine();
			
			System.out.println("내용 : ");
			String shoppingContent = inputContent();
			
			// Board 객체에 제목, 내용, 회원 번호를 담아서 서비스에 전달
			Shopping shopping = new Shopping();
			shopping.setShoppingTitle(shoppingTitle);
			shopping.setShoppingContent(shoppingContent);
			shopping.setEmployeeNo(EmployeeView.loginEmployee.getEmployeeNo());
			
			int result = sService.insertShopping(shopping);
			// 0 또는 생성된 게시글 번호(0 초과)
			
			if(result > 0) {
				System.out.println("\n[게시글이 등록되었습니다.]\n");
			
				// 게시글 상세 조회 서비스 호출 후 결과 반환 받기
				Shopping s = sService.selectShopping(result, EmployeeView.loginEmployee.getEmployeeNo());
												//게시글번호, 로그인한 회원의 회원번호
												//			-> 자신의 글 조회수 증가 X​
		        if (s != null) {
		            System.out.println("--------------------------------------------------------");
		            System.out.printf("글번호 : %d \n제목 : %s\n", s.getShoppingNo(), s.getShoppingTitle());
		            System.out.printf("작성자 : %s | 작성일 : %s  \n조회수 : %d\n",
		                  s.getEmployeeName(), s.getCreateDate(), s.getReadCount());
		           System.out.println("--------------------------------------------------------\n");
		           System.out.println(s.getShoppingContent());
		           System.out.println("\n--------------------------------------------------------");
		           
		           // 댓글 목록
		           if(!s.getComList().isEmpty()) {
		              for(Com c : s.getComList()) {
		                 System.out.printf("댓글번호: %d   작성자: %s  작성일: %s\n%s\n",
		                       c.getComNo(), c.getEmployeeName(), c.getCreateDate(), c.getComContent());
		                 System.out.println(" --------------------------------------------------------");
		              }
		           }
		           
		           // 댓글 등록, 수정, 삭제
		           // 수정/삭제 메뉴
		           subShoppingMenu(s);
		           
		        } else {
		           System.out.println("\n[해당 번호의 게시글이 존재하지 않습니다.]\n");
		        }
			} else {
				System.out.println("\n[게시글 등록 실패]\n");
			}
		} catch(Exception e) {
			System.out.println("\n<<게시글 등록 중 예외 발생>>\n");
			e.printStackTrace();
		}
	}
	
	private void searchShopping() {
		
		try {
			System.out.println("\n[게시글 검색]\n");
			
			System.out.println("1) 제목");
			System.out.println("2) 내용");
			System.out.println("3) 제목 + 내용");
			System.out.println("4) 작성자");
			System.out.print("검색 조건 선택 : ");
			
			int condition = sc.nextInt();
			sc.nextLine();
			
			if(condition >= 1 && condition <= 4) { // 정상 입력
			
				System.out.println("검색어 입력 : ");
				String query = sc.nextLine();
				
				// 검색 서비스 호출 후 결과 반환 받기
				List<Shopping> shoppingList = sService.searchShopping(condition, query);
				
				if(shoppingList.isEmpty()) { // 조회 결과가 없을 경우
					System.out.println("\n[검색 결과가 없습니다.]\n");
					
				} else {
					for(Shopping s : shoppingList) {
						
						// 3 | 샘플 제목3[4] | 유저삼 | 3시간전 | 10
						System.out.printf("%d | %s[%d] | %s | %s | %d\n",
								s.getShoppingNo(), s.getShoppingTitle(), s.getComCount(),
								s.getEmployeeName(), s.getCreateDate(), s.getReadCount());
					}
				}				
	
			} else { // 비정상 입력
				System.out.println("\n[1~4번 사이의 숫자를 입력해주세요]\n");
			}
			
		} catch (Exception e) {
			System.out.println("\n<<게시글 검색 중 예외 발생>>\n");
			e.printStackTrace();
		}		
		
	}
	
	
}
