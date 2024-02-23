package com.DreamCompany.IDreamedThat.services.sendEmail;

import jakarta.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface ServiceSendEmail {
    public void sendCode(String userEmail) throws MessagingException, UnsupportedEncodingException;
}
