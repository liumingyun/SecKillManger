package com.xxkj.bean;

import org.springframework.context.annotation.Bean;

/**
 * Created by Administrator on 2017/5/7.
 */
public class UserInfo {
    private  int uid;
    private  String username;
    private  String password;
    private  int status;
    private  String code;
    private  String email;

    public UserInfo() {
    }

    public UserInfo(int uid, String username, String password, int status, String code, String email) {
        this.uid = uid;
        this.username = username;
        this.password = password;
        this.status = status;
        this.code = code;
        this.email = email;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
