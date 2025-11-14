package com.neura.utils;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.openqa.selenium.By;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;

public class GetElement {


    private static final String LOCATOR_PATH = "locators";

    /**
     * Aranan elementName için locators klasöründeki tüm JSON dosyalarında arama yapar.
     */
    public static By getLocator(String elementName) {
        try {
            ClassLoader loader = GetElement.class.getClassLoader();
            URL folderUrl = loader.getResource(LOCATOR_PATH);

            if (folderUrl == null) {
                throw new RuntimeException("'locators' folder not found in resources.");
            }

            File folder = new File(folderUrl.toURI());
            File[] jsonFiles = folder.listFiles((dir, name) -> name.endsWith(".json"));

            if (jsonFiles == null || jsonFiles.length == 0) {
                throw new RuntimeException("No JSON files found in locators directory.");
            }

            // Her JSON dosyasını sırayla oku
            for (File file : jsonFiles) {
                try (InputStream is = new FileInputStream(file)) {
                    JSONObject json = new JSONObject(new JSONTokener(is));

                    if (json.has(elementName)) {
                        JSONObject element = json.getJSONObject(elementName);
                        String type = element.getString("type");
                        String value = element.getString("value");
                        System.out.println("Found '" + elementName + "' in: " + file.getName());
                        return buildLocator(type, value);
                    }
                }
            }

            throw new RuntimeException("Element '" + elementName + "' not found in any locator JSON file.");

        } catch (Exception e) {
            throw new RuntimeException("Error while reading locators: " + e.getMessage(), e);
        }
    }

    /**
     * Locator tipine göre By objesi döndürür
     */
    private static By buildLocator(String type, String value) {
        switch (type.toLowerCase()) {
            case "id":
                return By.id(value);
            case "xpath":
                return By.xpath(value);
            case "css":
            case "cssselector":
                return By.cssSelector(value);
            case "name":
                return By.name(value);
            case "classname":
                return By.className(value);
            default:
                throw new IllegalArgumentException(" Unknown locator type: " + type);
        }
    }
}
