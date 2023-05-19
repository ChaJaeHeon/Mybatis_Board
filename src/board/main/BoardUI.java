package board.main;

import java.util.ArrayList;
import java.util.Scanner;

import board.service.BoardService;
import board.vo.Board;
import board.vo.Member;

public class BoardUI {
	BoardService service = new BoardService();
	Scanner sc = new Scanner(System.in);
	String loginId;				//로그인 여부. 아이디/null
	
	public BoardUI() {
		String num;
		while (true) {
			menu();
			num = sc.nextLine();
			//메인메뉴 출력
			if(loginId==null) {
				switch (num) {
				case "1" : signup(); break;
				case "2" : login(); break;
				}
			}	
			else {
			switch (num) {
			case "3" : logout(); break;
			case "4" : write(); break;
			case "5" : boardList(); break;
			case "6" : getNum(); break;
			case "7" : getCon(); break;
			case "8" : delete(); break;
			case "9" : update(); break;
			case "0" : exit(); return;
			default :
				System.out.println("선택오류 입니다. 다시 선택하세요.");
				continue;
				}
			}
			}
			//번호 선택
			//선택에 따라 분기
		}

	//메뉴 출력
	void menu() {
		
		
		System.out.println("[  사용자 게시판  ]");
		if(this.loginId == null) {
			System.out.println("1. 가입");
			System.out.println("2. 로그인");
		}
		else {
			System.out.println(loginId+"님 로그인 중");
			System.out.println("3. 로그아웃");
			System.out.println("4. 글쓰기");
			System.out.println("5. 글목록");
			System.out.println("6. 글읽기");
			System.out.println("7. 글내용검색");
			System.out.println("8. 글삭제");
			System.out.println("9. 글수정");
			// 6.글읽기-글번호 입력하면 해당 글내용을 출력
			// 7.게시판 검색- 단어를 입력받아 제목이나 본문에 포함되어 있는 글 출력(대소문자 구분x)
			// 8.삭제- 글번호를 입력받아서 삭제(로그인한 본인 글인 경우에만)
			// 9.수정- 글번호를 입력받아서 -> 해당 글 출력 -> 제목/내용 입력받아서 수정 -> 본인 글일 경우에만 수정 가능
		}
		System.out.println("0. 종료");
		System.out.print("선택> ");
	}
	//가입
	void signup() {
		String id, name, password;
		
		System.out.println("가입을 시작합니다.");
		System.out.print("ID을 입력해주세요: ");
		id= sc.nextLine();
		System.out.print("PASSWORD를 입력해주세요: ");
		password = sc.nextLine();
		System.out.println("이름을 입력해주세요: ");
		name=sc.nextLine();
		
		Member m = new Member(id, password, name);
		int cnt = service.join(m);
		if (cnt == 1)
			System.out.println("가입 완료되었습니다.");
		else
			System.out.println("가입실패");
	}
	//로그인
	void login() {
		System.out.println("로그인을 시작합니다.");
		System.out.print("ID: ");
		String id = sc.nextLine();
		System.out.println("PASSWORD: ");
		String password = sc.nextLine();
		
		Member m = new Member();
		m.setId(id);
		m.setPassword(password);
		
		if (service.login(m)) {
			this.loginId=id;
		}
		else {
			System.out.println("ID 또는 비밀번호가 틀립니다.");
		}
	}
	
	void logout() {
		this.loginId=null;
		System.out.println("로그아웃 되었습니다.");
	}
	
	void write() {
		String title, contents;
		System.out.println("제목을 입력하세요: ");
		title = sc.nextLine();
		System.out.println("내용을 입력하세요: ");
		contents = sc.nextLine();
		
		Board board =new Board();
		board.setTitle(title);
		board.setContents(contents);
		board.setId(loginId);
		
		service.write(board);
		System.out.println("저장되었습니다.");
		
	}
	
	void boardList() {
		ArrayList<Board> board = new ArrayList<>();
		System.out.println("[   글목록   ]");
		board=service.boardList();
		for(int i =0; i<board.size(); i++) {
			System.out.println(board.get(i).getNum()+ "\t"+ board.get(i).getName()+ "\t"+board.get(i).getTitle()+"\t"+board.get(i).getContents()+"\t"+board.get(i).getInputdate());
		}
		
	}
	
	void getNum() {
		ArrayList<Board> board = new ArrayList<>();
		System.out.println("글의 번호를 입력하세요: ");
		String num = sc.nextLine();
		board = service.getNum(num);
		
		if(board.isEmpty()) 
			System.out.println("입력한 번호의 글이 없습니다.");
		else {
			for(int i = 0; i<board.size(); i++)
				System.out.println(board.get(i));
		}		
	}
	
	void getCon() {
		ArrayList<Board> board = new ArrayList<>();
		System.out.println("글의 내용을 입력하세요: ");
		String con = sc.nextLine();
		board = service.getCon(con);
		
		if(board.isEmpty()) 
			System.out.println("입력한 내용의 글이 없습니다.");
		else {
			for(int i = 0; i<board.size(); i++)
				System.out.println(board.get(i));
		}
	}
	
	void delete() {
		int num = 1;
		Board board = new Board();
		System.out.println("삭제할 번호를 입력하세요:");
		num = sc.nextInt();
		sc.nextLine();
		if(!loginId.equals(board.getId())) {
			System.out.println("작성자가 아닙니다.");
			return;
		}
		if(service.delete(num)==0)
			System.out.println("조회된 번호가 없습니다.");
		else
			System.out.println(num+"번이 삭제되었습니다.");
		sc.nextLine();
	}
	
	void update() {
		ArrayList<Board> board = new ArrayList<>();
		String n;
		System.out.println("삭제할 번호를 입력하세요.");
		n=sc.nextLine();
		board = service.getNum(n);
		
		if(board==null) 
			System.out.println("입력한 번호의 글이 없습니다.");
		else {
			for(int i = 0; i<board.size(); i++)
				System.out.println(board.get(i));
		}
		if(!loginId.equals(board.get(0).getId())) {
			System.out.println("작성자가 아닙니다.");
			return;
		}
		System.out.println("제목을 입력하세요: ");
		String title = sc.nextLine();
		System.out.println("내용을 입력하세요: ");
		String contents = sc.nextLine();
		service.update(title, contents, n);
		
	}
	
	//프로그램 종료
	void exit() {
		System.out.println("프로그램을 종료합니다.");
	}
}
