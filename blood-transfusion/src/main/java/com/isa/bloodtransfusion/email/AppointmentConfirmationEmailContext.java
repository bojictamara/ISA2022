package com.isa.bloodtransfusion.email;

import com.isa.bloodtransfusion.models.Appointment;
import com.isa.bloodtransfusion.models.User;

import java.time.format.DateTimeFormatter;

public class AppointmentConfirmationEmailContext extends AbstractEmailContext {

    @Override
    public <T> void init(T context){
        User customer = (User) context;
        put("firstName", customer.getName());
        setTemplateLocation("emails/appointment-confirmation");
        setSubject("Appointment reserved");
        setFrom("no-reply@blood-transfusion.com");
        setTo(customer.getEmail());
    }

    public void setAppointment(Appointment appointment) {
        put("start", appointment.getStart().format(DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm")));
        put("streetName", appointment.getCenter().getAddress().getStreetName());
        put("streetNumber", appointment.getCenter().getAddress().getStreetNumber());
        put("city", appointment.getCenter().getAddress().getCity());
        put("state", appointment.getCenter().getAddress().getState());
    }

}
