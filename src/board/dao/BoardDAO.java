package board.dao;

import java.util.ArrayList;


import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import board.vo.Board;
import board.vo.Member;
import board.dao.BoardMapper;

public class BoardDAO {
	SqlSessionFactory factory = MybatisConfig.getSqlSessionFactory();
	
	public int insertMember(Member m) {
		int cnt = 0;
		SqlSession ss = null;
		
		ss = factory.openSession();
		BoardMapper mapper = ss.getMapper(BoardMapper.class);
		cnt=mapper.insertMember(m);
		ss.commit();
	
		return cnt;
	}

	public Member getMember(String id) {
		SqlSession ss = null;
		Member m;
		
		ss = factory.openSession();
		BoardMapper mapper = ss.getMapper(BoardMapper.class);
		m=mapper.getMember(id);
		ss.commit();
	
		return m;
		
	}

	public int writeboard(Board board) {
		int cnt = 0;
		SqlSession ss = null;
		
		ss = factory.openSession();
		BoardMapper mapper = ss.getMapper(BoardMapper.class);
		cnt=mapper.writeboard(board);
		ss.commit();
		
		return cnt;
	}

	public ArrayList<Board> showboard() {
		ArrayList<Board> board = new ArrayList<>();
		SqlSession ss = null;
		
		ss = factory.openSession();
		BoardMapper mapper = ss.getMapper(BoardMapper.class);
		board=mapper.showboard();
		ss.close();
		
		return board;
	}

	public ArrayList<Board> getNum(String num) {
		SqlSession ss = factory.openSession();
		ArrayList<Board> list = null;
		
		BoardMapper m = ss.getMapper(BoardMapper.class);
		list = m.getNum(num);
		ss.close();
		
		return list;
	}

	public ArrayList<Board> getCon(String con) {
		SqlSession ss = factory.openSession();
		ArrayList<Board> list = null;
		
		BoardMapper m = ss.getMapper(BoardMapper.class);
		list = m.getCon(con);
		ss.close();
		
		return list;
	}

	public int delete(int num) {
		int cnt = 0;
		SqlSession ss = null;
		
		ss = factory.openSession();
		BoardMapper mapper = ss.getMapper(BoardMapper.class);
		cnt=mapper.delete(num);
		ss.commit();
		
		return cnt;
	}

	public int update(String title, String contents, String n) {
		SqlSession ss = null;
		int cnt = 0;
		
		try {
			ss = factory.openSession();   //트랜젝션 시작
			BoardMapper mapper = ss.getMapper(BoardMapper.class);
			cnt=mapper.update(title, contents, n);
			ss.commit();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (ss != null) ss.close();
		}
		return cnt;
	}



}


