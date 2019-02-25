package com.jfree.demo.common;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class Result<T> {

    private Integer code;

    private T one;

    private List<T> list;
}
