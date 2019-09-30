package com.louis.mango.core.vo;

/**
 * 登陆接口封装对象
 * Created by liyue
 * Time 2019-09-23 10:19
 */
public class LoginBean {
    private String account;
    private String password;
    private String captcha;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }
}
