package board.vo;

//게시글 정보
public class Board {
	
	private int num;
	private String id;
	private String name; //외래키 참조용 회원명 변수
	private String title;
	private String contents;
	private String inputdate;
	
	
	
	//생성자
	public Board() {
		super();
	}
	
	

	public Board(String id, String title, String contents) {
		super();
		this.id = id;
		this.title = title;
		this.contents = contents;
	}



	//getters, setters
	public int getNum() {
		return num;
	}


	public void setNum(int num) {
		this.num = num;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getContents() {
		return contents;
	}


	public void setContents(String contents) {
		this.contents = contents;
	}


	public String getInputdate() {
		return inputdate;
	}


	public void setInputdate(String inpudate) {
		this.inputdate = inpudate;
	}
	
	
	//toString()
	@Override
	public String toString() {
		return "Board [num=" + num + ", id=" + id + ", name=" + name + ", title=" + title + ", contents=" + contents
				+ ", inpudate=" + getInputdate() + "]";
	}

	
}

