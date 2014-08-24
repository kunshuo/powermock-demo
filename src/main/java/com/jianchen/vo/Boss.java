package com.jianchen.vo;

/**
 * @author: jian.cai@qunar.com
 * @Date: 14-8-24 Time: 下午9:52
 */
public class Boss extends BaseEntity {

    private String title;
    private String name;

    public Boss() {
    }

    public Boss(String title) {
        super();
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setName(String name) {
        this.name = name;
        super.noticeEmployees(name);
    }

    public String getName() {
        return name;
    }
}
