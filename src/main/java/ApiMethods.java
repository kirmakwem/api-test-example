import okhttp3.*;

import java.io.IOException;

public class  ApiMethods {
    private final OkHttpClient client = new OkHttpClient();

    public Response makeGetRequest(String url) {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try  {
            Response response = client.newCall(request).execute();
            System.out.println(response.body().string());
            return response;
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
            Response response = client.newCall(request).execute();
            System.out.println(response.body().string());
            return response;
        } catch (IOException e) {
            throw new RuntimeException("Не удалось совершить запрос");
        }
    }
}
