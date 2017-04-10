package com.skyhub.image;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by allan on 09/04/17.
 */
public class ImageScaleTest {

    private BufferedImage img;
    private ImageScale scale;

    @Before
    public void setup() throws IOException {
        scale = new ImageScale();
        String currentPath = System.getProperty("user.dir");
        img = ImageIO.read(new File(currentPath + "/src/test/resources/b737_5.jpg"));
    }

    @Test
    public void toSmall() throws Exception {
        BufferedImage smallImg = scale.toSmall(img);

        Assert.assertEquals(320, smallImg.getWidth());
        Assert.assertEquals(240, smallImg.getHeight());
    }

    @Test
    public void toMedium() throws Exception {
        BufferedImage smallImg = scale.toMedium(img);

        Assert.assertEquals(384, smallImg.getWidth());
        Assert.assertEquals(288, smallImg.getHeight());
    }

    @Test
    public void toLarge() throws Exception {
        BufferedImage smallImg = scale.toLarge(img);

        Assert.assertEquals(640, smallImg.getWidth());
        Assert.assertEquals(480, smallImg.getHeight());
    }

}