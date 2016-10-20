package com.web.action;

import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport{
	private static final long serialVersionUID = 5410502590877050769L;
	private String username;
	private String password;
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String login() throws Exception{
		if("admin".equals(username)&&"admin".equals(password)){
			System.out.println(23423423);
			return "login";
		}else{
			return "normal";
		}
		
	}
	
	

}
