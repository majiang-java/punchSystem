/**
 * 
 */
package com.util;

import java.util.Arrays;

/**
 * @author majiang
 *分页信息处理,定义名称，类型。
 */
public class PageModel {
	public static final int PAGE_SIZE = 5;
	int PageSize=1;//--每页显示条数
	int CurrentPage;//--当前页数
	int RecordNum;//--总条数
	int totlePage = RecordNum%PageSize==0?(RecordNum/PageSize):(RecordNum/PageSize)+1;
	/**
	 * 每页显示条数
	 * @return
	 */
	public int getTotlePage(){
		totlePage = RecordNum%PageSize==0?(RecordNum/PageSize):(RecordNum/PageSize)+1;
		return totlePage;
	}
	public int getPageSize() {
		return PageSize;
	}
	public void setPageSize(int pageSize) {
		if(pageSize <= 0){
			PageSize = 1;
		}else{
			PageSize = pageSize;
		}
	}
	/**
	 * 当前页数
	 * @return
	 */
	public int getCurrentPage() {
		
		return CurrentPage;
	}
	public void setCurrentPage(int currentPage) {
		int totlePage = RecordNum%PageSize==0?(RecordNum/PageSize):(RecordNum/PageSize)+1;
		if(currentPage > totlePage){
			CurrentPage = totlePage;
		}else if(currentPage <= 0){
			CurrentPage = 1;
		}else{
			CurrentPage = currentPage;
		}
	}
	/**
	 * 总条数
	 * @return
	 */
	public int getRecordNum() {
		return RecordNum;
	}
	public void setRecordNum(int recordNum) {
		RecordNum = recordNum;
	}
	
	public int getStartNum(){
		//int re = PageSize*(CurrentPage-1)+1;
		int re = (PageSize*CurrentPage-PageSize);
		if(re>=RecordNum){
			return 1;
		}else{
			return re;
		}
	}
	
	
	public int getEndNum(){
		int end = PageSize*CurrentPage;
		if(end>=RecordNum){
			return RecordNum;
		}else{
			return end;
		}
	}
	
	public static void main(String[] args){
		int pageSize = 10,recordNum = 99,currentPage = -1;
		PageModel p = new PageModel();
		String[] strs = new String[11];
		
		p.setPageSize(pageSize);  // 设置每页显示条数
		p.setRecordNum(strs.length);	//设置总条数
		p.setCurrentPage(currentPage); //当前页数
		for (int i = 0; i < strs.length; i++) {
			strs[i] = i+"";
		}
		System.out.println(p.getStartNum());
		System.out.println( p.getEndNum());
		System.out.println("截取前："+Arrays.asList(strs));
		String[] temp = Arrays.copyOfRange(strs, p.getStartNum(), p.getEndNum());
		System.out.println("截取后："+Arrays.asList(temp));
	}
	
}
