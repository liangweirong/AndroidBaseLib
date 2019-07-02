package com.example.baselib.net;

/**
 * Created by Administrator on 2018/5/3.
 */

public class StatusBean{

    public static final String OK_CODE="0"; //ok的
    //状态码
    private String status;
    //附带消息文字内容
    private String message;



    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isOk(){
        return status!=null&&OK_CODE.equals(status);
    }

    public boolean is(){
        return status!=null&&OK_CODE.equals(status);
    }


}
