package com.pn.exception;

/**
 * @Author 自由的骏马
 * @Date 2023/10/8 9:37
 * @PackageName:com.pn.exception
 * @ClassName: BusinessException
 * @Description: TODO
 * @Version 1.0
 */
public class BusinessException extends RuntimeException{
    public BusinessException() {
        super();
    }

    public BusinessException(String message) {
        super(message);
    }
}
