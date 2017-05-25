package com.xxkj.service;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import com.xxkj.bean.UserInfo;
import com.xxkj.dao.UserInfoDao;
import com.xxkj.enums.UserInfoEnum;
import com.xxkj.exceptions.UserInfoExecption;
import com.xxkj.utils.MessageUtils;

import org.apache.ibatis.javassist.compiler.ast.NewExpr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * Created by Administrator on 2017/5/7.
 */
@Service
public class UserInfoService {

	@Autowired
	public UserInfoDao userInfoDao;
	/**
	 * 注册
	 * @param username
	 * @param password
	 * @param email
	 */
	public UserInfoExecption register(String username, String password, String email) {
		// 生成用户code
		String code = UUID.randomUUID().toString().replace("-", "");
		System.out.print("code=" + code);
		UserInfo userInfo=userInfoDao.getUserinfoByEmail(email);
		if (userInfo!=null){
			//注册过
			if(userInfo.getStatus()==0) {
				MessageUtils.sendMail(email, userInfo.getCode(), username);
				//未激活
				return new UserInfoExecption(UserInfoEnum.NOTJIHUO);
			}else{
				return new UserInfoExecption(UserInfoEnum.REGSUCCESSED);
			}
		}
		// 添加用户
		int regisitNum = userInfoDao.registerUser(username, password, email,
				code);
		if (regisitNum<=0) {
			// 插入成功
			// 向用户发送激活邮
			return new UserInfoExecption(UserInfoEnum.REGERROR);
		} else {
			if (MessageUtils.sendMail(email, code, username)) {
				return new UserInfoExecption(UserInfoEnum.REGSUCCESS);
			} else {
				return new UserInfoExecption(UserInfoEnum.NOTJIHUO);
			}
		}
		
	}

	/**
	 * 激活用户
	 * 
	 * @param code
	 *            用户激活码
	 * @return 是否激活成功
	 */
	public UserInfoExecption Active(String username, String code) {
		UserInfo userInfo = userInfoDao.getUserinfoByCode(code);
		// 如果存在用户，将此用户状态设为可用
		if (userInfo.getUsername().equals(username)) {
			 userInfoDao.updateUserInfoStatus(userInfo.getUsername(),userInfo.getCode());
			 return new UserInfoExecption(UserInfoEnum.JIHUOSUCCESS);
		}else{
			 return new UserInfoExecption(UserInfoEnum.JIHUOERROR);
		}
	}
	
	
	public UserInfo login(String email,String password){
		return userInfoDao.getUserInfoByEmailAndPassWord(email, password);
	}
	
	

}
