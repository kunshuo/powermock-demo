package com.jianchen.biz.impl;

import com.jianchen.PersonHelper;
import com.jianchen.biz.PersonService;
import com.jianchen.vo.Person;

/**
 * @author: jian.cai@qunar.com
 * @Date: 14-7-29 Time: 下午5:55
 */
public class PersonServiceImpl implements PersonService {

    @Override
    public boolean checkPerson(Person person) {
        return PersonHelper.isMale(person);
    }
}
