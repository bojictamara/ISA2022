package com.isa.bloodtransfusion.exceptions;

public class ComplaintNotFoundException extends Exception {
    public ComplaintNotFoundException() {
        super();
    }
    public ComplaintNotFoundException(String message) {
        super(message);
    }
    public ComplaintNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
