package com.louis.mango.backup.util;

import org.springframework.http.HttpStatus;

/**
 * Http结果封装
 * Created by liyue
 * Time 2019-09-17 9:15
 */
public class HttpResult {
    private int code=200;
    private String msg;
    private Object data;

    public static HttpResult error(){
        return error(HttpStatus.INTERNAL_SERVER_ERROR.value(),"未知异常，请联系管理员");
    }

    public static HttpResult error(String msg){
        return error(HttpStatus.INTERNAL_SERVER_ERROR.value(),msg);
    }

    public static HttpResult error(int code, String msg) {
        HttpResult r = new HttpResult();
        r.setCode(code);
        r.setMsg(msg);
        return r;
    }

    public static HttpResult ok(String msg) {
        HttpResult r = new HttpResult();
        r.setMsg(msg);
        return r;
    }

    public static HttpResult ok(Object data) {
        HttpResult r = new HttpResult();
        r.setData(data);
        return r;
    }

    public static HttpResult ok() {
        return new HttpResult();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
