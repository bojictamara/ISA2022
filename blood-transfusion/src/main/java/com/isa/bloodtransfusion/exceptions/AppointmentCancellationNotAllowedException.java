package com.isa.bloodtransfusion.exceptions;

public class AppointmentCancellationNotAllowedException extends Exception {
    public AppointmentCancellationNotAllowedException() {
        super();
    }
    public AppointmentCancellationNotAllowedException(String message) {
        super(message);
    }
    public AppointmentCancellationNotAllowedException(String message, Throwable cause) {
        super(message, cause);
    }
}
