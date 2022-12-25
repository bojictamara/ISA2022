package com.isa.bloodtransfusion.exceptions;

public class AppointmentDoesNotExistsException extends Exception {
    public AppointmentDoesNotExistsException() {
        super();
    }
    public AppointmentDoesNotExistsException(String message) {
        super(message);
    }
    public AppointmentDoesNotExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
