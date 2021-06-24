import okhttp3.*;

import java.io.IOException;

public class TestBase {
    private OkHttpClient client = new OkHttpClient();
    public final ApiMethods apiMethods = new ApiMethods();
}
