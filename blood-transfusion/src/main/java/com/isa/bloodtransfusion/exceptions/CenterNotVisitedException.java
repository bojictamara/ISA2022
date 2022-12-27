package com.isa.bloodtransfusion.exceptions;

public class CenterNotVisitedException extends Exception {
    public CenterNotVisitedException() {
        super();
    }
    public CenterNotVisitedException(String message) {
        super(message);
    }
    public CenterNotVisitedException(String message, Throwable cause) {
        super(message, cause);
    }
}
