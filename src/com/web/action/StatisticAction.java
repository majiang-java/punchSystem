package com.web.action;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.util.PageModel;
import com.web.service.PersonServices;
import com.web.service.StatisticServices;

public class StatisticAction extends ActionSupport{
	
	private String cardNum;
	private String name;
	private Date highDate;
	private Date lowDate;
	private List logList;
	

	private String count;

	public List getLogList() {
		return logList;
	}



	public void setLogList(List logList) {
		this.logList = logList;
	}



	public String getStatisticList() throws SQLException{
	
		
		int currentPage = 1;
		String currentPageStr = ServletActionContext.getRequest().getParameter("currentPage");
		if(currentPageStr != null && !"".equals(currentPageStr)){
			currentPage = Integer.parseInt(currentPageStr);
		}
		List tempList = StatisticServices.getInstance().getStatisticList(cardNum,name,highDate,lowDate);
		if(tempList.size() == 0){
			logList = tempList;
			ServletActionContext.getRequest().setAttribute("totlePage", 0);
			return "success";
		}
		PageModel p = new PageModel();
		p.setPageSize(PageModel.PAGE_SIZE);  // 设置每页显示条数
		p.setRecordNum(tempList.size());	//设置总条数
		p.setCurrentPage(currentPage); //当前页数
		Object[] temps = Arrays.copyOfRange(tempList.toArray(), p.getStartNum(), p.getEndNum());
		logList = Arrays.asList(temps);
		ServletActionContext.getRequest().setAttribute("totlePage", p.getTotlePage());
		count = StatisticServices.getInstance().getCount(cardNum,name,highDate,lowDate);
		return "success";
	}







	public String getCardNum() {
		return cardNum;
	}



	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public Date getHighDate() {
		return highDate;
	}



	public void setHighDate(Date highDate) {
		this.highDate = highDate;
	}



	public Date getLowDate() {
		return lowDate;
	}



	public void setLowDate(Date lowDate) {
		this.lowDate = lowDate;
	}



	public String getCount() {
		return count;
	}



	public void setCount(String count) {
		this.count = count;
	}
	

}
