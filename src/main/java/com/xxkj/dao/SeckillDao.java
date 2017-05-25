package com.xxkj.dao;

import com.xxkj.bean.Seckill;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/5/7.
 */
@Component
public interface SeckillDao {
    /**
     * 减库存
     * @param seckillId
     * @param killTime
     * @return 如果影响行数>1，表示更新库存的记录行数
     */
     int reduceNumber(@Param("seckillId") long seckillId, @Param("killTime") Date killTime);
    /**
     * 按照id查
     * @param seckillId
     * @return
     */
    Seckill querySecKillById(long seckillId);
    /**
     * 根据偏移量查询秒杀商品列表
     * @param off
     * @param limit
     * @return
     */
    List<Seckill> queryAll(@Param("offset") int off, @Param("limit") int limit);

}
