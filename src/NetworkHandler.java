import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NetworkHandler {

    private final String BASE_URL = "[]";
    private final String EXERCISES_URL = "[]";

    private List<String> exercises;

    public NetworkHandler() {
        exercises = new ArrayList<>();
    }

    public List<String> refresh() {
        String content = getWebsiteContentByURL(EXERCISES_URL);
        if (content == null)
            return new ArrayList<>();
        String[] split = content.split("\n");
        exercises = Arrays.asList(split);
        return exercises;
    }

    private String getWebsiteContentByURL(String url) {

        try {
            URL directURL = new URL(url);

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(directURL.toURI())
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());

            return response.body();

        } catch (Exception e) {
            System.err.println("error at " + url);
            e.printStackTrace();
        }
        return null;
    }

}
