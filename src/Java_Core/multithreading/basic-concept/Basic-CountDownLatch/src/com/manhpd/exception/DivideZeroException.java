package com.manhpd.exception;

public class DivideZeroException extends Exception {

    public DivideZeroException(String message) {
        super(message);
    }

    public DivideZeroException(String message, Throwable cause) {
        super(message, cause);
    }

}
