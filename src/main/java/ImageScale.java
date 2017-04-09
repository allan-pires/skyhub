import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

/**
 * Created by allan on 09/04/17.
 */
public class ImageScale {

    private static int SMALL_WIDTH = 320;
    private static int SMALL_HEIGHT = 240;
    private static int MEDIUM_WIDTH = 384;
    private static int MEDIUM_HEIGHT = 288;
    private static int LARGE_WIDTH = 640;
    private static int LARGE_HEIGHT = 480;

    private static BufferedImage scale(BufferedImage sbi, int width, int height){
        BufferedImage dbi = null;

        if(sbi != null) {
            dbi = new BufferedImage(width, height, TYPE_INT_RGB);
            Graphics2D g = dbi.createGraphics();
            AffineTransform at = AffineTransform.getScaleInstance((width / dbi.getWidth()), (height / dbi.getHeight()));
            g.drawRenderedImage(sbi, at);
        }

        return dbi;
    }

    public static BufferedImage toSmall(BufferedImage sbi) {
        return scale(sbi, SMALL_WIDTH, SMALL_HEIGHT);
    }

    public static BufferedImage toMedium(BufferedImage sbi) {
        return scale(sbi, MEDIUM_WIDTH, MEDIUM_HEIGHT);
    }

    public static BufferedImage toLarge(BufferedImage sbi) {
        return scale(sbi, LARGE_WIDTH, LARGE_HEIGHT);
    }
}
