package com.xxkj.exceptions;


import com.xxkj.bean.UserInfo;
import com.xxkj.enums.UserInfoEnum;

public class UserInfoExecption {

    //秒杀执行结果的状态
    private int state;

    //状态的明文标识
    private String stateInfo;

 

  //秒杀成功返回所有信息
    public UserInfoExecption(UserInfoEnum statEnum) {
       
        this.state = statEnum.getCode();
        this.stateInfo = statEnum.getMsg();
     
    }

 
    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }



	
    
}
