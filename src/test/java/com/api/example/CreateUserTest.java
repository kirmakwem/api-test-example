package com.api.example;

import com.api.example.api.Endpoints;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreateUserTest extends TestBase {

    @Test
    public void createUserWithEmptyData() {
        String errorMessage = "The following required params are missed: email, password, username, firstName, lastName";
        RequestBody emptyBody = new FormBody.Builder().build();

        Response response = apiMethods.makePostRequest(Endpoints.USER, emptyBody);
        String responseBody = responseHelper.getResponseBody(response);

        assertEquals(400, response.code());
        assertEquals(errorMessage, responseBody);
    }

    @Test
    public void noEmail() {
        String errorMessage = "The following required params are missed: email";
        RequestBody emptyBody = new UserBuilder()
                .withFirstName("SOME NAME")
                .withLastName("SOME LAST NAME")
                .withPassword("SOME PASSWORD")
                .withUsername("SOME USERNAME")
                .build();

        Response response = apiMethods.makePostRequest(Endpoints.USER, emptyBody);
        String responseBody = responseHelper.getResponseBody(response);

        assertEquals(400, response.code());
        assertEquals(errorMessage, responseBody);
    }

    @Test
    public void emailExistsInSystem() {
        String email = dataHelper.generateUniqueEmail();
        dataHelper.createPlainUser(email, "password");
        String errorMessage = "Users with email '" + email + "' already exists";
        RequestBody emptyBody = new UserBuilder()
                .withFirstName("SOME NAME")
                .withLastName("SOME LAST NAME")
                .withPassword("SOME PASSWORD")
                .withUsername("SOME USERNAME")
                .withEmail(email)
                .build();

        Response response = apiMethods.makePostRequest(Endpoints.USER, emptyBody);
        String responseBody = responseHelper.getResponseBody(response);

        assertEquals(400, response.code());
        assertEquals(errorMessage, responseBody);
    }

    @Test
    public void emailNotValid() {
        String errorMessage = "Invalid email format";
        RequestBody emptyBody = new UserBuilder()
                .withFirstName("SOME NAME")
                .withLastName("SOME LAST NAME")
                .withPassword("SOME PASSWORD")
                .withUsername("SOME USERNAME")
                .withEmail("invalidemail@")
                .build();

        Response response = apiMethods.makePostRequest(Endpoints.USER, emptyBody);
        String responseBody = responseHelper.getResponseBody(response);

        assertEquals(400, response.code());
        assertEquals(errorMessage, responseBody);
    }

    @Test
    public void tooShortUsername() {
        String errorMessage = "The value of 'username' field is too short";
        String email = dataHelper.generateUniqueEmail();
        RequestBody emptyBody = new UserBuilder()
                .withFirstName("SOME NAME")
                .withLastName("SOME LAST NAME")
                .withPassword("SOME PASSWORD")
                .withUsername("i")
                .withEmail(email)
                .build();

        Response response = apiMethods.makePostRequest(Endpoints.USER, emptyBody);
        String responseBody = responseHelper.getResponseBody(response);

        assertEquals(400, response.code());
        assertEquals(errorMessage, responseBody);
    }

    @Test
    public void userCreatedSuccessfully() {
        String email = dataHelper.generateUniqueEmail();
        RequestBody emptyBody = new UserBuilder()
                .withFirstName("SOME NAME")
                .withLastName("SOME LAST NAME")
                .withPassword("SOME PASSWORD")
                .withUsername("SOME USERNAME")
                .withEmail(email)
                .build();

        Response response = apiMethods.makePostRequest(Endpoints.USER, emptyBody);
        String responseBody = responseHelper.getResponseBody(response);

        assertEquals(200, response.code());
        assertTrue(Checker.responseHasField(responseBody, "id"));
    }
}
