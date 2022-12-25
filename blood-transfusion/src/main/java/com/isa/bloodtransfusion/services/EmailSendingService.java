package com.isa.bloodtransfusion.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.isa.bloodtransfusion.email.AccountVerificationEmailContext;
import com.isa.bloodtransfusion.email.AppointmentConfirmationEmailContext;
import com.isa.bloodtransfusion.email.SerializableAppointment;
import com.isa.bloodtransfusion.models.Appointment;
import com.isa.bloodtransfusion.models.User;
import com.isa.bloodtransfusion.repositories.SecureTokenRepository;
import com.isa.bloodtransfusion.security.SecureTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class EmailSendingService {

    private final SecureTokenRepository tokenRepository;

    private final EmailService emailService;

    private final SecureTokenService tokenService;

    public void sendRegistrationConfirmationEmail(String baseUrl, User user) {
        var secureToken = tokenService.createSecureToken();
        secureToken.setUser(user);
        tokenRepository.save(secureToken);
        var emailContext = new AccountVerificationEmailContext();
        emailContext.init(user);
        emailContext.setToken(secureToken.getToken());
        emailContext.buildVerificationUrl(baseUrl, secureToken.getToken());
        try {
            emailService.sendMail(emailContext);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void sendAppointmentConfirmationEmail(Appointment appointment, User user) {
        var emailContext = new AppointmentConfirmationEmailContext();
        emailContext.init(user);
        emailContext.setAppointment(appointment);
        try {

            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(new SerializableAppointment(
                    appointment.getId(),
                    appointment.getStart().format(DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm")),
                    appointment.getCenter().getName(),
                    appointment.getCenter().getAddress().getStreetName(),
                    appointment.getCenter().getAddress().getStreetNumber(),
                    appointment.getCenter().getAddress().getCity(),
                    appointment.getCenter().getAddress().getState()
            ));

            BitMatrix bitMatrix = qrCodeWriter.encode(json, BarcodeFormat.QR_CODE, 500, 500);
            ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
            MatrixToImageConfig config = new MatrixToImageConfig();
            MatrixToImageWriter.writeToStream(bitMatrix, "png", pngOutputStream, config);
            byte[] pngData = pngOutputStream.toByteArray();

            emailService.sendEmailWithAttachment(emailContext, new ByteArrayResource(pngData));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
