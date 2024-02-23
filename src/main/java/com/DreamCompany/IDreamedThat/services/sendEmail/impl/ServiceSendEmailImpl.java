package com.DreamCompany.IDreamedThat.services.sendEmail.impl;

import com.DreamCompany.IDreamedThat.models.Keystore;
import com.DreamCompany.IDreamedThat.models.Recovery;
import com.DreamCompany.IDreamedThat.services.sendEmail.ServiceSendEmail;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

@Service
public class ServiceSendEmailImpl implements ServiceSendEmail {
    private final JavaMailSender mailSender;
    public ServiceSendEmailImpl(JavaMailSender mailSender) { this.mailSender = mailSender; }
//implementacion exitosa dar mejor manejo de errores y eliminar el usuario o realizar un @Transaccional si existen fallas en el envio
    @Override
    public void sendCode(String userEmail) throws MessagingException, UnsupportedEncodingException {
        String key = UUID.randomUUID().toString();
        Keystore.addKey(key, new Recovery(userEmail));
        String contentMessage = "<h1> Please click on the link to confirm your email: </h1>";
        String urlVerification = "http://localhost:8080/api/confirm-registration?token=" + key;
        contentMessage+="<h2><a href=\"" + urlVerification + "\">VERIFICATION</a></h2>";
        contentMessage+="<p>Thanks.</p>";
        contentMessage+="<p></p>";

        MimeMessage message = mailSender.createMimeMessage();
        message.setContent( contentMessage , "text/html; charset=utf-8" );
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("idreamedthat38@gmail.com", "I Dreamed That");
        helper.setTo(userEmail);
        helper.setSubject("\n" + "Email confirmation |I Dreamed That");

        mailSender.send(message);
    }
}
