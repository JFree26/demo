package com.jfree.demo.service;


import com.jfree.demo.common.Result;
import com.jfree.demo.entity.vo.WebFileVO;

public interface ExcelService {

 Result importExcel(WebFileVO webFileVO);
}
