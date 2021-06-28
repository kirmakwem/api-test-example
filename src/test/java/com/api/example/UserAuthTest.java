package com.api.example;

import com.api.example.api.ApiParams;
import com.api.example.api.Endpoints;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class UserAuthTest extends TestBase {

    @Test
    public void loginWithWrongUserData() {
        RequestBody loginBody = new FormBody.Builder()
                .add(ApiParams.USERNAME, "qw;knjwf")
                .add(ApiParams.PASSWORD, "kldmwekldmweld")
                .build();

        Response response = apiMethods.makePostRequest(Endpoints.LOGIN, loginBody);

        assertEquals(400, response.code());
        assertFalse(Checker.responseHasToken(response));
        assertFalse(Checker.responseHasCookie(response));
    }

    @Test
    public void loginWithCorrectData() {
        String email = dataHelper.generateUniqueEmail();
        String password = "password";
        dataHelper.createPlainUser(email, password);

        RequestBody loginBody = new FormBody.Builder()
                .add(ApiParams.EMAIL, email)
                .add(ApiParams.PASSWORD, password)
                .build();

        Response response = apiMethods.makePostRequest(Endpoints.LOGIN, loginBody);

        assertEquals(200, response.code());
        assertTrue(Checker.responseHasToken(response));
        assertTrue(Checker.responseHasCookie(response));
    }

    @Test
    public void getUserById() {
        String email = dataHelper.generateUniqueEmail();
        String password = "password";
        dataHelper.createPlainUser(email, password);

        RequestBody loginBody = new FormBody.Builder()
                .add(ApiParams.EMAIL, email)
                .add(ApiParams.PASSWORD, password)
                .build();

        Response loginResponse = apiMethods.makePostRequest(Endpoints.LOGIN , loginBody);
        String loginResponseString = responseHelper.getResponseBody(loginResponse);
        String cookie = responseHelper.getCookies(loginResponse);
        String token = responseHelper.getXcsrfToken(loginResponse);
        String userId = responseHelper.getFieldValue(loginResponseString, "user_id");

        Response getUserResponse = apiMethods.makeGetRequest(Endpoints.USER + "/" + userId, token, cookie);
        String responseAsString = responseHelper.getResponseBody(getUserResponse);

        assertEquals(200, getUserResponse.code());
        assertTrue(Checker.responseHasField(responseAsString, "firstName"));
        assertTrue(Checker.responseHasField(responseAsString, "lastName"));
        assertTrue(Checker.responseHasField(responseAsString, "email"));
        assertTrue(Checker.responseHasField(responseAsString, "id"));
    }

    @Test
    public void updateUserData() {
        String email = dataHelper.generateUniqueEmail();
        String password = "password";
        dataHelper.createPlainUser(email, password);

        RequestBody loginBody = new FormBody.Builder()
                .add(ApiParams.EMAIL, email)
                .add(ApiParams.PASSWORD, password)
                .build();

        Response loginResponse = apiMethods.makePostRequest(Endpoints.LOGIN , loginBody);
        String loginResponseString = responseHelper.getResponseBody(loginResponse);
        String cookie = responseHelper.getCookies(loginResponse);
        String token = responseHelper.getXcsrfToken(loginResponse);
        String userId = responseHelper.getFieldValue(loginResponseString, "user_id");

        RequestBody newUserData = new UserBuilder()
                .withFirstName("NEWFNAME")
                .withLastName("NEWLNAME")
                .build();

        Response updateUserResponse = apiMethods.makePutRequest(Endpoints.USER, newUserData, token, cookie);
        assertEquals(200, updateUserResponse.code());

        Response getUserResponse = apiMethods.makeGetRequest(Endpoints.USER + "/" + userId, token, cookie);
        String getUserResponseString = responseHelper.getResponseBody(getUserResponse);

        assertEquals(200, getUserResponse.code());
        assertEquals("NEWFNAME", responseHelper.getFieldValue(getUserResponseString, "firstName"));
        assertEquals("NEWLNAME", responseHelper.getFieldValue(getUserResponseString, "lastName"));
    }
}
