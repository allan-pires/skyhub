package com.skyhub.image;

import com.skyhub.consumer.URLConsumer;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by allan on 09/04/17.
 */
@RestController
public class ImageController {

    private static String URL = "http://54.152.221.29/images.json";

    @RequestMapping("/list")
    public String list() {
        return "I DID IT";
    }

    public static void initializeImageRepository() throws IOException {
        JSONObject json = new JSONObject(URLConsumer.getText(URL));
        JSONArray imgPaths = json.getJSONArray("images");

        for (int i = 0; i < imgPaths.length(); i++){
            JSONObject path = (JSONObject) imgPaths.get(i);
            BufferedImage img = getImageFromJson(path);
            saveImageScales(img);
        }
    }

    private static BufferedImage getImageFromJson(JSONObject path){
        String url = (String) path.get("url");
        return URLConsumer.getImage(url);
    }

    private static void saveImageScales(BufferedImage image) throws IOException {
        ImageRepository.save(ImageScale.toLarge(image));
        ImageRepository.save(ImageScale.toMedium(image));
        ImageRepository.save(ImageScale.toSmall(image));
    }
}
