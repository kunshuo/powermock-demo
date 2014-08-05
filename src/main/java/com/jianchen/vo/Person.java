package com.jianchen.vo;

/**
 * 人类
 *
 * @author: jian.cai@qunar.com
 * @Date: 14-7-29 Time: 下午4:34
 */
public class Person {
    /**
     * 姓名
     */
    private String name;
    /**
     * 性别
     */
    private String sex;
    /**
     * 年龄
     */
    private int age;

    public Person(String name, String sex, int age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    /**
     * 学人话，萌萌哒
     *
     * @param words
     * @return
     */
    public String responseBack(String words) {
        return "I hear you say " + words;

    }
}
