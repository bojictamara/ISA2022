package com.isa.bloodtransfusion.services;

import com.isa.bloodtransfusion.email.AbstractEmailContext;
import javax.mail.MessagingException;

public interface EmailService {
    void sendMail(AbstractEmailContext email) throws MessagingException;
}
