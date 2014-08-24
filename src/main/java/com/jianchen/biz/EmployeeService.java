package com.jianchen.biz;

import com.jianchen.util.EmployeeIdGenerator;
import com.jianchen.vo.Employee;

/**
 * @author: jian.cai@qunar.com
 * @Date: 14-8-21 Time: 上午11:56
 */
public class EmployeeService {
    /**
     * This method is responsible to return
     * the count of employees in the system.
     * It does it by calling the
     * static count method on the Employee class.
     *
     * @return Total number of employees in the system.
     */
    public int getEmployeeCount() {
        return Employee.count();
    }

    public void saveEmployee(Employee employee) {
        if (employee.isNew()) {
            employee.setEmployeeId(EmployeeIdGenerator.getNextId());
            employee.create();
            WelcomeEmail emailSender = new WelcomeEmail(employee, "Welcome to Mocking with PowerMock How-to!");
            emailSender.send();
            return;
        }
        employee.update();
    }

    /**
     * This method is responsible to increment the salary
     * of all employees in the system by the given percentage.
     * It does this by calling the static giveIncrementOf method
     * on the Employee class.
     *
     * @param percentage the percentage value by which
     *                   salaries would be increased
     * @return true if the increment was successful.
     * False if increment failed because of some exception
     * otherwise.
     */
    public boolean giveIncrementToAllEmployeesOf(int percentage) {
        try {
            Employee.giveIncrementOf(percentage);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Finds the employee by email.
     * Currently this method throws UnsupportedOperationException.
     *
     * @param email the employee email to search.
     * @return Employee matching the email.
     */
    public Employee findEmployeeByEmail(String email) {
        throw new UnsupportedOperationException();
    }

    /**
     * The method that will check whether
     * the employee exists based on various criterion's.
     * Currently this method throws
     * UnsupportedOperationException.
     *
     * @param employee the employee instance to match.
     * @return true if th employee exists, false otherwise.
     */
    public boolean employeeExists(Employee employee) {
        throw new UnsupportedOperationException();
    }
}

