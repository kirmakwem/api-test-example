package com.api.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.Response;
import static org.junit.jupiter.api.Assertions.fail;

class Checker {
    public static boolean responseHasToken(Response response) {
        return response.header("x-csrf-token") != null;
    }

    public static boolean responseHasCookie(Response response) {
        return response.header("Set-Cookie") != null;
    }

    public static boolean responseHasField(String response, String fieldName) {
        ObjectMapper objectMapper = new ObjectMapper();
        boolean result = false;
        try {
            result = objectMapper.readTree(response).has(fieldName);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return result;
    }
}
