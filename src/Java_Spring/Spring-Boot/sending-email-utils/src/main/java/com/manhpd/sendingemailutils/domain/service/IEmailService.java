package com.manhpd.sendingemailutils.domain.service;

import com.manhpd.sendingemailutils.dto.Mail;

import javax.mail.MessagingException;

public interface IEmailService {

    void sendMessage(Mail mail) throws MessagingException;

}
