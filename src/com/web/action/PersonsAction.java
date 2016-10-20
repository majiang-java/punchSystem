package com.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.util.PageModel;
import com.web.service.PersonServices;

public class PersonsAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5173416336849842859L;
	
	private List personList;
	private String CardNum;
	private String Name;
	private String CardLog;
	
	public String getCardLog() {
		return CardLog;
	}
	public void setCardLog(String cardLog) {
		CardLog = cardLog;
	}


	private String flag;

	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public List getPersonList() {
		return personList;
	}
	public void setPersonList(List personList) {
		this.personList = personList;
	}
	public String getCardNum() {
		return CardNum;
	}
	public void setCardNum(String cardNum) {
		CardNum = cardNum;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}

	public String getAllList() throws SQLException{
		int currentPage = 1;
		String currentPageStr = ServletActionContext.getRequest().getParameter("currentPage");
		if(currentPageStr != null && !"".equals(currentPageStr)){
			currentPage = Integer.parseInt(currentPageStr);
		}
		List tempList = PersonServices.getInstance().getPersonList();
		if(tempList.size() == 0){
			personList = tempList;
			ServletActionContext.getRequest().setAttribute("totlePage", 0);
			return "list";
		}
		PageModel p = new PageModel();
		p.setPageSize(PageModel.PAGE_SIZE);  // 设置每页显示条数
		p.setRecordNum(tempList.size());	//设置总条数
		p.setCurrentPage(currentPage); //当前页数
		Object[] temps = Arrays.copyOfRange(tempList.toArray(), p.getStartNum(), p.getEndNum());
		personList = Arrays.asList(temps);
		ServletActionContext.getRequest().setAttribute("totlePage", p.getTotlePage());
		return "list";
	}
	
	public String add(){
		return "add";
	}
	
	

	public String edit() throws SQLException{
		String carNum = ServletActionContext.getRequest().getParameter("flag");
		Map<String, String> map = PersonServices.getInstance().getPersonMsg(carNum);
		CardNum = map.get("CardNum");
		Name = map.get("Name");
		CardLog = map.get("CardLog");
		return "edit";
	}
	
	public String update() throws SQLException{
		PersonServices.getInstance().update(CardNum, Name,CardLog);
		return "success";
	}
	public String save() throws SQLException{
		
		int returnFlag= PersonServices.getInstance().insertPerson(CardNum, Name,CardLog);
		flag = String.valueOf(returnFlag);
		if(returnFlag>0){
			return "success";
		}else{
			return "add";
		}
	}
	public String delete() throws Exception{
		String carNum = ServletActionContext.getRequest().getParameter("flags");
		int returnFlag = PersonServices.getInstance().delete(carNum);
		flag = String.valueOf(returnFlag);
		return "success";
	}
	 
	public String logCard() throws Exception{
		
		
		Map<String, String> map = PersonServices.getInstance().getPersonMsg(CardNum);
		if(map.isEmpty()){
			ServletActionContext.getRequest().setAttribute("msg", "-50");
			return "logCard";
		}
		CardLog = PersonServices.getInstance().getCardLog(CardNum);
		int IntCardlog = Integer.parseInt(CardLog);
		if(IntCardlog == 0){
			ServletActionContext.getRequest().setAttribute("msg", "-100");
		}else if(IntCardlog > 0){
			IntCardlog--;
			PersonServices.getInstance().logCard(CardNum,IntCardlog);
			ServletActionContext.getRequest().setAttribute("msg", IntCardlog);
		}
		//PersonServices.getInstance().insertLog(CardNum, CardLog);
		return "logCard";
	}
	
	public String chectUser() throws SQLException, IOException{
		
		String carNum = ServletActionContext.getRequest().getParameter("flag");
		Map<String, String> map = PersonServices.getInstance().getPersonMsg(carNum);
		PrintWriter writer = ServletActionContext.getResponse().getWriter();
		if(map.isEmpty()){
			writer.write("1");
		}else{
			writer.write("2");
		}
		writer.flush();
		writer.close();
		return null;
	}
	
	public String getTitle() throws SQLException, IOException {
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		String title = ServletActionContext.getRequest().getParameter("title");
		String titleMsg = PersonServices.getInstance().getTitle(title);
		PrintWriter writer = ServletActionContext.getResponse().getWriter();
		writer.write(titleMsg);
		writer.flush();
		writer.close();
		return null;
	}
	
	public String setTitle() throws SQLException, IOException{
		String titleMsg = ServletActionContext.getRequest().getParameter("titleMsg");
		PrintWriter writer = ServletActionContext.getResponse().getWriter();
		int flag = PersonServices.getInstance().setTitle(titleMsg);
		if(flag > 0){
			writer.write("1");
		}else{
			writer.write("2");
		}
		writer.flush();
		writer.close();
		return null;
	}

}
