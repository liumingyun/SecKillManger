package com.xxkj.dao;

import java.util.List;

import com.xxkj.bean.SuccessKilled;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Administrator on 2017/5/7.
 */
public interface SeckilledDao {
    /**
     * 插入购买明细,可过滤重复
     * @param seckillId
     * @param userPhone
     * @return插入的行数
     */
    int insertSuccessKilled(@Param("seckillId") long seckillId,@Param("userPhone") long userPhone,@Param("uid") long uid);

    /**
     * 根据秒杀商品的id查询明细SuccessKilled对象(该对象携带了Seckill秒杀产品对象)
     * @param seckillId
     * @return
     */
    SuccessKilled queryByIdWithSeckill(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);
    
    
    
    List<SuccessKilled>  queryByIdWithSeckillList(@Param("uid") int uid);

}
