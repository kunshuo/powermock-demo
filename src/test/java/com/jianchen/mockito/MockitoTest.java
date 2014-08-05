package com.jianchen.mockito;

import com.jianchen.exception.BizException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import java.util.List;

import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * @author: jian.cai@qunar.com
 * @Date: 14-8-5 Time: 下午3:06
 */
public class MockitoTest {
    @Test
    public void testMockito() {
        List<String> list = mock(List.class);
        when(list.get(0)).thenReturn("abc");
        assertTrue(list.get(0).equals("abc"));
        verify(list).get(0);
    }

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

}
