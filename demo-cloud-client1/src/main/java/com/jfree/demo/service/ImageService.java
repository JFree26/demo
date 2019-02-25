package com.jfree.demo.service;

import com.jfree.demo.common.Result;
import com.jfree.demo.entity.vo.WebFileVO;

import java.io.IOException;

public interface ImageService {

    Result uploadImage(WebFileVO webFileVO) throws IOException;
}
