package com.iss.cms.core.exceptions;

public class CMSException extends RuntimeException {
    public CMSException() {
    }

    public CMSException(String message) {
        super(message);
    }
}
