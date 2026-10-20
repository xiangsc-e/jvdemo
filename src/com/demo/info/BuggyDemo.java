package com.demo.info;

/**
 * 故意包含一些典型问题的类，用于验证 SonarQube 扫描效果
 */
public class BuggyDemo {

    // SonarQube: 硬编码密码
    private static final String PASSWORD = "admin123";

    public String process(String input) {
        int unusedVariable = 42; // SonarQube: 未使用的局部变量
        try {
            return input.toUpperCase();
        } catch (Exception e) {
            // SonarQube: 空 catch 块，异常被吞掉
        }
        return input;
    }

    public boolean isAdmin(String password) {
        // SonarQube: 密码明文比较
        return PASSWORD.equals(password);
    }
}
