package edu.kh.jdbc.run;

import java.util.Scanner;

import edu.kh.jdbc.model.service.TestService;
import edu.kh.jdbc.model.vo.TestVO;

public class Run3 {
	public static void main(String[] args) {
		
		// 번호, 제목, 내용을 입력받아
		// 번호가 일치하는 행의 제목, 내용 수정
		
		// 수정 성공 시 -> 수정되었습니다.
		// 수정 실패 시 -> 일치하는 번호가 없습니다.
		// 예외 발생 시 -> 수정 중 예외가 발생했습니다.
		
		Scanner sc = new Scanner(System.in);
		
		TestService service = new TestService();
		
		System.out.print("번호 : ");
		int num = sc.nextInt();
		sc.nextLine();
		
		System.out.print("제목 : ");
		String title = sc.nextLine();
		
		System.out.print("내용 : ");
		String content = sc.nextLine();
		
		TestVO vo = new TestVO(num, title, content);
		
		try {

			int result = service.update(vo);
			
			if(result > 0) {
				
				System.out.println("수정되었습니다.");
			} else {
				System.out.println("일치하는 번호가 없습니다.");
			}			
		
		} catch(Exception e) {
			// service, dao 수행 중 발생한 예외를 처리
			System.out.println("수정 중 예외가 발생했습니다.");
			e.printStackTrace();
		}		
		
		
	}

}