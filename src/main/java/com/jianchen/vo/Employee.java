package com.jianchen.vo;

/**
 * @author: jian.cai@qunar.com
 * @Date: 14-8-21 Time: 下午12:30
 */
public class Employee {

    private String email;

    public Employee() {
    }

    public Employee(String email) {
        this.email = email;
    }

    /**
     * The method that is responsible to return the
     * count of employees in the system.
     *
     * @return The total number of employees in the system.
     * Currently this
     * method throws UnsupportedOperationException.
     */
    public static int count() {
        throw new UnsupportedOperationException();
    }

    /**
     * The method that is responsible to increment
     * salaries of all employees by the given percentage.
     *
     * @param percentage the percentage value by which
     *                   salaries would be increased
     *                   Currently this method throws
     *                   UnsupportedOperationException.
     */
    public static void giveIncrementOf(int percentage) {
        throw new UnsupportedOperationException();
    }

    /**
     * The method that identifies if the employee
     * is not yet persisted in the DB.
     *
     * @return true if employee is not yet
     * persisted in the DB, false otherwise.
     * Currently this method throws
     * UnsupportedOperationException
     */
    public boolean isNew() {
        throw new UnsupportedOperationException();
    }

    /**
     * This method is responsible to update
     * an existing employee's information into the DB.
     * Currently this method throws
     * UnsupportedOperationException
     */
    public void update() {
        throw new UnsupportedOperationException();
    }

    /**
     * This method is responsible to create
     * a new employee into the DB.
     * Currently this method throws
     * UnsupportedOperationException
     */
    public void create() {
        throw new UnsupportedOperationException();
    }

    public void setEmployeeId(int id) {
        throw new UnsupportedOperationException();
    }

    public String getEmail() {
        return this.email;
    }
}
