package com.jianchen.exception;

/**
 * 业务异常类
 *
 * @author: jian.cai@qunar.com
 * @Date: 14-8-5 Time: 下午3:18
 */
public class BizException extends RuntimeException {
    public BizException(String errMsg) {
        super(errMsg);

    }

}
