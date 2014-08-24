package com.jianchen.vo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.core.classloader.annotations.SuppressStaticInitializationFor;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * suppress 不需要的方法
 * There are times when we may need to suppress a constructor, method, field, or static initializer
 * because they perform some operations that are not very desirable for doing unit testing of their own code.
 * Such a situation may arise while dealing with third-party libraries or legacy code.
 * <p/>
 * The PowerMockito.when().thenReturn() syntax will not work when we want to suppress a method.
 * That's because if we use the PowerMockito.when().thenReturn() syntax, it will result
 * into a method invocation, and we don't want that to happen.
 *
 * @author: jian.cai@qunar.com
 * @Date: 14-8-24 Time: 下午9:53
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(Boss.class)
@SuppressStaticInitializationFor("com.jianchen.vo.BaseEntity")
public class BossTest {
    @Test
    public void testGetTitle() throws Exception {
        PowerMockito.suppress(PowerMockito.constructor(BaseEntity.class));
        Assert.assertEquals("boss", new Boss("boss").getTitle());
    }

    @Test
    public void testSetName() throws Exception {
        PowerMockito.suppress(PowerMockito.constructor(BaseEntity.class));
        PowerMockito.suppress(PowerMockito.method(BaseEntity.class, "noticeEmployees", String.class));
        final Boss boss = new Boss();
        boss.setName("jianchen");
        Assert.assertEquals("jianchen", boss.getName());
    }

    /**
     * 静态方法被mock掉了，不会被执行
     *
     * @throws Exception
     */
    @Test
    public void testSuppressStatic() throws Exception {
        PowerMockito.suppress(PowerMockito.constructor(BaseEntity.class));
        Boss boss = new Boss();
        Assert.assertNotNull(boss);
    }
}
