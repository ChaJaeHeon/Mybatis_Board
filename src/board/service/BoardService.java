package board.service;

import java.util.ArrayList;

import board.dao.BoardDAO;
import board.vo.Board;
import board.vo.Member;

public class BoardService {
	BoardDAO dao = new BoardDAO();
	
	//가입
	public int join(Member m) {
		return dao.insertMember(m);
	}
	//로그인
	public boolean login(Member m) {
		Member result = dao.getMember(m.getId());
		if (result != null && result.getPassword().equals(m.getPassword())) 
			return true;
		
		else 
			return false;
	}
	public int write(Board board) {
		return dao.writeboard(board);
	}
	
	public ArrayList<Board> boardList() {
		return dao.showboard();
	}
	
	public ArrayList<Board> getNum(String num) {
		return dao.getNum(num);
	}
	
	public ArrayList<Board> getCon(String con) {
		return dao.getCon(con);
	}
	
	public int delete(int num) {
		return dao.delete(num);
	}
	public int update(String title, String contents, String n) {
		return dao.update(title, contents, n);
	}

}
