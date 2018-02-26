package com.tcps.gaowy.serviceandroid;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 读取配置文件中自定义的属性
 */
@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "MyProjectConfig")
public class ConfigVlaue {
    //上传文件的路径
    private String fileUploadPath;
}
