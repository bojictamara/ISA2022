package com.isa.bloodtransfusion.exceptions;

public class AppointmentAlreadyCancelledException extends Exception {
    public AppointmentAlreadyCancelledException() {
        super();
    }
    public AppointmentAlreadyCancelledException(String message) {
        super(message);
    }
    public AppointmentAlreadyCancelledException(String message, Throwable cause) {
        super(message, cause);
    }
}
