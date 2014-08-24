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
@PrepareForTest({Employee.class, EmployeeIdGenerator.class, EmployeeService.class})
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

    @Test
    public void shouldSendWelcomeEmailToNewEmployees()
            throws Exception {
        Employee employeeMock = PowerMockito.mock(Employee.class);
        PowerMockito.when(employeeMock.isNew()).thenReturn(true);
        PowerMockito.mockStatic(EmployeeIdGenerator.class);
        //Creating the mock for WelcomeEmail.
        WelcomeEmail welcomeEmailMock = PowerMockito.mock(WelcomeEmail.class);
        /**
         *Notice the whenNew syntax.
         *PowerMockito.whenNew().withArguments().thenReturn()
         *informs PowerMock that,
         *1. When New instance of WelcomeEmail is created,
         *2. With employee instance and "Welcome to Mocking
         *with PowerMock How-to!" text,
         *3. Then return a mock of WelcomeEmail class.
         */
        PowerMockito.whenNew(WelcomeEmail.class)
                .withArguments(employeeMock, "Welcome to Mocking with PowerMock How-to!")
                .thenReturn(welcomeEmailMock);
        EmployeeService employeeService = new EmployeeService();
        employeeService.saveEmployee(employeeMock);

        /**
         * Verifying that the constructor for the
         * WelcomeEmail class is invoked
         * with arguments as the mocked employee instance and
         * text "Welcome to Mocking with PowerMock How-to!".
         */
        PowerMockito.verifyNew(WelcomeEmail.class).withArguments(employeeMock, "Welcome to Mocking with PowerMock How-to!");
        //Verifying that the send method was called on the mocked instance.
        Mockito.verify(welcomeEmailMock).send();
    }

    /**
     * 使用spy进行mock部分方法。其他未显式mock的方法，调用都是实例的具体实现。
     * 特别的在spy测试类的时候，传递的应该是一个实体，而不是类名
     */
    @Test
    public void shouldInvokeTheCreateEmployeeMethodWhileSavingANewEmployee() {
        //Following is the syntax to create a spy using the PowerMockito.spy method.
        //Notice that we have to pass an actual instance of the EmployeeService class.
        //This is necessary since a spy will only mock few methods of a class and
        //invoke the real methods for all methods that are not mocked.
        final EmployeeService spy = PowerMockito.spy(new EmployeeService());
        final Employee employeeMock = PowerMockito.mock(Employee.class);
        PowerMockito.when(employeeMock.isNew()).thenReturn(true);
        //Notice that we have to use the PowerMockito
        //.doNothing().when(spy).createEmployee() syntax to create the spy. This is required
        //because if we use the PowerMockito.when(spy.createEmployee())
        // syntax will result in calling the actual method on the spy.
        // Hence, remember when we are using spies,always use the doNothing(), doReturn()
        //or the doThrow() syntax only.
        PowerMockito.doNothing().when(spy).createEmployee(employeeMock);
        spy.saveEmployee(employeeMock);
        //Verification is simple enough and we have to use the standard syntax for it.
        Mockito.verify(spy).createEmployee(employeeMock);
    }

    /**
     * spy一个private方法
     */
    @Test
    public void shouldInvokeThePrivateCreateEmployeeMethodWhileSavingANewEmployee() throws Exception {
        final EmployeeService spy = PowerMockito.spy(new EmployeeService());
        final Employee employeeMock = PowerMockito.mock(Employee.class);
        PowerMockito.when(employeeMock.isNew()).thenReturn(true);
        //Since we cannot access the private method outside the class,
        //We have to pass the name of the private method along with the arguments passed
        //To the PowerMockito.doNothing().when() method.
        PowerMockito.doNothing().when(spy, "createEmployee", employeeMock);
        spy.saveEmployee(employeeMock);
        //Verification is similar to setting up the mock.
        // We have to inform PowerMock about which private method to verify by invoking the invoke method on PowerMockito.verifyPrivate().
        // The name of the private method along with its //arguments are passed to invoke method.
        PowerMockito.verifyPrivate(spy).invoke("createEmployee", employeeMock);
    }


}
