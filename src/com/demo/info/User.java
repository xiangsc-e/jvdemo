package com.demo.info;

/**
 * 用户实体类
 */
public class User {
    private final String name;
    private final int age;
    private final String email;

    public User(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getDisplayInfo() {
        return "--- 用户信息 ---\n"
                + "姓名: " + name + "\n"
                + "年龄: " + age + "\n"
                + "邮箱: " + email;
    }
}
