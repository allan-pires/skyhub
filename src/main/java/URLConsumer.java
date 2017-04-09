import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class URLConsumer {

    public static JSONObject consume(String uri) {
        try{
            String lines = readFromConnection(getConnection(uri));
            return new JSONObject(lines);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return new JSONObject();
    }

    private static String readFromConnection(HttpURLConnection connection) throws IOException {
        InputStream input = new BufferedInputStream(connection.getInputStream());
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));

        String line;
        StringBuilder builder = new StringBuilder();
        while ((line = reader.readLine()) != null){
            builder.append(line);
        }

        connection.disconnect();

        return builder.toString();
    }

    private static HttpURLConnection getConnection(String uri) throws IOException {
        URL url = new URL(uri);

        return (HttpURLConnection) url.openConnection();
    }

}
