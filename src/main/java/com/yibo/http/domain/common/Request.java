package com.yibo.http.domain.common;

import java.io.Serializable;

public class Request<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private String token;

    private String userId;

    private String username;

    private String sign;

    private String time;

    private T reqData;

    public Request() {
    }

    public Request(T reqData) {
        this.reqData = reqData;
    }

    public Request(String token, T reqData) {
        this.token = token;
        this.reqData = reqData;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public T getReqData() {
        return reqData;
    }

    public void setReqData(T reqData) {
        this.reqData = reqData;
    }
}