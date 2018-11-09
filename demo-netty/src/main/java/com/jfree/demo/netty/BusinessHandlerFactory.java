package com.jfree.demo.netty;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BusinessHandlerFactory {

    private static Map<Integer, ActionHandler> handlers = new ConcurrentHashMap<>();

    private static ActionHandler notFound = new NotFoundHandler();

    static {
        handlers.put(2, new TestHandler());
    }

    public static ActionHandler createHandler(Integer actionType) {
        if (!handlers.keySet().contains(actionType)) {
            return notFound;
        }
        return handlers.get(actionType);
    }

}
