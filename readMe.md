1,easyMock的版本低，会导致运行时报mock的静态方法类为非接口的错误。

mock 静态方法
mock final方法
mock 构造器
mock 私有方法
argument matchers to assert that methods are invoked with correct arguments. (mockito本身就支持)

学习笔记：
测试类上的注解说明：
@PrepareForTest
This annotation is required when we want to mock final classes or classes with final, private, static, or native methods.

PowerMockito.doNothing,PowerMockito.doThrow主要用在mock没有返回值的方法的场景。不仅可以用在静态方法上，也可以用在实体方法上

PowerMock uses custom class loader and bytecode manipulation to enable mocking of static methods.
It does this by using the @RunWith and @PrepareForTest annotations.

Verification is a process where we assert that a certain method was invoked by the code under test.

verify方法的时候，需要显示通知mock框架，比如通过调用 PowerMockito.verifyStatic();

因为PowerMock和mockito配合使用，所以在调用方法的时候，到底是调用powermock还是调用Mockito，
有一个简单方法是：如果该方法在mockito里本身就支持，那就调用mockito。
如何判断呢？比如powermock支持的新特性的，这些在mockito里就不支持，比如veryfy静态方法。


为什么powerMock可以mock类的final方法？
Most of the mocking frameworks are based on the use of the Proxy pattern (see http:// en.wikipedia.org/wiki/Proxy_pattern#Example).
The Proxy pattern is heavily dependent on the fact that a class can be subclassed and a method can be overridden. Because of this reason,
most of the mocking frameworks cannot mock final methods or classes.
Since PowerMock uses a custom class loader and bytecode manipulation, it is able to achieve what other mocking frameworks fail to do.

Answer的使用：
The Answer interface specifies an action to execute along with the return value. The return value is returned when the given method is invoked on the mock.
The instance of InvocationOnMock passed as an argument to the answer method of the Answer interface is quite resourceful. Using this we can do the following:
 callRealMethod(): Call the real method
 getArguments(): Get all arguments passed to the method invocation
 getMethod(): Return the method that was invoked on the mock instance
 getMock(): Get the mocked instance

spy: mock部分方法
Spies are designed in such a way that they will invoke real methods for all methods that are not mocked.
This is the reason we need to pass in an instance of the class that is to be partially mocked (in our case the EmployeeService class)
while creating a spy using the PowerMockito.spy method.

*******************
To mock any method on a spy, we have to necessarily use the PowerMockito. doNothing()/doReturn()/doThrow() syntax only.