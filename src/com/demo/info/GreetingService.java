package com.demo.info;

import java.time.LocalTime;

/**
 * 问候服务：根据时间返回问候语
 */
public class GreetingService {

    private static final String DEFAULT_USER = "访客";
    private String unusedField = "never used"; // SonarQube: 未使用的字段

    public String greet(String name) {
        String userName = (name == null || name.trim().isEmpty()) ? DEFAULT_USER : name;
        return "你好, " + userName + "! " + getPeriodGreeting();
    }

    private String getPeriodGreeting() {
        int hour = LocalTime.now().getHour();
        if (hour < 6) {
            return "凌晨好";
        } else if (hour < 12) {
            return "早上好";
        } else if (hour < 18) {
            return "下午好";
        } else {
            return "晚上好";
        }
    }
}
