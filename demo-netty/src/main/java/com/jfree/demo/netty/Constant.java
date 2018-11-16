package com.jfree.demo.netty;

import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Constant {
    private static final Map<String, MyNettyHandler> online = new ConcurrentHashMap<>();

    private static volatile Integer onlineCount = 0;

    public static String ip;

    public static void addCount() {
        synchronized (onlineCount) {
            onlineCount += 1;
        }
    }

    public static void reduceCount() {
        synchronized (onlineCount) {
            onlineCount -= 1;
        }
    }

    public synchronized static void addLine(String token, MyNettyHandler mr) {
        if (online.containsKey(token)) {
            MyNettyHandler old = online.remove(token);
            if (old != null) {
                System.out.println("old isactived=" + old.getCtx().channel().isActive());
                if (old.getCtx() != null) {
                    old.getCtx().close();
                }
            }
            if (mr.getCtx() != null) {
                old.getCtx().close();
            }
        }
        online.put(token, mr);
        System.out.println("old isactived=" + mr.getCtx().channel().isActive());
        addCount();

    }

    public synchronized static MyNettyHandler remove(String token) {
        MyNettyHandler mod = online.remove(token);
        if (mod != null) {
            mod.getCtx().close();
            reduceCount();
            return mod;
        }
        return null;
    }

    public static Map<String, MyNettyHandler> getAll() {
        return online;
    }

    public static MyNettyHandler removeValue(MyNettyHandler mr) {
        synchronized (mr) {
            String key = null;
            for (Map.Entry<String, MyNettyHandler> map : online.entrySet()) {
                if (map.getValue().equals(mr)) {
                    key = map.getKey();
                    break;
                }
            }
            if (key != null) {
                online.remove(key);
                return mr;
            }
        }
        return null;
    }

    public  static void sendAll(String msg) {
        if (msg != null&&online.size()>0) {
            Collection<MyNettyHandler> cn = online.values();
            for (MyNettyHandler mr : cn) {
                mr.getCtx().channel().writeAndFlush(new TextWebSocketFrame(msg));
            }

        }
    }
}
