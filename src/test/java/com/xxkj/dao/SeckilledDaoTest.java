package com.xxkj.dao;

import com.xxkj.bean.SuccessKilled;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Administrator on 2017/5/7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
// 告诉junit spring的配置文件
@ContextConfiguration({ "classpath:spring/spring-dao.xml" })
public class SeckilledDaoTest {

    @Autowired
    private SeckilledDao seckilledDao;
    @Ignore
    public void insertSuccessKilled(){
        //seckilledDao.insertSuccessKilled(1004,15611111111L,);
    }
    @Ignore
    public void queryByIdWithSeckill(){
       SuccessKilled seckilled=seckilledDao.queryByIdWithSeckill(1004,15611111111L);
       System.out.println("seckilled:"+seckilled.getUserPhone());
    }

}
