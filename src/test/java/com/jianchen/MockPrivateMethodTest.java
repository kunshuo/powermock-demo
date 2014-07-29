package com.jianchen;

import com.jianchen.vo.Cat;
import com.jianchen.vo.Person;
import org.easymock.EasyMock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * @author: jian.cai@qunar.com
 * @Date: 14-7-29 Time: 下午6:11
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(PersonHelper.class)
public class MockPrivateMethodTest {

    /**
     * 传进去的就是为M的person
     *
     * @throws Exception
     */
    @Test
    public void testMockPrivateMethod() throws Exception {
        PersonHelper personHelper = PowerMock.createPartialMock(PersonHelper.class, "isPersonType");

        PowerMock.expectPrivate(personHelper, "isPersonType", EasyMock.anyObject()).andReturn(false);

        EasyMock.replay(personHelper);

        Person person = new Person("jianchen", "M", 25);

        assertFalse(personHelper.checkPersonIsValid(person));
    }

    /**
     * 传进去的是cat，理论上应该返回false，但是mock的结果是true。所以该case也可以通过
     */
    @Test
    public void testMockPrivateMethod_1() throws Exception {

        //mock 私有方法
        PersonHelper personHelper = PowerMock.createPartialMock(PersonHelper.class, "isPersonType");
        PowerMock.expectPrivate(personHelper, "isPersonType", EasyMock.anyObject()).andReturn(true);
        EasyMock.replay(personHelper);

        Cat cat = new Cat();
        assertTrue(personHelper.checkPersonIsValid(cat));

    }

}
