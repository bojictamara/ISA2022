package com.isa.bloodtransfusion.exceptions;

public class ExistingEmailException extends Exception {
    public ExistingEmailException() {
        super();
    }
    public ExistingEmailException(String message) {
        super(message);
    }
    public ExistingEmailException(String message, Throwable cause) {
        super(message, cause);
    }
}
