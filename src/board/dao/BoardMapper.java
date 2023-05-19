package board.dao;

import java.util.ArrayList;

import board.vo.Board;
import board.vo.Member;

public interface BoardMapper {

	public int insertMember(Member m);
	
	public Member getMember(String id);

	public int writeboard(Board board);

	public ArrayList<Board> showboard();

	public ArrayList<Board> getNum(String num);

	public ArrayList<Board> getCon(String con);

	public int delete(int num);

	public int update(String title, String contents,  n);

}
