package com.code.free.utilities;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ConstantsReaderWrapper {
    
    @Value("classpath:constants.json")
    private Resource jsonFile;

    public String getValueByKey(String key) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(jsonFile.getInputStream());
        return node.get(key).asText();
    }
}
