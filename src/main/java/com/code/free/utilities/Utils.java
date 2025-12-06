package com.code.free.utilities;

import java.security.SecureRandom;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.code.free.entities.user.UserEntity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Component
@RequiredArgsConstructor
public class Utils {

    private final JavaMailSender mailSender;
    private final Constants constants;

    public Integer generateOtp() { // to be corrected
        Integer otp = 100000 + new SecureRandom().nextInt(900000);
        return otp;
    }

    public void sendEmail(String email, String body, String subject) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email);
        msg.setSubject(subject);
        msg.setText(body);

        mailSender.send(msg);
    }

    public UserEntity getCurrentUser() {
        return (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public LocalDateTime getExpiryTime() {
        return getExpiryTime(constants.getDefaultExpiryMinutes());
    }

    public LocalDateTime getExpiryTime(Integer minutesFromNow) {
        Integer minutes = minutesFromNow != null
                ? minutesFromNow
                : constants.getDefaultExpiryMinutes();
        return LocalDateTime.ofInstant(
                Instant.now().plus(Duration.ofMinutes(minutes)),
                ZoneOffset.UTC);
    }
}
