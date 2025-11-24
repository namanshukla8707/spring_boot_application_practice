package com.code.free.utilities;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Component;

import com.code.free.configuration.GeminiConfig;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class GeminiUtils {

    private final Constants constants;
    private final ConstantsReaderWrapper constantsReaderWrapper;
    private final GeminiConfig geminiConfig;

    public List<String> usernameSuggestionByGemini(String username) throws IOException {

        String prompt = constantsReaderWrapper.getValueByKey(constants.getUsernameSuggestionConstantKey()) + username;
        List<String> suggestions = List.of(geminiConfig.generateContentResponse(prompt).text().split(","));
        suggestions = suggestions.stream()
                .map(name -> name.trim() + Math.abs(name.hashCode())%100)
                .toList();
        return suggestions;
    }
}
