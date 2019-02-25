package com.jfree.demo.service.impl;

import com.jfree.demo.common.Result;
import com.jfree.demo.config.ImageConfig;
import com.jfree.demo.entity.vo.WebFileVO;
import com.jfree.demo.service.ImageService;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;


@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageConfig imageConfig;

    @Override
    public Result uploadImage(WebFileVO webFileVO) throws IOException {
        String[] imageType = imageConfig.getImageType().split(",");
        boolean flag = false;
        for (String type : imageType) {
            if (StringUtils.endsWithIgnoreCase(webFileVO.getFile().getOriginalFilename(), type)) {
                flag = true;
                break;
            }
        }
        if (flag) {
            String fileType = webFileVO.getFile().getContentType();
            // 获得文件后缀名称
            String imageName = fileType.substring(fileType.indexOf("/") + 1);
            // 原名称
            String oldFileName = webFileVO.getFile().getOriginalFilename();
            // 新名称
            String newFileName = System.currentTimeMillis() + "$test$" + oldFileName;

            FileCopyUtils.copy(webFileVO.getFile().getInputStream(), new FileOutputStream(newFileName));
        }
        return null;
    }
}
