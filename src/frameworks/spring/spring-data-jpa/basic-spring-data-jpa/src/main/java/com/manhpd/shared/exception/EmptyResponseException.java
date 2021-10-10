package com.manhpd.shared.exception;

public class EmptyResponseException extends Exception {

    public EmptyResponseException() {
        super();
    }

    public EmptyResponseException(String msg) {
        super(msg);
    }

}
