package com.jfree.demo.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Setter
@Getter
@NoArgsConstructor
@Component
@ConfigurationProperties(prefix = "message")
@PropertySource("classpath:file-image.yml")
public class ImageConfig {
    private long fileSize;  //压缩大小

    private Double scaleRatio; //压缩比例

    private String upPath; //保存路径

    private String imageType; //图片类型


}
