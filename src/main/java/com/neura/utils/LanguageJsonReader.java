package com.neura.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class LanguageJsonReader {


    public static String get(String page, String key) throws IOException {
        String lang = LanguageManager.getLanguage();
        ObjectMapper mapper = new ObjectMapper();

        JsonNode root;
        if (lang.equals("eng")) {
            root = mapper.readTree(new File("src/main/resources/languages/English.json"));
        } else if (lang.equals("jps")) {
            root = mapper.readTree(new File("src/main/resources/languages/Japanese.json"));
        } else {
            throw new RuntimeException("Unsupported language: " + lang);
        }

        return root.get(page).get(key).asText();
    }
}
