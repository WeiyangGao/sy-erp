package com.tcps.gaowy.serviceandroid.controller;

import com.tcps.gaowy.basecore.pojo.FileInfo;
import com.tcps.gaowy.serviceandroid.ConfigVlaue;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;

@Slf4j
@RestController
@RequestMapping("/file")
public class FileController {

    /**
     * 文件路径
     */
    @Autowired
    ConfigVlaue configVlaue;


    /**
     * 文件上传
     *
     * @param file 上传的文件内容
     * @return 文件保存路径
     * @throws IOException
     */
    @PostMapping
    public FileInfo upload(MultipartFile file) throws IOException {
        String filePath = configVlaue.getFileUploadPath();
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        log.info(file.getName());
        log.info(file.getContentType());
        log.info(file.getOriginalFilename());
        log.info(file.getSize() + "");
        File localFile = new File(filePath, new Date().getTime() + suffix);
        if (!localFile.exists()) {
            localFile.getParentFile().mkdirs();
        }
        file.transferTo(localFile);
        return new FileInfo(localFile.getAbsolutePath());
    }

    /**
     * 文件下载
     *
     * @param id 文件id
     * @param request --
     * @param response 输出流
     */
    @GetMapping("/{id}")
    public void download(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) {
        //文件夹位置
        String filePath = configVlaue.getFileUploadPath();
        //要下载的文件名字
        //String downloadFileName = id + ".txt";
        String fileName = "myeclipse-pro-2014-GA-offline-installer-windows.exe";
        String downloadFileName = fileName;
        //JDK 7+语法，自动关流。
        try (
                InputStream inputStream = new FileInputStream(new File(filePath, fileName));
                OutputStream outputStream = response.getOutputStream()
        ) {
            response.setContentType("applicaton-xdownload");
            response.addHeader("Content-Disposition", "attachment;filename=" + downloadFileName);
            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
        } catch (Exception e) {
            log.error("下载失败：");
            e.printStackTrace();

        }
    }


}
