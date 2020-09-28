package com.example.mybatis.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * json返回模型-为了能正确的描述返回数据
 */
public class Result<D> implements Serializable {

    //用于自定义JSON输出时候的字段名称
    @JsonProperty("isSuccess")
    private boolean success = false;

    private String code;

    private String message;

    private D data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public D getData() {
        return data;
    }

    public void setData(D data) {
        this.data = data;
    }
}
