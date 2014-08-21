package com.jianchen.biz;

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
     * @return Total number of employees in the system.
     */
    public int getEmployeeCount() {
        return Employee.count();
    }

    public void saveEmployee(Employee employee){

    }
}

