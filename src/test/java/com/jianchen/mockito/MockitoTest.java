package com.jianchen.mockito;

import com.jianchen.biz.BaseService;
import com.jianchen.biz.Container;
import com.jianchen.biz.impl.SubService;
import com.jianchen.exception.BizException;
import com.jianchen.vo.Person;
import com.sun.xml.internal.rngom.parse.host.Base;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * @author: jian.cai@qunar.com
 * @Date: 14-8-5 Time: 下午3:06
 */
public class MockitoTest {
    /**
     * mock方法
     */
    @Test
    public void testMockito() {
        List<String> list = mock(List.class);
        when(list.get(0)).thenReturn("abc");
        assertTrue(list.get(0).equals("abc"));
        verify(list).get(0);
    }

    /**
     * 抛出异常
     */
    @Test(expected = BizException.class)
    public void testMockito_throw_exception() {
        List<String> list = mock(List.class);
        doThrow(new BizException("aaa")).when(list).get(1);
        list.get(1);
        //verify(list, times(1)).size();
    }

    @Test
    public void testMockito_1() {
        List<String> list = mock(List.class);
        when(list.size()).thenReturn(5);
        assertTrue(list.size() == 5);
        verify(list, times(1)).size();
    }

    /**
     * mock出来的对象，不会报索引越界异常
     */
    @Test
    public void testaa() {
        List<String> list = mock(List.class);
        assertTrue(list.get(9) == null);
    }

    /**
     * 针对传进来的参数可以定制输出，做些额外的工作
     */
    @Test
    public void testThenAnswer() {
        Person person = mock(Person.class);
        when(person.responseBack("how are you!")).thenAnswer(new Answer<String>() {
            @Override
            public String answer(InvocationOnMock invocationOnMock) throws Throwable {
                Object[] args = invocationOnMock.getArguments();
                System.out.println(invocationOnMock.getMethod()); //print: public java.lang.String com.jianchen.vo.Person.responseBack(java.lang.String)
                return (String) args[0];
            }
        });

        assertEquals("how are you!", person.responseBack("how are you!"));
    }

    /**
     * 通过spy改变行为
     */
    @Test
    public void testBaseMethod() {
        SubService ss = new SubService();
        ss.save();

        //如下的方法没有效果，打印出super和sub的save
        SubService subService = mock(SubService.class);
        doNothing().when(((BaseService) subService)).validate();
        subService.save();

        //改用spy试试，成功了，仅打印出sub->save
        SubService spy = spy(new SubService());
        doNothing().when((BaseService) spy).validate();
        spy.save();
    }

    /**
     * 测试抽象类
     */
    @Test
    public void testMockAbstractClass() {
        Container container = mock(Container.class, Mockito.CALLS_REAL_METHODS);
        assertEquals(4, container.getSize());
    }

}
