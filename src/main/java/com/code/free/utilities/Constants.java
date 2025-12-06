package com.code.free.utilities;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class Constants {

    @Value("${jwt.secret}")
    private String jwtSecretKey;

    @Value("${jwt.expiration.ms}")
    private Integer jwtExpirationMs;

    @Value("${gemini.api.key}")
    private String geminiApiKey;

    private String usernameSuggestionConstantKey = "usernameSuggestionPrompt";

    @Value("${gemini.ai.model.name}")
    private String geminiAIModelName;

    private String otpEmailBodyKey = "otpEmailBody";

    private String otpEmailSubjectKey = "otpEmailSubject";

    private String emailRegexKey = "emailRegex";

    private Integer defaultExpiryMinutes=5;

}
