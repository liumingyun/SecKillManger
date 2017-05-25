package com.xxkj.dao;

import com.xxkj.bean.UserInfo;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Administrator on 2017/5/7.
 */

public interface UserInfoDao {
	/**
	 * 
	 * @param username
	 * @param password
	 * @param email
	 * @param code
	 * @return
	 */
     int registerUser(@Param("username") String username,@Param("password") String password,@Param("email") String email,@Param("code") String code);
     
     
     /**
      * 
      * @param code
      * @return
      */
     UserInfo getUserinfoByCode(String code);
     /**
      * 
      * @param username
      * @param code
      * @return
      */
     int updateUserInfoStatus(@Param("username") String username,@Param("code") String code);
     
     /**
      *	email
      * @param email
      * @return
      */
     UserInfo getUserinfoByEmail(String email);
     /**
      * 登陆
      * @param email
      * @param password
      * @return
      */
     UserInfo getUserInfoByEmailAndPassWord(@Param("email") String email,@Param("password") String password);
}
