package com.manhpd.sendingemailutils.domain.service;

import com.manhpd.sendingemailutils.dto.Mail;
import com.manhpd.sendingemailutils.shared.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;

@Service
public class EmailService implements IEmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    @Override
    public void sendMessage(Mail mail) throws MessagingException {
        this.emailSender.send(this.buildMimeMessage(mail));
    }

    private MimeMessage buildMimeMessage(Mail mail) throws MessagingException {
        MimeMessage message = this.emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

        String htmlContent = this.getHtmlContent(mail);

        helper.setTo(mail.getTo());
        helper.setText(htmlContent, true);
        helper.setSubject(mail.getSubject());
        helper.setFrom(mail.getFrom());

        return message;
    }

    private String getHtmlContent(Mail mail) {
        Context context = new Context();
        context.setVariables(mail.getModel());
        String html = this.templateEngine.process(Constants.EMAIL_TEMPLATE, context);

        return html;
    }

}
