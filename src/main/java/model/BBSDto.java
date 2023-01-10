package model;

import java.sql.Date;

public class BBSDto { //글 한개(하나의 레코드)를 저장할 수 있는 타입(클래스)
	//속성(멤버변수)]
	private String no; //글번호
	private String title; //글제목
	private String content; //글내용
	private String hitCount; //조회수
	private java.sql.Date postDate; //작성일
	private String id; //작성자 id
	
	private String name;//이름 저장용
	
	//생성자]
	public BBSDto() {}

	

	public BBSDto(String no, String title, String content, String hitCount, Date postDate, String id) {
		this.no = no;
		this.title = title;
		this.content = content;
		this.hitCount = hitCount;
		this.postDate = postDate;
		this.id = id;
	}



	//게터&세터]
	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getHitCount() {
		return hitCount;
	}

	public void setHitCount(String hitCount) {
		this.hitCount = hitCount;
	}

	public java.sql.Date getPostDate() {
		return postDate;
	}

	public void setPostDate(java.sql.Date postDate) {
		this.postDate = postDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
	
	

}
