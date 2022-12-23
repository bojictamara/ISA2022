package com.isa.bloodtransfusion.exceptions;

public class ExistingUsernameException extends Exception {
    public ExistingUsernameException() {
        super();
    }
    public ExistingUsernameException(String message) {
        super(message);
    }
    public ExistingUsernameException(String message, Throwable cause) {
        super(message, cause);
    }

}
