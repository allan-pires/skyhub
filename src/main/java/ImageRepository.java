import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSInputFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by allan on 09/04/17.
 */
public class ImageRepository {

    private static DB getConnection(){
        MongoClient mongoClient = new MongoClient("localhost" , 27017);
        return mongoClient.getDB("skyhub");
    }

    private static byte[] imageToBinary(BufferedImage bufferedImage) throws IOException {
        byte[] binaryImage = "".getBytes();

        try{
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "jpg", out);
            out.flush();

            binaryImage = out.toByteArray();
            out.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return binaryImage;
    }

    public static void save(BufferedImage image) throws IOException {
        byte[] binaryImage = imageToBinary(image);
        GridFS fs = new GridFS(getConnection());
        GridFSInputFile in = fs.createFile(binaryImage);
        in.save();
    }

}
