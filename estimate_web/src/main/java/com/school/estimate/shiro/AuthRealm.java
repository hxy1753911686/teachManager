package com.school.estimate.shiro;

import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.school.estimate.domain.Module;
import com.school.estimate.domain.Role;
import com.school.estimate.domain.Teacher;
import com.school.estimate.domain.User;
import com.school.estimate.service.TeacherService;
import com.school.estimate.service.UserService;


//继承了授权的
public class AuthRealm extends AuthorizingRealm{

	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	private TeacherService teacherService;
	

	public void setTeacherService(TeacherService teacherService) {
		this.teacherService = teacherService;
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection pc) {
		// TODO Auto-generated method stub
		System.out.println("调用了授权的方法");
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		//从数据库获取当前登陆用户的模块名称
		//PrincipalCollection中已经有传进去的用户了
		User user = (User) pc.getPrimaryPrincipal();
		
		Set<Role> roles = user.getRoles();
			
		for (Role role : roles) {
			Set<Module> modules = role.getModules();
			for (Module module : modules) {
				//从module中取出cpermission放入info中授权
				info.addStringPermission(module.getName());
			}
		}
		
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {
		// TODO Auto-generated method stub
		System.out.println("调用了认证的方法");
		UsernamePasswordToken token = (UsernamePasswordToken) arg0;
		String username = token.getUsername();
		
		//根据用户名进行数据库查询
		List<User> userList = userService.find("from User where name = '" + username +"'" , User.class, null);
		
		if(userList != null && userList.size() > 0){
			//返回模板认证信息
			User user = userList.get(0);
			/**
			 * 第一个参数principals : 主要的,即当前用户信息
			 * 第二个参数credentials : 要认证的对象,一般来说扔密码
			 * 第三个参数realmName : 传入一个字符串来判断是哪个realm
			 */
			return new SimpleAuthenticationInfo(user,user.getPassword(),this.getName());
		}
		return null;
	}
}
