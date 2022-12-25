package com.isa.bloodtransfusion.services;

import com.isa.bloodtransfusion.email.AbstractEmailContext;
import org.springframework.core.io.ByteArrayResource;

import javax.mail.MessagingException;

public interface EmailService {
    void sendMail(AbstractEmailContext email) throws MessagingException;
    void sendEmailWithAttachment(AbstractEmailContext email, ByteArrayResource attachment);
}
