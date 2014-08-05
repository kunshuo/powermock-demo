package com.jianchen.mockito;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import java.util.List;

import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

    @Test
    public void testMockito_throwException() {
        List<String> list = mock(List.class);
        when(list.size()).thenReturn(5);
        assertTrue(list.size() == 5);

    }

}
