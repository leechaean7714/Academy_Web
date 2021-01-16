package com.my.dto;

import java.util.Date;

public class MyDto {

	//컬럼과 같은 이름인 이유?
	private int myno;
	private String myname;
	private String mytitle;
	private String mycontent;
	private Date mydate;//util의 데이트인 이유는?
	
	
	
	
	public MyDto(){}
	
	public MyDto(int myno, String myname, String mytitle, String mycontent, Date mydate) {
		//이렇게 넣어줘야 private에 있는 걸 꺼내서 쓸수 있따.
		this.myno = myno;
		this.myname = myname;
		this.mytitle=mytitle;
		this.mycontent=mycontent;
		this.mydate=mydate;
		
		
	}

	public int getMyno() {
		
		return myno;
	}

	public void setMyno(int myno) {
		
		this.myno = myno;
	}

	public String getMyname() {
		
		return myname;
	}

	public void setMyname(String myname) {
		
		this.myname = myname;
	}

	public String getMytitle() {
		
		return mytitle;
	}

	public void setMytitle(String mytitle) {
		
		this.mytitle = mytitle;
	}

	public String getMycontent() {
		
		return mycontent;
	}

	public void setMycontent(String mycontent) {
		
		this.mycontent = mycontent;
	}

	public Date getMydate() {
		
		return mydate;
	}

	public void setMydate(Date mydate) {
		
		this.mydate = mydate;
	}
	
	
	
	
	
	
}
