package com.api.example;

import com.api.example.api.ApiMethods;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import com.api.example.helpers.*;
import static org.junit.jupiter.api.Assertions.fail;

public class TestBase {
    protected final ApiMethods apiMethods = new ApiMethods();
    protected final ResponseHelper responseHelper = new ResponseHelper();
    protected final DataHelper dataHelper = new DataHelper();
}
