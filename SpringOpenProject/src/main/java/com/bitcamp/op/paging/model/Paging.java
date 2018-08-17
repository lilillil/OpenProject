package com.bitcamp.op.paging.model;

public class Paging {

	private int totalPageCnt;
	private int startPage;
	private int endPage;
	
	
	public Paging() { }
	
	//현재 페이지, 총 레코드 수, 한 페이지당 보여줄 레코드 수
	public Paging(int pagenum, int totalCount, int MESSAGE_COUNT_PER_PAGE) {
		
		//총 페이지 수
		int totalPageCnt = 0;
		int startPage = 0;
		int endPage = 0;
		
		if (totalCount == 0) {
			totalPageCnt = 0;
		} else {
			
			totalPageCnt = totalCount / MESSAGE_COUNT_PER_PAGE;
			if (totalCount % MESSAGE_COUNT_PER_PAGE > 0) {
				totalPageCnt++;
			}

		}
		
		startPage = (pagenum == 1 ) ? 1 : ((pagenum-1)/5)*5+1;
		endPage =  ((startPage + 5 -1)< totalPageCnt) ? startPage + 5 -1 : totalPageCnt ; 
		
		this.totalPageCnt = totalPageCnt;
		this.startPage = startPage;
		this.endPage = endPage;
	}
	
	

	public int getTotalPageCnt() {
		return totalPageCnt;
	}
	public void setTotalPageCnt(int totalPageCnt) {
		this.totalPageCnt = totalPageCnt;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	
	
}
