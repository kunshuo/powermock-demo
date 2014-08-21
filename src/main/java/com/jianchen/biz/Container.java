package com.jianchen.biz;

import com.sun.corba.se.impl.ior.OldJIDLObjectKeyTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * 抽象容器类
 *
 * @author: jian.cai@qunar.com
 * @Date: 14-8-8 Time: 上午11:20
 */
public abstract class Container {

    public abstract void put(Object obj);

    public int getSize() {
        return 4;
    }
}
