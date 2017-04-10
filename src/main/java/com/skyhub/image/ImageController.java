package com.skyhub.image;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.skyhub.connection.URLConsumer;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

/**
 * Created by allan on 09/04/17.
 */
@RestController
public class ImageController {

    private static String IMAGES_JSON_URL = "http://54.152.221.29/images.json";
    private static String URL_TO_PRODUCE = "localhost:8080/images/";
    private static String IMG_PREFIX = "img";
    private static String SMALL_SUFFIX = "_small";
    private static String MEDIUM_SUFFIX = "_medium";
    private static String LARGE_SUFFIX = "_large";

    @Autowired
    ImageRepository repository;

    @Autowired
    ImageScale scale;

    @Autowired
    URLConsumer consumer;

    @RequestMapping(value = "/images/{name}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] showImage(@PathVariable String name) {
        return repository.getImage(name);
    }

    @RequestMapping(value = "/list")
    public String listImages() {
        return getPrettyJson(createImagesJSONObject());
    }

    private JSONObject createImagesJSONObject(){
        JSONObject obj = new JSONObject();
        JSONArray names = getImageNamesJSONArray();

        if (names.length() > 0){
            obj.append("images", names);
        }

        return obj;
    }

    private JSONArray getImageNamesJSONArray(){
        List<String> names = repository.getAllImagesNames();
        JSONArray namesArr = new JSONArray();

        for (String name : names){
            JSONObject img = new JSONObject();
            img.put("url", URL_TO_PRODUCE + name);
            namesArr.put(img);
        }

        return namesArr;
    }

    private String getPrettyJson(JSONObject obj){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(obj.toString());

        return gson.toJson(element);
    }

    public void saveImagesFromURL() throws IOException {
        JSONObject json = new JSONObject(consumer.getTextFromURL(IMAGES_JSON_URL));
        JSONArray imgPaths = json.getJSONArray("images");

        for (int i = 0; i < imgPaths.length(); i++){
            JSONObject path = (JSONObject) imgPaths.get(i);
            BufferedImage img = getImageFromJson(path);
            saveImageScales(img, IMG_PREFIX + i);
        }
    }

    private BufferedImage getImageFromJson(JSONObject path){
        String url = (String) path.get("url");
        return consumer.getImageFromURL(url);
    }

    private void saveImageScales(BufferedImage image, String name) throws IOException {
        repository.save(scale.toLarge(image), name + LARGE_SUFFIX);
        repository.save(scale.toMedium(image), name + MEDIUM_SUFFIX);
        repository.save(scale.toSmall(image), name + SMALL_SUFFIX);
    }
}
