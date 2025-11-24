package com.code.free.configuration;

import org.springframework.context.annotation.Configuration;

import com.code.free.utilities.Constants;
import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class GeminiConfig {

    private final Constants constants;

    private Client geminiConfig() {
        return Client.builder().apiKey(constants.getGeminiApiKey()).build();
    }

    public GenerateContentResponse generateContentResponse(String prompt) {
        GenerateContentResponse response = geminiConfig().models.generateContent(
                constants.getGeminiAIModelName(),
                prompt,
                null);
        return response;
    }
    
}
