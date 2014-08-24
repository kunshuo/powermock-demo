package com.jianchen.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * /**
 * The Department class that will
 * have a relationship with Employee class.
 * One Employee will be
 * associated with at max one Department,
 * but one Department will be associated with
 * one or more Employees
 *
 * @author: jian.cai@qunar.com
 * @Date: 14-8-24 Time: 下午9:19
 */
public class Department {
    private long departmentId;
    private List<Employee> employees = new ArrayList<Employee>();
    /**
     * The max salary offered by this department.
     */
    private long maxSalaryOffered;

    public Department() {
    }

    public Department(int departmentId) {
        this.departmentId = departmentId;
    }

    /**
     * The method to add a new employee to this department.
     *
     * @param employee the instance to add to this
     *                 departmnet.
     */
    public void addEmployee(final Employee employee) {
        employees.add(employee);
        updateMaxSalaryOffered();
    }

    /**
     * The private method that keeps track of
     * max salary offered by this department.
     */
    private void updateMaxSalaryOffered() {
        maxSalaryOffered = 0;
        for (Employee employee : employees) {
            if (employee.getSalary() > maxSalaryOffered) {
                maxSalaryOffered = employee.getSalary();
            }
        }
    }

    public long getDepartmentId() {
        return departmentId;
    }
}
