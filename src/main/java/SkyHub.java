import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by allan on 09/04/17.
 */
public class SkyHub {

    private static String URL = "http://54.152.221.29/images.json";

    private static void initializeImageRepository() throws IOException {
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

    public static void main(String[] args) throws IOException {
        initializeImageRepository();
    }

}
