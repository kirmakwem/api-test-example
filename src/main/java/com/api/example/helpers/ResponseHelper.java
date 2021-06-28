package com.api.example.helpers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Response;

import java.io.IOException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.fail;

public class ResponseHelper {

    public String getResponseBody(Response response) {
        String responseBody = null;
        try {
            responseBody = Objects.requireNonNull(response.body()).string();
        } catch (IOException e) {
            e.printStackTrace();
            fail("Произошла ошибка во время получения содержимого body");
        }

        return responseBody;
    }

    public String getFieldValue(String response, String fieldName) {
        ObjectMapper objectMapper = new ObjectMapper();
        String result = "";
        try {
            result = objectMapper.readTree(response).get(fieldName).asText();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return result;
    }


    public String getXcsrfToken(Response response) {
        return response.header("x-csrf-token");
    }

    public String getCookies(Response response) {
        String cookieHeader = response.header("Set-Cookie");
        assert cookieHeader != null;
        int startIndex = cookieHeader.lastIndexOf('=') + 1;
        return cookieHeader.substring(startIndex);
    }
}
