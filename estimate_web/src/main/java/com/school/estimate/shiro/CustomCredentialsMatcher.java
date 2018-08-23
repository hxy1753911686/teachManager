package com.school.estimate.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;

public class CustomCredentialsMatcher extends SimpleCredentialsMatcher{

	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		// TODO Auto-generated method stub
		//比较用户输入的密码及数据库中的密码是否一致
		
		//通过token获得输入的用户名密码
		UsernamePasswordToken userToken = (UsernamePasswordToken) token;
		String password = new String(userToken.getPassword());
		
		/**
		 * 第一个参数 source : 要加密的字符串
		 * 第二个参数salt : 混淆的字符串
		 * 第三个参数hashIterations : hash几次
		 */
		Md5Hash hash = new Md5Hash(password, userToken.getUsername(), 2);
		System.out.println(password+":"+userToken.getUsername());
		System.out.println("输入的密码MD5后的值："+hash.toString());
		//通过info获取数据库中的密码
		Object obj = info.getCredentials();
		
		return equals(hash.toString(), obj);
	}
}
