package com.jfree.demo.entity.vo;

import com.jfree.demo.entity.BaseMerchant;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
@NoArgsConstructor
public class WebFileVO {

    private MultipartFile file;

    private BaseMerchant baseMerchant;



}
