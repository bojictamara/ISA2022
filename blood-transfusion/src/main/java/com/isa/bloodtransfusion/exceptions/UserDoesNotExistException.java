package com.isa.bloodtransfusion.exceptions;

public class UserDoesNotExistException extends Exception {
    public UserDoesNotExistException() {
        super();
    }
    public UserDoesNotExistException(String message) {
        super(message);
    }
    public UserDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
