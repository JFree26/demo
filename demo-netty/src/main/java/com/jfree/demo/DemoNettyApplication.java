package com.jfree.demo;

import com.jfree.demo.netty.NettyServer;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class DemoNettyApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoNettyApplication.class, args);
	}

    @Component
    public static class NettyStartConfig implements ApplicationRunner {

        @Override
        public void run(ApplicationArguments applicationArguments) throws Exception {
            new Thread(() -> {
                try {
                    new NettyServer(32119).start();
                } catch (Exception e) {
                    return;
                }
            }).start();
            return;
        }
    }
}
