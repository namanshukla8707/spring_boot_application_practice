package com.code.free.utilities;

import java.security.SecureRandom;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Component
@RequiredArgsConstructor
public class Utils {

    
private final JavaMailSender mailSender;
    
    public Integer generateOtp() {
        Integer otp = 100000 + new SecureRandom().nextInt(900000);
        return otp;
    }

    public void sendEmail(String email, String body,String subject) {
       
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email);
        msg.setSubject(subject);
        msg.setText(body);

        mailSender.send(msg);
    }
}
