package com.jianchen.biz;

import com.jianchen.util.EmployeeIdGenerator;
import com.jianchen.vo.Employee;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author: jian.cai@qunar.com
 * @Date: 14-8-21 Time: 下午1:19
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({Employee.class, EmployeeIdGenerator.class})
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

    /**
     * mock 静态方法
     */
    @Test
    public void shouldGenerateEmployeeIdIfEmployeeIsNew() {
        Employee mock = PowerMockito.mock(Employee.class);
        PowerMockito.when(mock.isNew()).thenReturn(true);
        PowerMockito.mockStatic(EmployeeIdGenerator.class);
        PowerMockito.when(EmployeeIdGenerator.getNextId()).thenReturn(90);
        EmployeeService employeeService = new EmployeeService();
        employeeService.saveEmployee(mock);
        PowerMockito.verifyStatic();
        EmployeeIdGenerator.getNextId();
        Mockito.verify(mock).setEmployeeId(90);
        Mockito.verify(mock).create();
    }
}
