package com.xxkj.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import com.xxkj.bean.Seckill;
import com.xxkj.bean.SuccessKilled;
import com.xxkj.dao.SeckillDao;
import com.xxkj.dao.SeckilledDao;
import com.xxkj.enums.SeckillStatEnum;
import com.xxkj.eto.Exposer;
import com.xxkj.eto.SeckillDto;
import com.xxkj.exceptions.RepeatKillException;
import com.xxkj.exceptions.SeckillCloseException;
import com.xxkj.exceptions.SeckillException;

@Service
public class SecKillService {
	
	  //日志对象
    
	@Autowired
	public SeckillDao seckillDao;
	
    @Autowired
    public SeckilledDao seckilledDao;
    
    
	public List<Seckill> getSeckillList() {
        return seckillDao.queryAll(0,4);
    }
	
	 public Seckill getById(long seckillId) {
	        return seckillDao.querySecKillById(seckillId);
	 }

	 
	 public List<SuccessKilled> getSuccessKilleds(int uid) {
	        return seckilledDao.queryByIdWithSeckillList(uid);
	 }

	 
	 
	 
	 
	 
	 
	 
	 
	 //加入一个混淆字符串(秒杀接口)的salt，为了我避免用户猜出我们的md5值，值任意给，越复杂越好
	    private final String salt="shsdssljdd'l.";
	 
	 public Exposer exportSeckillUrl(long seckillId) {

	    /*	Seckill seckill=redisDao.getSeckill(seckillId);
	    	if (seckill==null) {
	    		 seckill=seckillDao.queryById(seckillId);
	    		 if (seckill==null) //说明查不到这个秒杀产品的记录
	    	        {
	    	            return new Exposer(false,seckillId);
	    	     }else{
	    	    	 redisDao.putSeckill(seckill);
	    	     }
			}*/
	    	
	    	Seckill seckill= seckillDao.querySecKillById(seckillId);
	    	if (seckill==null) {
	    		  return new Exposer(false,seckillId);
	    	}
	    	
	        //若是秒杀未开启
	        Date startTime=seckill.getStartTime();
	        Date endTime=seckill.getEndTime();
	        //系统当前时间
	        Date nowTime=new Date();
	        if (startTime.getTime()>nowTime.getTime() || endTime.getTime()<nowTime.getTime())
	        {
	            return new Exposer(false,seckillId,nowTime.getTime(),startTime.getTime(),endTime.getTime());
	        }

	        //秒杀开启，返回秒杀商品的id、用给接口加密的md5
	        String md5=getMD5(seckillId);
	        return new Exposer(true,md5,seckillId);
	    }

	    private String getMD5(long seckillId)
	    {
	        String base=seckillId+"/"+salt;
	        String md5= DigestUtils.md5DigestAsHex(base.getBytes());
	        return md5;
	    }
	 
	 
	 
	 
	    /**
	     * 使用注解控制事务方法的优点:
	     * 1.开发团队达成一致约定，明确标注事务方法的编程风格
	     * 2.保证事务方法的执行时间尽可能短，不要穿插其他网络操作RPC/HTTP请求或者剥离到事务方法外部
	     * 3.不是所有的方法都需要事务，如只有一条修改操作、只读操作不要事务控制
	     */
	    @Transactional
	    public SeckillDto executeSeckill(long uid,long seckillId, long userPhone, String md5)
	            throws SeckillException, RepeatKillException, SeckillCloseException {
	        if (md5==null||!md5.equals(getMD5(seckillId)))
	        {
	            throw new SeckillException("seckill data rewrite");//秒杀数据被重写了
	        }
	        //执行秒杀逻辑:减库存+增加购买明细
	        Date nowTime=new Date();
	        try{
	            //否则更新了库存，秒杀成功,增加明细
	            int insertCount=seckilledDao.insertSuccessKilled(seckillId,userPhone,uid);
	            System.out.println("掺入"+insertCount);
	            //看是否该明细被重复插入，即用户是否重复秒杀
	            if (insertCount<=0)
	            {
	                throw new RepeatKillException("seckill repeated");
	            }else {
	                //减库存,热点商品竞争
	                int updateCount=seckillDao.reduceNumber(seckillId,nowTime);
	                if (updateCount<=0)
	                {
	                    //没有更新库存记录，说明秒杀结束 rollback
	                    throw new SeckillCloseException("seckill is closed");
	                }else {
	                    //秒杀成功,得到成功插入的明细记录,并返回成功秒杀的信息 commit
	                    SuccessKilled successKilled=seckilledDao.queryByIdWithSeckill(seckillId,userPhone);
	                    System.out.println("successKilled====="+successKilled);
	                    return new SeckillDto(seckillId, SeckillStatEnum.SUCCESS,successKilled);
	                }
	            }
	        }catch (SeckillCloseException e1)
	        {
	            throw e1;
	        }catch (RepeatKillException e2)
	        {
	            throw e2;
	        }catch (Exception e)
	        {
	            //所以编译期异常转化为运行期异常
	            throw new SeckillException("seckill inner error :"+e.getMessage());
	        }

	    }
	 
	 
	 
	 
	 
	 
	 
}
