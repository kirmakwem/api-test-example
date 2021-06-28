package com.api.example.helpers;

import com.api.example.UserBuilder;
import com.api.example.api.ApiMethods;
import com.api.example.api.Endpoints;
import okhttp3.RequestBody;
import okhttp3.Response;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

public class DataHelper {

    public String generateUniqueEmail() {
        long currentTime = new Date().getTime();
        return currentTime + "@some.com";
    }

    public void createPlainUser(String email, String password) {
        ApiMethods apiMethods = new ApiMethods();
        RequestBody requestBody = new UserBuilder()
                .withFirstName("PlainFirstName")
                .withLastName("PlainSecondName")
                .withUsername("PlainUsername")
                .withEmail(email)
                .withPassword(password)
                .build();
        Response response = apiMethods.makePostRequest(Endpoints.USER, requestBody);
        assertEquals(200, response.code());
    }
}
