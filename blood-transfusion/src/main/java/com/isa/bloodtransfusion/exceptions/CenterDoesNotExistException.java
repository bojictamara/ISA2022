package com.isa.bloodtransfusion.exceptions;

public class CenterDoesNotExistException extends Exception {
    public CenterDoesNotExistException() {
        super();
    }
    public CenterDoesNotExistException(String message) {
        super(message);
    }
    public CenterDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
