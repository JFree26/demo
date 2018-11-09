package com.jfree.demo.netty;


public class BusinessActionModHandler extends AbstractActionModHandler {


    BusinessActionModHandler(MyNettyHandler handler, Object text) {
        super(handler, text);
    }

    @Override
    public Message action0(Message msg) {
        ActionHandler ar = BusinessHandlerFactory.
                createHandler(msg.getActionType());
        Message message = ar.action(msg);//try-catch
        return message;
    }

    public void doWork() {
        action(inMessage);
    }

}
