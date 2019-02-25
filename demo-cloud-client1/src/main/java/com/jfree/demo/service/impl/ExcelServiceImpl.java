package com.jfree.demo.service.impl;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.jfree.demo.common.Result;
import com.jfree.demo.entity.ExcelCarrier;
import com.jfree.demo.entity.vo.WebFileVO;
import com.jfree.demo.service.ExcelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExcelServiceImpl implements ExcelService{
    private final static Logger logger = LoggerFactory.getLogger(ExcelServiceImpl.class);
    @Override
    public Result importExcel(WebFileVO webFileVO) {
        ImportParams importParams = new ImportParams();
        List<ExcelCarrier> list=null;
        try {
            list = ExcelImportUtil.importExcel(webFileVO.getFile().getInputStream(), ExcelCarrier.class, importParams);
        } catch (Exception e) {
              logger.warn("excel上传错误");
        }
        return null;
    }
}
