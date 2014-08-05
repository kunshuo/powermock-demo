package com.jianchen.biz.impl;

import com.jianchen.biz.BaseService;

/**
 * @author: jian.cai@qunar.com
 * @Date: 14-8-5 Time: 下午5:46
 */
public class SubService extends BaseService {
    @Override
    public void save() {
        super.save();
        System.out.println("sub->save");
    }
}
