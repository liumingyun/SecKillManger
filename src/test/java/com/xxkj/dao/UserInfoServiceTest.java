package com.xxkj.dao;

import com.xxkj.service.UserInfoService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2017/5/7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
// 告诉junit spring的配置文件
//告诉junit spring的配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml"})
public class UserInfoServiceTest {
    @Autowired
    private UserInfoService userInfoService;
    @Ignore
    public void registerUser() throws Exception {
        // 生成用户code
        String code = UUID.randomUUID().toString().replace("-", "");
        System.out.print("code=" + code);
        userInfoService.register("lx","123456","1528747322@qq.com");
    }


  /*  @Test
    public void updateUserInfoStatus() throws Exception {
       int num= userInfoDao.updateUserInfoStatus("lx","1eb9c5b63ffa44499877b97058b64aa8");
       System.out.println("修改状态:"+num);
    }*/


}