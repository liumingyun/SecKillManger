package com.xxkj.dao;


import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xxkj.bean.Seckill;
import com.xxkj.bean.SuccessKilled;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/5/7.
 */

@RunWith(SpringJUnit4ClassRunner.class)
// 告诉junit spring的配置文件
@ContextConfiguration({ "classpath:spring/spring-dao.xml" })
public class SecKillDaoTest {
	
	
        @Autowired
        public SeckillDao seckillDao;
        
        @Autowired
        public SeckilledDao seckilledDao;
        
        @Ignore
        public void queryById(){
            Seckill seckill=seckillDao.querySecKillById(1001);
            System.out.println("seckill:"+seckill.getName());
        }
        
        @Test
        public void queryAllTest(){
        	List<Seckill> listSeckill=seckillDao.queryAll(0,4);
            for (Seckill seckill:listSeckill){
            //System.out.println("listSeckill:"+seckill.getName());
                List<SuccessKilled> listSuccessKilled=seckilledDao.queryByIdWithSeckillList(45);
                for (SuccessKilled successKilled:listSuccessKilled) {
                	 // System.out.println("successKilled:"+seckill.getName());
					if (successKilled.getSeckillId()==seckill.getSeckillId()) {
						System.out.println(seckill.getName()+"已购买");
						
					}else{
						System.out.println(seckill.getName()+"没有购买");
					}
				}    
            }
        
        }
        @Ignore
        public void reduceNumber() throws ParseException {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
            Date date= df.parse(df.format(new Date()));
            int num=seckillDao.reduceNumber(1000,date);
            System.out.println("秒杀成功数:"+num);
        }

}
