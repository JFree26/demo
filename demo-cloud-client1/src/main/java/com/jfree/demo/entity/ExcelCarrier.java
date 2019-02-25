package com.jfree.demo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
public class ExcelCarrier extends BaseMerchant {


    private Long id;

    private String test1;

    private String test2;

    private String test3;

    private LocalDateTime createTime;
}
