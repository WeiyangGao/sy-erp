package com.tcps.gaowy.serviceandroid.controller;

import com.tcps.gaowy.basecore.pojo.FileInfo;
import com.tcps.gaowy.serviceandroid.ConfigVlaue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

@Slf4j
@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    ConfigVlaue configVlaue;

    @PostMapping
    public FileInfo upload(MultipartFile file) throws IOException {
        String filePath = configVlaue.getFileUploadPath();
        log.info(file.getName());
        log.info(file.getOriginalFilename());
        log.info(file.getSize() + "");
        File localFile = new File(filePath, new Date().getTime() + ".txt");
        if (!localFile.exists()) {
            boolean result = localFile.getParentFile().mkdirs();
            if (!result) {
                log.info("创建失败！");
            }
        }
        file.transferTo(localFile);
        return new FileInfo(localFile.getAbsolutePath());
    }
}
