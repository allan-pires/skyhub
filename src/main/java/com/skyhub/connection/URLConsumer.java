package com.skyhub.connection;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by allan on 09/04/17.
 */
public class URLConsumer {

    public static BufferedImage getImage(String uri) {
        BufferedImage image = null;

        try{
            URL url = new URL(uri);
            image = ImageIO.read(url);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return image;
    }

    public static String getText(String uri) {
        String text = "";

        try{
            text = readFromConnection(getConnection(uri));
            return text;
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return text;
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
