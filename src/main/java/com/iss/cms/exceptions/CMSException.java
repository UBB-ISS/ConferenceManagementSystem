package com.iss.cms.exceptions;

public class CMSException extends RuntimeException {
    public CMSException() {
    }

    public CMSException(String message) {
        super(message);
    }
}
