package com.code.free.utilities;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Component;

import com.code.free.configuration.Config;
import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class GeminiUtils {

    private final Constants constants;
    private final ConstantsReaderWrapper constantsReaderWrapper;
    private final Config config;

    private GenerateContentResponse generateContentResponse(String prompt) {
        GenerateContentResponse response = config.geminiConfig().models.generateContent(
                constants.getGeminiAIModelName(),
                prompt,
                null);
        return response;
    }

    public List<String> usernameSuggestionByGemini(String username) throws IOException {

        String prompt = constantsReaderWrapper.getValueByKey(constants.getUsernameSuggestionConstantKey()) + username;

        List<String> suggestions = List.of(generateContentResponse(prompt).text().split(","));

        return suggestions;
    }
}
