package com.school.estimate.action;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;

import com.opensymphony.xwork2.ActionContext;
import com.school.estimate.domain.User;


public class LoginAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private String username;
	private String password;



	public String login() throws Exception {
		
		if(username==null || username.equals("null") || username.equals("")){
			return "login";
	      }
		
		//得到subject对象
		Subject subject = SecurityUtils.getSubject();
//		Md5Hash hash = new Md5Hash(password, username, 2);
//		System.out.println(password +":" + username);
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		
		try {
			subject.login(token);
			System.out.println("登陆成功");
			//获取到当前的用户，放入到session中
			User user = (User) subject.getPrincipal();
			session.put("_CURRENT_USER", user);
			
			return SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ActionContext.getContext().put("errorInfo", "您输入的用户名或密码错误");
			
			return "login";
		}
	}
	
	
	//退出
	public String logout(){
		
		session.remove("_CURRENT_USER");		//删除session
		
		return "logout";
	}

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

}

