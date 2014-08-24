package com.jianchen.vo;

/**
 * @author: jian.cai@qunar.com
 * @Date: 14-8-24 Time: 下午9:47
 */
public class BaseEntity {

    static {
        String x = null;
        x.toString();
    }

    /**
     * The default constructor throws
     * UnsupportedOperationException.
     */
    public BaseEntity() {
        throw new UnsupportedOperationException();
    }

    public void noticeEmployees(String name) {
        throw new UnsupportedOperationException();
    }
}

