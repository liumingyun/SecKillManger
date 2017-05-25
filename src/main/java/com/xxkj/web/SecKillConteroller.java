package com.xxkj.web;

import java.util.Date;
import java.util.List;

import com.xxkj.bean.Seckill;
import com.xxkj.bean.SuccessKilled;
import com.xxkj.bean.UserInfo;
import com.xxkj.enums.SeckillStatEnum;
import com.xxkj.eto.Exposer;
import com.xxkj.eto.SeckillDto;
import com.xxkj.eto.SeckillResult;
import com.xxkj.eto.UserInfoResult;
import com.xxkj.exceptions.RepeatKillException;
import com.xxkj.exceptions.SeckillCloseException;
import com.xxkj.exceptions.UserInfoExecption;
import com.xxkj.service.SecKillService;
import com.xxkj.service.UserInfoService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2017/5/7.
 */
@Controller
@RequestMapping("/seckill")//url:模块/资源/{}/细分
public class SecKillConteroller {
    @Autowired
    private SecKillService secKillService;
    
   
    @RequestMapping(value = "/{uid}/list",method = RequestMethod.GET)
    public String list(@PathVariable("uid") int uid,Model model)
    {
        
 
    	
        //获取列表页
        List<Seckill> list=secKillService.getSeckillList();
        List<SuccessKilled> successList=secKillService.getSuccessKilleds(uid);
        System.out.println("购买过数量:"+successList.size());
        for (Seckill seckill : list) {
			System.out.println(seckill.getName()+"\t"+seckill.getCreateTime()+"\t"+seckill.getStartTime());
		}
        model.addAttribute("uid",uid);
        model.addAttribute("list",list);
        model.addAttribute("successList",successList);
        return "list";
        
    }
    
    
    
    
    
    
    @RequestMapping(value = "/{seckillId}/detail",method = RequestMethod.GET)
    public String detail(@PathVariable("seckillId") Long seckillId, Model model)
    {
        if (seckillId == null)
        {
            return "redirect:/seckill/list";
        }
        Seckill seckill=secKillService.getById(seckillId);
        System.out.print("mingzi:"+seckill.getName());	        
        if (seckill==null)
        {
            return "forward:/seckill/list";
        }

        model.addAttribute("seckill",seckill);
        
        return "detail";
    }
    
    
    
    
    
    
    
    //ajax ,json暴露秒杀接口的方法
    @RequestMapping(value = "/{seckillId}/exposer",
                    method = RequestMethod.POST,
                    produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<Exposer> exposer(@PathVariable("seckillId") Long seckillId)
    {
        SeckillResult<Exposer> result;
        try{
            Exposer exposer=secKillService.exportSeckillUrl(seckillId);
            result=new SeckillResult<Exposer>(true,exposer);
        }catch (Exception e)
        {
            e.printStackTrace();
            result=new SeckillResult<Exposer>(false,e.getMessage());
        }

        return result;
    }
    
    
    
    
    
    
    
    
    @RequestMapping(value = "/{uid}/{seckillId}/{md5}/execution",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<SeckillDto> execute(
    											@CookieValue(value = "uid",required = false) int uid,
    											@PathVariable("seckillId") Long seckillId,
                                                   @PathVariable("md5") String md5,
                                                   @CookieValue(value = "userPhone",required = false) Long phone)
    {
    	 
    	 
    	 System.out.println("未seckillId册"+seckillId);
        if (phone==null)
        {
        	System.out.println("未注册");
            return new SeckillResult<SeckillDto>(false,"未注册");
        }
        SeckillResult<SeckillDto> result;

        try {
        	SeckillDto execution = secKillService.executeSeckill(uid,seckillId, phone, md5);
            return new SeckillResult<SeckillDto>(true, execution);
        }catch (RepeatKillException e1)
        {
        	SeckillDto execution=new SeckillDto(seckillId, SeckillStatEnum.REPEAT_KILL);
            return new SeckillResult<SeckillDto>(true,execution);
        }catch (SeckillCloseException e2)
        {
        	SeckillDto execution=new SeckillDto(seckillId, SeckillStatEnum.END);
            return new SeckillResult<SeckillDto>(true,execution);
        }
        catch (Exception e)
        {
        	SeckillDto execution=new SeckillDto(seckillId, SeckillStatEnum.INNER_ERROR);
            return new SeckillResult<SeckillDto>(true,execution);
        }

    }
    
    
    
    
    
    
    
    
    
    
    
    
    
  //获取系统时间
    @RequestMapping(value = "/time/now",method = RequestMethod.GET)
    @ResponseBody
    public SeckillResult<Long> time()
    {
        Date now=new Date();
        return new SeckillResult<Long>(true,now.getTime());
    }
    
    
    
    
    
    
}
