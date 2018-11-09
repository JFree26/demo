package com.jfree.demo.netty;//package com.example.demo.netty;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//public class NettyStartConfig implements ApplicationRunner {
//
//    @Value("${websocket.server.port}")
//    private Integer port;
//
//    private static boolean sendInfo = false;
//
//    @Override
//    public void run(ApplicationArguments applicationArguments) throws Exception {
//        new NettyServer(port).start();
//
//    }
//
//    public static void updateInfo(boolean info) {
//        sendInfo = info;
//    }
//
//    public static boolean sendInfo() {
//        return sendInfo;
//    }
//}
