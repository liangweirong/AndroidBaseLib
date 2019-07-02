package com.example.baselib.net;

import com.google.gson.annotations.SerializedName;

/**
 * 基础 网络数据模型类型
 * 网络请求成功返回 1000
 */
public class BaseNetBean<T> {

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    private T data;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    /**
     * 状态是否OK
     *
     * @return 如果OK返回true
     */
    public boolean isOk() {
        return 1000 == code;
    }

}
