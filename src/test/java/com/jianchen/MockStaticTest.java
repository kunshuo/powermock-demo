package com.jianchen;

import com.jianchen.biz.PersonService;
import com.jianchen.biz.impl.PersonServiceImpl;
import com.jianchen.vo.Person;
import org.easymock.EasyMock;
import org.hamcrest.core.AnyOf;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * @author: jian.cai@qunar.com
 * @Date: 14-7-29 Time: 下午4:30
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(PersonHelper.class)
public class MockStaticTest {
    @Test
    public void testMockStaticMethod() {
        PersonService personService = new PersonServiceImpl();
        PowerMock.mockStatic(PersonHelper.class);
        EasyMock.expect(PersonHelper.isMale((Person) EasyMock.anyObject())).andReturn(true);
        PowerMock.replay(PersonHelper.class);

        Person person = new Person("jianchen", "M", 25);

        boolean isMale = personService.checkPerson(person);

        assertTrue("test failed,the person is not male", isMale);


    }
}
