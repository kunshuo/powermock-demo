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
        EmployeeController employeeController = new EmployeeController(mock);
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
        EmployeeController employeeController = new EmployeeController(mock);
        Employee employee = new Employee();
        employeeController.saveEmployee(employee);
        //Verifying that controller did call the
        //saveEmployee method on the mocked service instance.
        Mockito.verify(mock).saveEmployee(employee);

        //note:因为saveEmployee没有返回值，所以验证该方法，可以通过校验employeeService方法是否被调用。
        //从这里也可以看出powerMock没有重新造轮子，而是继承了现存mock框架的既有功能
    }

    /**
     * mock 有返回值的静态方法
     */
    @Test
    public void shouldReturnTheCountOfEmployeesUsingTheDomainClass() {
        PowerMockito.mockStatic(Employee.class);
        PowerMockito.when(Employee.count()).thenReturn(900);
        EmployeeService employeeService = new EmployeeService();
        Assert.assertEquals(900, employeeService.getEmployeeCount());
    }

    /**
     * mock 无返回值的静态方法
     */
    @Test
    public void shouldReturnTrueWhenIncrementOf10PercentageIsGivenSuccessfully() {
        PowerMockito.mockStatic(Employee.class);
        PowerMockito.doNothing().when(Employee.class);
        Employee.giveIncrementOf(10);
        EmployeeService employeeService = new EmployeeService();
        Assert.assertTrue(employeeService.giveIncrementToAllEmployeesOf(10));
    }

    /**
     * mock 无返回值的静态方法，抛出异常
     */
    @Test
    public void shouldReturnFalseWhenIncrementOf10PercentageIsNotGivenSuccessfully() {
        PowerMockito.mockStatic(Employee.class);
        PowerMockito.doThrow(new IllegalStateException()).when(Employee.class);
        Employee.giveIncrementOf(10);
        EmployeeService employeeService = new EmployeeService();
        Assert.assertFalse(employeeService.giveIncrementToAllEmployeesOf(10));
    }

    /**
     * verify 方法是否被调用
     */
    @Test
    public void shouldCreateNewEmployeeIfEmployeeIsNew() {
        Employee mock = PowerMockito.mock(Employee.class);
        PowerMockito.when(mock.isNew()).thenReturn(true);
        EmployeeService employeeService = new EmployeeService();
        employeeService.saveEmployee(mock);
        //Verifying that the create method was indeed invoked
        //on the employee instance.
        Mockito.verify(mock).create();
        //Verifying that while creating a new employee
        //update was never invoked.
        Mockito.verify(mock, Mockito.never()).update();
    }


    @Test
    public void shouldInvoke_giveIncrementOfMethodOnEmployeeWhileGivingIncrement() {
        PowerMockito.mockStatic(Employee.class);
        PowerMockito.doNothing().when(Employee.class);
        Employee.giveIncrementOf(9);
        EmployeeService employeeService = new EmployeeService();
        employeeService.giveIncrementToAllEmployeesOf(9);
        //We first have to inform PowerMock that we will now verify
        //the invocation of a static method by calling verifyStatic.
        PowerMockito.verifyStatic();
        //Then we need to inform PowerMock about the method we want to verify.
        //This is done by actually invoking the static method.
        Employee.giveIncrementOf(9);
    }
}
