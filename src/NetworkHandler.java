import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NetworkHandler {

    private final String BASE_URL = "https://raw.githubusercontent.com/stormofice/aud-custom-tests/main/uebungX/YCustomTest.java";
    private final String EXERCISES_URL = "https://raw.githubusercontent.com/stormofice/aud-custom-tests/main/exercises.cti";

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

    public File downloadExercise(String exercise) {
        String[] split = exercise.split("/");
        File temp = new File(System.getProperty("java.io.tmpdir") + File.separator + split[1] + "CustomTest.java");
        try {
            if (!temp.exists()) {
                temp.createNewFile();
            }
            FileWriter fw = new FileWriter(temp, false);
            String content = getWebsiteContentByURL(BASE_URL.replace("X", split[0]).replace("Y", split[1]));
            fw.write(content);
            fw.close();
            System.out.println("Successfully downloaded exercise " + temp.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return temp;
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
