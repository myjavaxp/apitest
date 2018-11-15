package com.yibo.http.domain.common;

import java.io.Serializable;
import java.util.StringJoiner;

import static org.springframework.http.HttpStatus.OK;

/**
 * 这个类可以从后端项目直接拷贝，当然自己可以改造，只要保持成员变量一致即可。
 */
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1991015577868168856L;

    private int repCode;

    private String repMsg;

    private T repData;

    /**
     * 返回那些需要有返回值内容的情况，比如查询，把查询结果放入repData即可
     *
     * @param repData 返回值内容
     * @return 标准响应体
     */
    public static <T> Result<T> ofSuccessData(T repData) {
        return new Result<>(OK.value(), OK.getReasonPhrase(), repData);
    }

    /**
     * 返回只需要一个成功消息的时候，比如提示新增成功，修改成功
     *
     * @param repMsg 消息体
     * @return 标准响应体
     */
    public static <T> Result<T> ofSuccessMessage(String repMsg) {
        return new Result<>(OK.value(), repMsg, null);
    }

    /**
     * 返回一个标准http状态码（枚举类Status里没有的）。通常用于处理异常，返回前台一个错误信息
     *
     * @param repCode 状态码
     * @param repMsg  状态信息
     * @return 标准响应体
     */
    public static <T> Result<T> ofFailedStatus(int repCode, String repMsg) {
        return new Result<>(repCode, repMsg, null);
    }

    public Result() {
        this.repCode = OK.value();
        this.repMsg = OK.getReasonPhrase();
    }

    private Result(int repCode, String repMsg, T repData) {
        this.repCode = repCode;
        this.repMsg = repMsg;
        this.repData = repData;
    }

    public int getRepCode() {
        return repCode;
    }

    public void setRepCode(int repCode) {
        this.repCode = repCode;
    }

    public String getRepMsg() {
        return repMsg;
    }

    public void setRepMsg(String repMsg) {
        this.repMsg = repMsg;
    }

    public T getRepData() {
        return repData;
    }

    public void setRepData(T repData) {
        this.repData = repData;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Result.class.getSimpleName() + "[", "]")
                .add("repCode=" + repCode)
                .add("repMsg='" + repMsg + "'")
                .add("repData=" + repData)
                .toString();
    }
}