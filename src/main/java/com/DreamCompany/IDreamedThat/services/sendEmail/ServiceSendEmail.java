package com.DreamCompany.IDreamedThat.services.sendEmail;

import com.DreamCompany.IDreamedThat.models.Recovery;
import jakarta.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface ServiceSendEmail {
    public void sendCode(String userEmail, Recovery recovery) throws MessagingException, UnsupportedEncodingException;
}
