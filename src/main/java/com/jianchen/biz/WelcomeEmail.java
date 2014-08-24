package com.jianchen.biz;

import com.jianchen.vo.Employee;

/**
 * The class that is responsible to send the Welcome Email to new employees.
 *
 * @author: jian.cai@qunar.com
 * @Date: 14-8-22 Time: 下午12:08
 */
public class WelcomeEmail {
    /**
     * The constructor for the WelcomeEmail
     * is going to connect to the SMTP server
     * and keep the message ready to be relayed.
     * Currently this constructor throws
     * UnsupportedOperationException.
     */
    public WelcomeEmail(final Employee employee, final String message) {
        //Initialize the connection to SMTP server
        //Compose the message body.
        throw new UnsupportedOperationException();
    }

    /**
     * This method is responsible for actually sending the
     * email.
     * Currently this method throws
     * UnsupportedOperationException.
     */
    public void send() {
        throw new UnsupportedOperationException();
    }
}

