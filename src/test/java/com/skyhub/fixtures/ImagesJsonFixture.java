package com.skyhub.fixtures;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by allan on 10/04/17.
 */
public class ImagesJsonFixture {
    private static String jsonText = "{\n" +
            "  \"images\": [\n" +
            "    {\n" +
            "      \"url\": \"images/img0.jpg\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"url\": \"images/img1.jpg\"\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    public static String defaultJsonText(){
        return jsonText;
    }

    public static JSONObject defaultJson() throws JSONException {
        return new JSONObject(jsonText);
    }

    public static List<String> defaultNameList() {
        List<String> imgs = new ArrayList<>();

        imgs.add("img0");
        imgs.add("img1");
        imgs.add("img2");

        return imgs;
    }
}
