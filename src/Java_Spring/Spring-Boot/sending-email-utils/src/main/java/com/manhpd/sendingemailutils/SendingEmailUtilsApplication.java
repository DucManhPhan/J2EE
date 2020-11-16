package com.manhpd.sendingemailutils;

import com.manhpd.sendingemailutils.domain.service.IEmailService;
import com.manhpd.sendingemailutils.dto.Mail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class SendingEmailUtilsApplication implements ApplicationRunner {

    private static Logger log = LoggerFactory.getLogger(SendingEmailUtilsApplication.class);

    @Autowired
    private IEmailService emailService;

    public static void main(String[] args) {
        SpringApplication.run(SendingEmailUtilsApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Sending Email with Thymeleaf HTML Template Example");

        Mail mail = new Mail();
        mail.setFrom("noreply-email-notifications-gmail.com");
        mail.setTo("to-user@gmail.com");
        mail.setSubject("Sending Email with Thymeleaf HTML Template Example");

        Map model = new HashMap();
        model.put("name", "name");
        model.put("location", "location");
        model.put("signature", "your-signature");
        mail.setModel(model);

        this.emailService.sendMessage(mail);
    }
}
