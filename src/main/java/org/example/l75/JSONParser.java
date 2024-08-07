package org.example.l75;

import java.util.ArrayList;
import java.util.HashMap;

public class JSONParser {


    public static void main(String[] args) {
        String json = "{\"name\":\"John\", \"age\":30, \"isStudent\":false, \"courses\":[\"Math\",\"Science\"]}";
        Object parsedJson = parse(json);
        System.out.println(parsedJson);
    }

    public static Object parse(String json) {
        json = json.trim();
        if (json.startsWith("{")) {
            return parseObject(json);
        } else if (json.startsWith("[")) {
            return parseArray(json);
        }
        return null;
    }

    private static HashMap<String, Object> parseObject(String json) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        int i = 1; // Start after '{'
        while (i < json.length() - 1) { // Stop before '}'
            skipWhitespace(json, i);
            String key = parseString(json, i);
            i += key.length() + 2; // Move index past key and surrounding quotes
            skipWhitespace(json, i);
            if (json.charAt(i) != ':') throw new RuntimeException("Expected ':'");
            i++; // Move past ':'
            skipWhitespace(json, i);
            Object value = parseValue(json, i);
            jsonObject.put(key, value);
            i += getValueLength(json, i); // Move index to next
            skipWhitespace(json, i);
            if (json.charAt(i) == ',') {
                i++; // Skip over ','
            } else if (json.charAt(i) != '}') {
                throw new RuntimeException("Expected ',' or '}'");
            }
        }
        return jsonObject;
    }

    private static ArrayList<Object> parseArray(String json) {
        ArrayList<Object> jsonArray = new ArrayList<>();
        int i = 1; // Start after '['
        while (i < json.length() - 1) { // Stop before ']'
            skipWhitespace(json, i);
            Object value = parseValue(json, i);
            jsonArray.add(value);
            i += getValueLength(json, i);
            skipWhitespace(json, i);
            if (json.charAt(i) == ',') {
                i++; // Skip over ','
            } else if (json.charAt(i) != ']') {
                throw new RuntimeException("Expected ',' or ']'");
            }
        }
        return jsonArray;
    }

    private static Object parseValue(String json, int start) {
        char firstChar = json.charAt(start);
        if (firstChar == '\"') {
            return parseString(json, start);
        } else if (firstChar == '{') {
            return parseObject(json.substring(start));
        } else if (firstChar == '[') {
            return parseArray(json.substring(start));
        } else if (Character.isDigit(firstChar) || firstChar == '-') {
            return parseNumber(json, start);
        } else if (json.startsWith("true", start)) {
            return true;
        } else if (json.startsWith("false", start)) {
            return false;
        } else if (json.startsWith("null", start)) {
            return null;
        }
        throw new RuntimeException("Unexpected token");
    }

    private static String parseString(String json, int start) {
        int end = start + 1;
        while (end < json.length() && json.charAt(end) != '\"') {
            // Handle escape sequences here if necessary
            end++;
        }
        if (end == json.length()) throw new RuntimeException("Unterminated string");
        return json.substring(start + 1, end);
    }

    private static Number parseNumber(String json, int start) {
        int end = start;
        while (end < json.length() && (Character.isDigit(json.charAt(end)) || json.charAt(end) == '-' || json.charAt(end) == '.')) {
            end++;
        }
        return Double.parseDouble(json.substring(start, end));
    }

    private static void skipWhitespace(String json, int start) {
        while (start < json.length() && Character.isWhitespace(json.charAt(start))) {
            start++;
        }
    }

    private static int getValueLength(String json, int start) {
        char firstChar = json.charAt(start);
        if (firstChar == '\"') {
            return parseString(json, start).length() + 2; // Include quotes
        } else if (firstChar == '{') {
            return parseObject(json.substring(start)).toString().length(); // Assume full object length
        } else if (firstChar == '[') {
            return parseArray(json.substring(start)).toString().length(); // Assume full array length
        } else {
            int end = start;
            while (end < json.length() && !Character.isWhitespace(json.charAt(end)) && json.charAt(end) != ',' && json.charAt(end) != '}' && json.charAt(end) != ']') {
                end++;
            }
            return end - start;
        }
    }
}
