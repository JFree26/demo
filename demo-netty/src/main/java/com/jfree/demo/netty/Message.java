package com.jfree.demo.netty;


import com.jfree.demo.config.JSONUtil;

import java.time.LocalDateTime;

public class Message {

    private Integer actionType;// 10为客户端ping，服务器返回11.

    private String token;

    private String value;

    private LocalDateTime time;//消息产生时间

    private String strTime;

    public Integer getActionType() {
        return actionType;
    }

    public void setActionType(Integer actionType) {
        this.actionType = actionType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getStrTime() {
        return strTime;
    }

    public void setStrTime(String strTime) {
        this.strTime = strTime;
    }

    public static Message jsonToMeg(String po) {
        return JSONUtil.jsonToPo(po, Message.class);
    }

    public String megToJson() {
        return JSONUtil.poToJson(this);
    }

    public static Message ping() {
        Message ping = new Message();
        ping.setActionType(11);
        return ping;
    }

    @Override
    public String toString() {
        return "Message{" +
                "actionType=" + actionType +
                ", value='" + value + '\'' +
                ", time=" + time +
                '}';
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
