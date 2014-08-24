package com.jianchen.controller;

import com.jianchen.biz.EmployeeService;
import com.jianchen.vo.Employee;

/**
 * @author: jian.cai@qunar.com
 * @Date: 14-8-21 Time: 上午11:55
 */
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * This method is responsible to return the
     * projected count of employees in the system.
     * Let's say the company is growing by 20% every year,
     * then the project count of employees is 20% more than
     * the actual count of employees in the system.
     * We will also round it off to the ceiling value.
     *
     * @return Total number of projected employees in the
     * system.
     */
    public int getProjectedEmployeeCount() {
        final int actualEmployeeCount = employeeService.getEmployeeCount();
        return (int) Math.ceil(actualEmployeeCount * 1.2);
    }

    public void saveEmployee(Employee employee) {
        employeeService.saveEmployee(employee);
    }


    public int getEmployeeCount() {
        return employeeService.getEmployeeCount();
    }

    /**
     * The method that will
     * find an employee by their email.
     * It delegates this task to the employee service.
     *
     * @param email the employee email to search.
     * @return Employee matching the email.
     */
    public Employee findEmployeeByEmail(String email) {
        return employeeService.findEmployeeByEmail(email);
    }

    /**
     * This method is responsible to check if
     * email is already taken or not.
     * It delegates this task to the employee service.
     *
     * @param email The employee email to validate.
     * @return true if the employee email is taken,
     * false otherwise.
     */
    public boolean isEmployeeEmailAlreadyTaken(String email) {
        return employeeService.employeeExists(new Employee(email));
    }
}
