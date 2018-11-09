package com.jfree.demo.netty;

import com.jfree.demo.config.DateUtil;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

public abstract class AbstractActionModHandler implements ActionModHandler {


    Integer actionType;

    Message inMessage;

    MyNettyHandler handler;

    TextWebSocketFrame text;

    AbstractActionModHandler(MyNettyHandler handler, Object text) {
        this.handler = handler;
        this.text = (TextWebSocketFrame) text;
        initRead();

    }

    public Message action(Message message) {
        Message outMessage = new Message();
        if (read(message)) {
            if (message.getActionType() == 10) {
                outMessage.setActionType(11);
                outMessage.setTime(LocalDateTime.now());
                outMessage.setToken(message.getToken());
                outMessage.setValue("pong");
                outMessage.setStrTime(DateUtil.localDateTimeToString(outMessage.getTime()));
            } else {
                outMessage = action0(message);
            }
        } else {
            outMessage.setActionType(-1);
            outMessage.setValue("发送的数据不正确");
            outMessage.setTime(LocalDateTime.now());
            outMessage.setStrTime(DateUtil.localDateTimeToString(outMessage.getTime()));
        }
        out(outMessage);
        return outMessage;
    }

    @Override
    public void out(Message message) {
        if (handler.getCtx().channel().isWritable()) {
            handler.getCtx().channel().writeAndFlush(new TextWebSocketFrame(message.megToJson())).addListener(future -> {
                if (!future.isSuccess()) {
                    //log.warn
                    System.out.println(message.megToJson() + "发送失败");
                }
            });
        }else {
            try {
                handler.getCtx().channel().writeAndFlush(new TextWebSocketFrame(message.megToJson())).sync();
                //log.info
                System.out.println(message.megToJson()+"阻塞发送成功");
            } catch (InterruptedException e) {
               //log.info
                System.out.println(message.megToJson()+"阻塞发送失败");
            }

        }
    }

    public void initRead() {
        if (StringUtils.isEmpty(text.text())) {

        } else {
            try {
                inMessage = Message.jsonToMeg(text.text());
                actionType = inMessage.getActionType();
            } catch (Exception e) {
            }

        }
    }

    public boolean read(Message message) {
        return (null != message) && (null != message.getActionType());
    }


    abstract Message action0(Message message);

    public Integer actionType() {
        return actionType;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Integer getActionType() {
        return actionType;
    }

    public void setActionType(Integer actionType) {
        this.actionType = actionType;
    }

    public Message getInMessage() {
        return inMessage;
    }

    public void setInMessage(Message inMessage) {
        this.inMessage = inMessage;
    }

    public MyNettyHandler getHandler() {
        return handler;
    }

    public void setHandler(MyNettyHandler handler) {
        this.handler = handler;
    }

    public TextWebSocketFrame getText() {
        return text;
    }

    public void setText(TextWebSocketFrame text) {
        this.text = text;
    }
}
