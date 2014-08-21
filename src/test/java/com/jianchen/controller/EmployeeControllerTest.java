package com.jianchen.controller;

import com.jianchen.biz.EmployeeService;
import com.jianchen.vo.Employee;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;

/**
 * @author: jian.cai@qunar.com
 * @Date: 14-8-21 Time: 上午11:59
 */
public class EmployeeControllerTest {
    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testGetProjectedEmployeeCount() throws Exception {

    }

    @Test
    public void shouldReturnProjectedCountOfEmployeesFromTheService() {
        //Creating a mock using the PowerMockito.mock
        //method for the EmployeeService class.
        EmployeeService mock = PowerMockito.mock(EmployeeService.class);
        /**
         * note:This mocked instance can be programmed to return dummy data on the occurrence of a certain event (such as method invocation).
         * To set up the dummy data, we have to use the PowerMockito.when method.
         * */
        //Next statement essentially says that when
        //getProjectedEmployeeCount method
        //is called on the mocked EmployeeService instance,
        //return 8.
        PowerMockito.when(mock.getEmployeeCount()).thenReturn(8);
        EmployeeController employeeController = new EmployeeController(mock);
        Assert.assertEquals(10, employeeController.getProjectedEmployeeCount());
    }

    @Test
    public void shouldInvokeSaveEmployeeOnTheServiceWhileSavingTheEmployee() {
        EmployeeService mock = PowerMockito.mock(EmployeeService.class);
        EmployeeController employeeController = new
                EmployeeController(mock);
        Employee employee = new Employee();
        employeeController.saveEmployee(employee);
        //Verifying that controller did call the
        //saveEmployee method on the mocked service instance.
        Mockito.verify(mock).saveEmployee(employee);

        //note:因为saveEmployee没有返回值，所以验证该方法，可以通过校验employeeService方法是否被调用。
        //从这里也可以看出powerMock没有重新造轮子，而是继承了现存mock框架的既有功能
    }


    @Test
    public void shouldInvokeSaveEmployeeOnTheServiceWhileSavingTheEmployeewithSettings() {
        EmployeeService mock = PowerMockito.mock(EmployeeService.class, Mockito
                .withSettings()
                .name("EmployeeServiceMock")
                .verboseLogging()); //输出详细的日志信息
        EmployeeController employeeController = new
                EmployeeController(mock);
        Employee employee = new Employee();
        employeeController.saveEmployee(employee);
        //Verifying that controller did call the
        //saveEmployee method on the mocked service instance.
        Mockito.verify(mock).saveEmployee(employee);

        //note:因为saveEmployee没有返回值，所以验证该方法，可以通过校验employeeService方法是否被调用。
        //从这里也可以看出powerMock没有重新造轮子，而是继承了现存mock框架的既有功能
    }
}
