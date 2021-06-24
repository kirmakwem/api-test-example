import okhttp3.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Date;

import org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class UserTest extends TestBase {

    @Test
    public void getHello() {
        final String url = "https://playground.learnqa.ru/api/hello";
        Response response = apiMethods.makeGetRequest(url);
        assertEquals(200, response.code());
    }

    @Test
    public void createNewUser() throws IOException {
        final String url = "https://playground.learnqa.ru/api/user/";
        long currentTime = new Date().getTime();
        RequestBody formBody = new FormBody.Builder()
                .add("username", "kirilltest")
                .add("password", "Password123")
                .add("firstName", "Kirill")
                .add("lastName", "Test")
                .add("email", currentTime + "@some.com")
                .build();

        Response response = apiMethods.makePostRequest(url, formBody);
        assertEquals(200, response.code());
    }
}
