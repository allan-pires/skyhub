package com.skyhub.image;

import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by allan on 09/04/17.
 */
public class ImageRepository {

    private String IMG_FORMAT = "jpg";
    private String GROUP = "scaled.img";
    private String HOST = "localhost";
    private String DB_NAME = "skyhub";
    private int PORT = 27017;


    private DB getConnection() {
        MongoClient mongoClient = null;
        try {
            mongoClient = new MongoClient(HOST , PORT);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return mongoClient.getDB(DB_NAME);
    }

    public byte[] getImage(String name) {
        GridFS gfsPhoto = new GridFS(getConnection(), GROUP);
        GridFSDBFile imageForOutput = gfsPhoto.findOne(name);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            imageForOutput.writeTo(out);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return out.toByteArray();
    }

    public List<String> getAllImagesNames() {
        GridFS gfsPhoto = new GridFS(getConnection(), GROUP);
        DBCursor cursor = gfsPhoto.getFileList();
        List<String> names = new ArrayList<>();

        while (cursor.hasNext()) {
            names.add(cursor.next().get("filename").toString());
        }

        return names;
    }

    public void save(BufferedImage image, String name) throws IOException {
        byte[] binaryImage = imageToBinary(image);
        GridFS fs = new GridFS(getConnection(), GROUP);
        GridFSInputFile in = fs.createFile(binaryImage);
        in.setFilename(name);
        in.save();
    }

    private byte[] imageToBinary(BufferedImage bufferedImage) throws IOException {
        byte[] binaryImage = "".getBytes();

        try{
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, IMG_FORMAT, out);
            out.flush();

            binaryImage = out.toByteArray();
            out.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return binaryImage;
    }

}
