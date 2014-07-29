package com.jianchen;

import com.jianchen.vo.Person;

/**
 * @author: jian.cai@qunar.com
 * @Date: 14-7-29 Time: 下午4:33
 */
public class PersonHelper {
    /**
     * 性别是否为Male
     *
     * @param person
     * @return
     */
    public static boolean isMale(Person person) {
        return "M".equals(person.getSex());
    }

    /**
     * 是否是人类
     *
     * @param obj
     * @return
     */
    private boolean isPersonType(Object obj) {
        return obj instanceof Person;
    }

    /**
     * 你是人类吗，么么哒
     *
     * @param obj
     * @return
     */
    public boolean checkPersonIsValid(Object obj) {
        if (!isPersonType(obj))
            return false;
        else
            return true;
    }
}
