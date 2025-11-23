package com.code.free.utilities;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Component;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class GeminiUtils {

    private final Constants constants;
    private final ConstantsReaderWrapper constantsReaderWrapper;

    public List<String> usernameSuggestionByGemini(String username) throws IOException{

        String prompt = constantsReaderWrapper.getValueByKey(constants.getUsernameSuggestionConstantKey())+username;

        Client geminiClient = Client.builder().apiKey(constants.getGeminiApiKey()).build();
        GenerateContentResponse response = geminiClient.models.generateContent(
                constants.getGeminiAIModelName(),
                prompt,
                null);
        List<String> suggestions = List.of(response.text().split(","));

        return suggestions;
    }
}
