package com.gta.oec.cms.common.pagination;

import java.util.ArrayList;
import java.util.List;

/** 
 * @description 分页model  
 * @author 黄建华(Bill Huang)  
 * @createDate 2014-03-18 19:58:34 
 * @file PageModel.java 
 * @package com.gta.oec.cms.common.pagination 
 * @email jianshaosky@126.com 
 * @version 1.0 
 */
public class PageModel<E> {
	
	/*
	 * pagination result set
	 * data set
	 * */
	private List<E> result;
	
	/*
	 * pagination page size list
	 * */
	private List<Integer> pageList = new ArrayList<Integer>();
	
	/*
	 * page size
	 * */
	private int pageSize = 10;
	
	/*
	 * page size
	 * 当前显示到的ID, 在mysql limit 中就是第一个参数.
	 * */
	private int currentPageStartIndex = 0;
	
	/*
	 * total size
	 * */
	private int totalSize = 0;
	
	/*
	 * total page
	 * */
	private int totalPage = 0;
	
	/*
	 * current page number 
	 * when the client
	 * is init, so pageIndex must be 0
	 * when user click next button can
	 * change the page index..
	 * */
	private int pageIndex = 1;
	
	public PageModel(){
		this.init();
	}

	public PageModel(int totalSize,List<E> result){
		this.init();
		this.totalSize = totalSize;
		this.result = result;
	}
	
	private void init(){
		pageList.add(10);
		pageList.add(20);
		pageList.add(30);
		pageList.add(50);
	}
	
	/*
	 * get the start page index
	 * */
	public int getStartPageIndex(){
		return 1;
	}
	
	/*
	 * get previous page index
	 * */
	public int getPrevPageIndex(){
		if(this.pageIndex<=1){
			return 1;
		}
		return pageIndex-1;
	}
	
	/*
	 * get the next page index
	 * */
	public int getNextPageIndex(){
		if(this.pageIndex>=this.totalPage){
			return this.totalPage;
		}
		return pageIndex+1;
	}
	
	/*
	 * get the end page index
	 * */
	public int getEndPageIndex(){
		return this.totalPage;
	}
	
	public List<E> getResult() {
		return result;
	}

	public void setResult(List<E> result) {
		this.result = result;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
		this.totalPage = (int)Math.ceil(Double.valueOf(this.totalSize)/Double.valueOf(this.pageSize));
		if(this.pageIndex>=this.totalPage){
			this.pageIndex = this.totalPage;
		}
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getCurrentPageStartIndex() {
		if(pageIndex<=1){
			return 0 * pageSize;
		}else{			
			currentPageStartIndex = (pageIndex-1) * pageSize;
			return currentPageStartIndex;
		}
	}

	public void setCurrentPageStartIndex(int currentPageStartIndex) {
		this.currentPageStartIndex = currentPageStartIndex;
	}
	
	@Override
	public String toString() {
		StringBuffer str = new StringBuffer(getClass().getName()+"@");
		str.append("pageSize="+pageSize+";");
		str.append("currentPageStartIndex="+currentPageStartIndex+";");
		str.append("totalSize="+totalSize+";");
		str.append("totalPage="+totalPage+";");
		str.append("pageIndex="+pageIndex+";");
		if(pageList!=null){			
			str.append("pageList.szie="+pageList.size()+";");
		}
		if(result!=null){			
			str.append("result.szie="+result.size()+".");
		}
		return str.toString();
	}
	
}
