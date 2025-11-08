package com.api.oficina.infrastructure.integrations;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class Sender {

    @Autowired
    private JavaMailSender mailSender;

    @Async
    public void sendEmail(String to, String subject, String body, byte[] base64Pdf) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, false);
            helper.setFrom("mot.motorcycle.uk@gmail.com");
            helper.setCc("anthonyverg@icloud.com");

            if(base64Pdf != null) {
                ByteArrayResource resource = new ByteArrayResource(base64Pdf);
                helper.addAttachment("booking.pdf", resource);
            }

            mailSender.send(mimeMessage);
            System.out.println("Email sent");
        } catch (MessagingException e) {
            System.err.println("Erro ao enviar e-mail: " + e.getMessage());
        }
    }

}
