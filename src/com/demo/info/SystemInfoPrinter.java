package com.demo.info;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 系统信息展示工具类
 */
public class SystemInfoPrinter {

    public static void printSystemInfo() {
        System.out.println("--- 系统信息 ---");
        System.out.println("Java 版本: " + System.getProperty("java.version"));
        System.out.println("操作系统: " + System.getProperty("os.name"));
        System.out.println("当前时间: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }
}
