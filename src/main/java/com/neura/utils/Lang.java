package com.neura.utils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

public class Lang {

    private static JsonNode root;

    static {
        try {
            String lang = ConfigReader.get("language");
            String path;
            if (lang.equals("en"))
                path = "src/main/resources/languages/English.json";
            else
                path = "src/main/resources/languages/Japanese.json";

            root = new ObjectMapper().readTree(new File(path));
        } catch (Exception e) {
            throw new RuntimeException("Language file could not be loaded!", e);
        }
    }

    public static String text(String keyPath) {
        String[] keys = keyPath.split("\\.");
        JsonNode node = root;

        for (String key : keys) {
            node = node.get(key);
        }

        return node.asText();
    }
}
