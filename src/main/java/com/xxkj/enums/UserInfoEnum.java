package com.xxkj.enums;

public enum UserInfoEnum {
	
	UNKNOWNERROR(0, "未知错误"), 
	REGSUCCESS(1, "注册成功"), 
	JIHUOSUCCESS(2, "激活成功"), 
	NOTJIHUO(3,"注册成功未激活"),
	REGSUCCESSED(4,"已有账号"),
	REGERROR(-1, "注册失败"), 
	JIHUOERROR(-2, "激活失败"),
	;
	private Integer code;
	private String msg;

	UserInfoEnum(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Integer getCode() {
		return code;
	}

    public static UserInfoEnum stateOf(int index)
    {
        for (UserInfoEnum state : values())
        {
            if (state.getCode()==index)
            {
                return state;
            }
        }
        return null;
    }
}
