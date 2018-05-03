package com.cafe24.util;

import java.util.HashMap;
import java.util.Map;

public class PagingBean {

	private long totalCount;
	private long nowPage = 0;
	private int countList = 3;
	private int countPage = 2;
	private long page;
	private String keyword;
	private String option;
	private Map<String, String> opt = new HashMap<String,String>();
	
	public Map<String, String> getOpt() {
		return opt;
	}


	public void setOpt(Map<String, String> opt) {
		this.opt = opt;
	}


	public String getOption() {
		return option;
	}


	public void setOption(String option) {
		this.option = option;
	}


	public long getPage() {
		return page;
	}


	public void setPage(long page) {
		this.page = page;
	}


	public String getKeyword() {
		return keyword;
	}


	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}


	public PagingBean() {
		super();
	}


	public PagingBean(long totalCount, long nowPage, int countList, int countPage) {
		super();
		this.totalCount = totalCount;
		this.nowPage = nowPage;
		this.countList = countList;
		this.countPage = countPage;
	}

	public PagingBean(Long totalCount, Long nowPage, int countList, int countPage, String keyword, String option) {
		super();
		this.totalCount = totalCount;
		this.nowPage = nowPage;
		this.countList = countList;
		this.countPage = countPage;
		this.keyword = keyword;
		this.option = option;
	}


	public PagingBean(long totalCount, long nowPage, int countList, int countPage,
			Map<String, String> opt) {
		super();
		this.totalCount = totalCount;
		this.nowPage = nowPage;
		this.countList = countList;
		this.countPage = countPage;
		this.opt = opt;
	}


	public long getTotalPageCount() {
		long totalPage = totalCount / countList;
		if( totalCount%countList > 0) {
			totalPage++;
		}
		return totalPage;
	}

	public long getStartPage() {
		//현재 페이지의  StartPage 
		// nowPage-1 은  nowPage가 끝번호 일경우 ex)10 다음 페이지 첫번호인 11로 나오게됨. 1이아니라. 
		return ((nowPage-1)/countPage)*countPage+1;
	}
	
	public long getEndPage() {
		long endPage = getStartPage()+countPage-1;
		if(endPage>getTotalPageCount())
			endPage = getTotalPageCount();
		return endPage;
	}
	
	public long getFullEndPage() {
		long endPage = getStartPage()+countPage-1;
		return endPage;
	}
	public long getStartCount() {
		long temp = (nowPage-1);
		if(temp == -1)
			temp = 0;
		return temp*countList;
	}
	
	public long getTotalPageGroup() {
		
		long totalPageGroup = 0L;
		totalPageGroup = getTotalPageCount()/countPage;
		if(getTotalPageCount()%countPage >0)
			totalPageGroup++;
		return totalPageGroup;	
	}
	public long getNowPageGroup() {
		long nowPageGroup = nowPage/countPage;
		if(nowPage%countPage > 0)
			nowPageGroup++;
		return nowPageGroup;
	}
	public boolean isPreviousPageGroup() {
		if(getNowPageGroup() > 1)
			return true;
		return false;
	}
	public boolean isNextPageGroup() {
		if( getNowPageGroup() < getTotalPageGroup())
			return true;
		return false;
	}
	/*public boolean getPrevious() {
		if( nowPage<=countPage)
			return false;
		return true;
	}
	public boolean getNext() {
		if((-countPage) < getEndPage())
			return false;
		return true;
	}*/
	/////////////
	
	public long getTotalCount() {
		return totalCount;
	}


	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}


	public long getNowPage() {
		return nowPage;
	}


	public void setNowPage(long nowPage) {
		this.nowPage = nowPage;
	}


	public int getCountList() {
		return countList;
	}


	public void setCountList(int countList) {
		this.countList = countList;
	}


	public int getCountPage() {
		return countPage;
	}


	public void setCountPage(int countPage) {
		this.countPage = countPage;
	}


	@Override
	public String toString() {
		return "PagingBean [totalCount=" + totalCount + ", nowPage=" + nowPage + ", countList=" + countList
				+ ", countPage=" + countPage + ", page=" + page + ", keyword=" + keyword + ", option=" + option
				+ ", opt=" + opt + "]";
	}


	

	
	

	
	
}
