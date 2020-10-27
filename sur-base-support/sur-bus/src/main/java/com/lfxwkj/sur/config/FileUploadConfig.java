package com.lfxwkj.sur.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileUploadConfig {

    @Value("${file-upload-path.windows}")
    private String windows;

    @Value("${file-upload-path.linux}")
    private String linux;

    public String getWindows() {
        return windows;
    }

    public void setWindows(String windows) {
        this.windows = windows;
    }

    public String getLinux() {
        return linux;
    }

    public void setLinux(String linux) {
        this.linux = linux;
    }
}
