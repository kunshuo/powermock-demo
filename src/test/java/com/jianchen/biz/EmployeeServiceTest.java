package com.jianchen.biz;

import com.jianchen.vo.Employee;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author: jian.cai@qunar.com
 * @Date: 14-8-21 Time: 下午1:19
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(Employee.class)
public class EmployeeServiceTest {
    /**
     * mock静态方法
     */
    @Test
    public void shouldReturnTheCountOfEmployeesUsingTheDomainClass() {
        PowerMockito.mockStatic(Employee.class);
        PowerMockito.when(Employee.count()).thenReturn(900);
        EmployeeService employeeService = new EmployeeService();
        Assert.assertEquals(900, employeeService.getEmployeeCount());
    }
}
