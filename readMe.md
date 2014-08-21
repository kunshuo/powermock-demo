1,easyMock的版本低，会导致运行时报mock的静态方法类为非接口的错误。

学习笔记：
测试类上的注解说明：
@PrepareForTest
This annotation is required when we want to mock final classes or classes with final, private, static, or native methods.

PowerMockito.doNothing,PowerMockito.doThrow主要用在mock没有返回值的方法的场景。不仅可以用在静态方法上，也可以用在实体方法上

PowerMock uses custom class loader and bytecode manipulation to enable mocking of static methods.
It does this by using the @RunWith and @PrepareForTest annotations.

Verification is a process where we assert that a certain method was invoked by the code under test.