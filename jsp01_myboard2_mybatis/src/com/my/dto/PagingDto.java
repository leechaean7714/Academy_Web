package com.my.dto;

public class PagingDto {

	
	private int rows;
	private int page;
	private int startpage;
	private int endpage;
	private int pagescale;
	private int pagegroup;
	private int totalpage;
	
	public PagingDto() {}

	public PagingDto(int rows, int page, int startpage, int endpage, int pagescale, int pagegroup, int totalpage) {
		super();
		this.rows = rows;
		this.page = page;
		this.startpage = startpage;
		this.endpage = endpage;
		this.pagescale = pagescale;
		this.pagegroup = pagegroup;
		this.totalpage = totalpage;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getStartpage() {
		return startpage;
	}

	public void setStartpage(int startpage) {
		this.startpage = startpage;
	}

	public int getEndpage() {
		return endpage;
	}

	public void setEndpage(int endpage) {
		this.endpage = endpage;
	}

	public int getPagescale() {
		return pagescale;
	}

	public void setPagescale(int pagescale) {
		this.pagescale = pagescale;
	}

	public int getPagegroup() {
		return pagegroup;
	}

	public void setPagegroup(int pagegroup) {
		this.pagegroup = pagegroup;
	}

	public int getTotalpage() {
		return totalpage;
	}

	public void setTotalpage(int totalpage) {
		this.totalpage = totalpage;
	}
	
	
	
	
}
