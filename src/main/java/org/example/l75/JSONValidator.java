package org.example.l75;

public class JSONValidator {

    public static boolean isValidJSON(String jsonStr) {
        int i = 0;
        int len = jsonStr.length();
        boolean inString = false;
        boolean inEscape = false;
        int braces = 0;
        int brackets = 0;
        char c;

        while (i < len) {
            c = jsonStr.charAt(i);
            switch (c) {
                case '{':
                    if (!inString) {
                        braces++;
                    }
                    break;
                case '}':
                    if (!inString) {
                        braces--;
                        if (braces < 0) {
                            return false; // If closing braces appear without matching opening braces
                        }
                    }
                    break;
                case '[':
                    if (!inString) {
                        brackets++;
                    }
                    break;
                case ']':
                    if (!inString) {
                        brackets--;
                        if (brackets < 0) {
                            return false; // If closing brackets appear without matching opening brackets
                        }
                    }
                    break;
                case '"':
                    if (i == 0 || jsonStr.charAt(i - 1) != '\\') {
                        inString = !inString;
                    }
                    break;
            }
            i++;
        }

        return (braces == 0) && (brackets == 0) && !inString;
    }

    public static void main(String[] args) {
        String jsonString = "{\"name\": \"John\", \"age\": 30, \"city\": \"New York\"}";
        System.out.println("Is the JSON valid? " + isValidJSON(jsonString));
    }
}