package com.page.dto;

public class PageDto {

	private int totalcount;//전체 게시물 개수
	private int pagenum;   //현재 페이지 번호
	private int contentnum;//한페이지에 몇개 표시할지
	private int startpage; //현재 페이지블록의 시작 페이지
	private int endpage;   //현재 페이지 블록의 마지막 페이지
	private boolean prev;  //이전 페이지로 가는 화살표
	private boolean next;  //다음 페이지로 가는 화살표
	private int currentblock;//현재 페이지 블록
	private int lastblock;   //마지막 페이지 블록
	
}
