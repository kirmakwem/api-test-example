package com.api.example.api;

import okhttp3.*;

import java.io.IOException;

public class ApiMethods {
    private final OkHttpClient client = new OkHttpClient();

    public Response makeGetRequest(String url) {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try  {
            return client.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException("Не удалось совершить запрос");
        }
    }

    public Response makeGetRequest(String url, String token, String cookie) {
        Request request = new Request.Builder()
                .header("X-Csrf-Token", token)
                .header("Cookie", "auth_sid=" + cookie)
                .url(url)
                .build();

        try  {
            return client.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException("Не удалось совершить запрос");
        }
    }


    public Response makePostRequest(String url, RequestBody body) {
        Request request = new Request.Builder()
                .header("Content-Type", "application/json")
                .url(url)
                .post(body)
                .build();

        try  {
            return client.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException("Не удалось совершить запрос");
        }
    }

    public Response makePutRequest(String url, RequestBody body, String token, String cookie) {
        Request request = new Request.Builder()
                .header("Content-Type", "application/json")
                .header("X-Csrf-Token", token)
                .header("Cookie", "auth_sid=" + cookie)
                .url(url)
                .put(body)
                .build();

        try  {
            return client.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException("Не удалось совершить запрос");
        }
    }
}
