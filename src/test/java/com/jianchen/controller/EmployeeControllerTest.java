package com.jianchen.controller;

import com.jianchen.biz.EmployeeService;
import com.jianchen.vo.Employee;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
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

    /**
     * 验证调用方法的顺序，InOrder的使用
     */
    @Test
    public void shouldInvokeIsNewBeforeInvokingCreate() {
        Employee mock = PowerMockito.mock(Employee.class);
        EmployeeService employeeService = new EmployeeService();
        employeeService.saveEmployee(mock);
        //First we have to let PowerMock know that the verification order is going to be important
        //This is done by calling Mockito.inOrder and passing
        //it the mocked object.
        InOrder inOrder = Mockito.inOrder(mock);
        //Next, we can continue our verification using
        //the inOrder instance using the same technique
        //as seen earlier.
        inOrder.verify(mock).isNew();
        inOrder.verify(mock).update();
        inOrder.verify(mock, Mockito.never()).create();
    }

    /**
     * 方法参数的matcher
     */
    @Test
    public void shouldFindEmployeeByEmail() {
        final EmployeeService mock = PowerMockito.mock(EmployeeService.class);
        final Employee employee = new Employee();
        //Notice that we are just check if the email address starts with "deep" then we have found the matching employee.
        PowerMockito.when(mock.findEmployeeByEmail(Mockito.startsWith("deep"))).thenReturn(employee);
        final EmployeeController employeeController = new EmployeeController(mock);
        //Following 2 invocations will match return valid employee,
        //since the email address passed does start with "deep"
        Assert.assertSame(employee, employeeController.findEmployeeByEmail("deep@gitshah.com"));
        Assert.assertSame(employee, employeeController.findEmployeeByEmail("deep@packtpub.com"));
        //However, this next invocation would not return a valid employee,
        //since the email address passed does not start with "deep"
        Assert.assertNull(employeeController.findEmployeeByEmail("noreply@packtpub.com"));
    }

    /**
     * match anything
     */
    @Test
    public void shouldReturnNullIfNoEmployeeFoundByEmail() {
        final EmployeeService mock = PowerMockito.mock(EmployeeService.class);
        //No matter what email is passed calling the findEmployeeByEmail on the
        //mocked EmployeeService instance is now going to return null.
        PowerMockito.when(mock.findEmployeeByEmail(Mockito.anyString())).thenReturn(null);
        final EmployeeController employeeController = new EmployeeController(mock);
        Assert.assertNull(employeeController.findEmployeeByEmail("deep@gitshah.com"));
        Assert.assertNull(employeeController.findEmployeeByEmail("deep@packtpub.com"));
        Assert.assertNull(employeeController.findEmployeeByEmail("noreply@packtpub.com"));
    }

    /**
     * 定制match
     */
    @Test
    public void shouldReturnTrueIfEmployeeEmailIsAlreadyTaken() {
        final EmployeeService mock = PowerMockito.mock(EmployeeService.class);
        //A little more complex matcher using the ArgumentMatcher class.
        //By implementing the matches method in this class we can write any kind of complex logic
        //to validate that the correct arguments are being passed.
        final String employeeEmail = "packt@gitshah.com";
        PowerMockito.when(mock.employeeExists(Mockito.argThat(new ArgumentMatcher<Employee>() {
            /**
             * This method currently only checks that
             * the email address set in the employee instance
             * matches the email address we passed to the controller.
             * {@inheritDoc}
             */
            @Override
            public boolean matches(Object employee) {
                return ((Employee) employee).getEmail().equals(employeeEmail);
            }
        }))).thenReturn(true);
        final EmployeeController employeeController = new EmployeeController(mock);
        Assert.assertTrue(employeeController.isEmployeeEmailAlreadyTaken(employeeEmail));
    }

    /**
     * answer返回值
     */
    @Test
    public void shouldFindEmployeeByEmailUsingTheAnswerInterface() {
        final EmployeeService mock = PowerMockito.mock(EmployeeService.class);
        final Employee employee = new Employee();
        //Notice use of Answer interface.
        //Depending on what argument is passed we could either
        //return a valid employee
        //or return null.
        PowerMockito.when(mock.findEmployeeByEmail(Mockito.anyString())).then(new Answer<Employee>() {
            /**
             * Implementing the answer method to return a valid
             * employee, * if email starts with "deep" or ends "packtpub.com"
             * in all other cases we return null.
             * {@inheritDoc}
             */
            @Override
            public Employee answer(InvocationOnMock invocation) throws Throwable {
                final String email = (String) invocation.getArguments()[0];
                if (email == null) return null;
                if (email.startsWith("deep"))
                    return employee;
                if (email.endsWith("packtpub.com"))
                    return employee;
                return null;
            }
        });
        final EmployeeController employeeController = new EmployeeController(mock);
        //Following 3 invocations will match and return valid //employee,
        //since the email address passed does start with "deep" //or ends with "packtpub.com"
        Assert.assertSame(employee, employeeController.findEmployeeByEmail("deep@gitshah.com"));
        Assert.assertSame(employee, employeeController.findEmployeeByEmail("deep@packtpub.com"));
        Assert.assertSame(employee, employeeController.findEmployeeByEmail("noreply@packtpub.com"));
        //However, this next invocation would not return a valid //employee,
        //since the email address passed does not start with //"deep" or ends with "packtpub.com"
        Assert.assertNull(employeeController.findEmployeeByEmail("hello@world.com"));
    }


    @Test
    public void shouldReturnCountOfEmployeesFromTheServiceWithDefaultAnswer() {
        //Creating a mock using the PowerMockito.mock method for the
        //EmployeeService class.
        EmployeeService mock = PowerMockito.mock(EmployeeService.class,
                /**
                 * Passing in a default answer instance.
                 * This method will be called when no matching mock methods have been setup.
                 */
                new Answer() {
                    /**
                     * We are simply implementing the answer method of the
                     interface and returning hardcoded 10.
                     * @param invocation The context of the invocation.
                     *        Holds useful information like what arguments where passed.
                     * @return Object the value to return for this mock.
                     */
                    @Override
                    public Object answer(InvocationOnMock invocation) {
                        return 10;
                    }
                });
        //note:没有显式的指定mock方法的返回值，此处用answer就会默认返回每个方法的返回值
        EmployeeController employeeController = new EmployeeController(mock);
        Assert.assertEquals(10, employeeController.getEmployeeCount());
        //note:如果执行下面注释掉的语句，就会报错，因为employeeService.findEmployeeByEmail(email)也会返回10
        //然后类型转换就会失败，因为正常应该返回Employee类型
        Assert.assertTrue(employeeController.findEmployeeByEmail("abc") instanceof Employee);
    }


    /**
     * 部分mock，针对某些需要mock的实例，我们可能只需mock部分方法，其他的方法还是希望调用具体的实现。这样就用到了spy
     */
}

