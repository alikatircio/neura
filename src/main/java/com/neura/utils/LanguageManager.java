package com.neura.utils;

public class LanguageManager {
        private static String currentLang = "eng";

        public static void setLanguage(String lang) {
            currentLang = lang;
        }

        public static String getLanguage() {
            return currentLang;
        }
}
