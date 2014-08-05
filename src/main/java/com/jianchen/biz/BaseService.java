package com.jianchen.biz;

/**
 * @author: jian.cai@qunar.com
 * @Date: 14-8-5 Time: 下午5:44
 */
public class BaseService {
    public void save() {
        validate();
    }

    public void validate() {
        System.out.println("super->save");
    }
}
