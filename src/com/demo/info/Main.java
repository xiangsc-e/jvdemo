package com.demo.info;

/**
 * 简单的信息展示应用入口
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=====================================");
        System.out.println("  欢迎使用 信息展示 Demo");
        System.out.println("=====================================");

        // 1. 展示系统信息
        SystemInfoPrinter.printSystemInfo();

        // 2. 展示用户信息
        User user = new User("张三", 28, "zhangsan@example.com");
        System.out.println(user.getDisplayInfo());

        // 3. 展示问候语
        GreetingService greetingService = new GreetingService();
        System.out.println(greetingService.greet(user.getName()));

        System.out.println("=====================================");
        System.out.println("  程序执行结束");
        System.out.println("=====================================");
    }
}
