package com.api.example;

import com.api.example.api.ApiParams;
import okhttp3.FormBody;
import okhttp3.RequestBody;

public class UserBuilder {
    private final FormBody.Builder userParamsBuilder = new FormBody.Builder();

    public UserBuilder withUsername(String username) {
        userParamsBuilder.add(ApiParams.USERNAME, username);
        return this;
    }

    public UserBuilder withPassword(String password) {
        userParamsBuilder.add(ApiParams.PASSWORD, password);
        return this;
    }

    public UserBuilder withFirstName(String firstName) {
        userParamsBuilder.add(ApiParams.FIRST_NAME, firstName);
        return this;
    }

    public UserBuilder withLastName(String lastName) {
        userParamsBuilder.add(ApiParams.LAST_NAME, lastName);
        return this;
    }

    public UserBuilder withEmail(String email) {
        userParamsBuilder.add(ApiParams.EMAIL, email);
        return this;
    }

    public RequestBody build() {
        return userParamsBuilder.build();
    }
}
