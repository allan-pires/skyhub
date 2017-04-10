package com.skyhub.image;

import net.coobird.thumbnailator.Thumbnails;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Scale function found in: https://stackoverflow.com/questions/9417356/bufferedimage-resize
 */

public class ImageScale {

    private static int SMALL_WIDTH = 320;
    private static int SMALL_HEIGHT = 240;
    private static int MEDIUM_WIDTH = 384;
    private static int MEDIUM_HEIGHT = 288;
    private static int LARGE_WIDTH = 640;
    private static int LARGE_HEIGHT = 480;

    private BufferedImage scale(BufferedImage img, int width, int height){
        BufferedImage image = new BufferedImage(width, height, img.getType());

        try {
            image = Thumbnails.of(img).size(width, height).asBufferedImage();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }

    public BufferedImage toSmall(BufferedImage sbi) {
        return scale(sbi, SMALL_WIDTH, SMALL_HEIGHT);
    }

    public BufferedImage toMedium(BufferedImage sbi) {
        return scale(sbi, MEDIUM_WIDTH, MEDIUM_HEIGHT);
    }

    public BufferedImage toLarge(BufferedImage sbi) {
        return scale(sbi, LARGE_WIDTH, LARGE_HEIGHT);
    }
}
