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

    public static BufferedImage toSmall(BufferedImage sbi) {
        BufferedImage dbi = null;
        if(sbi != null) {
            dbi = new BufferedImage(SMALL_WIDTH, SMALL_HEIGHT, TYPE_INT_RGB);
            Graphics2D g = dbi.createGraphics();
            AffineTransform at = AffineTransform.getScaleInstance((SMALL_WIDTH / dbi.getWidth()), (SMALL_HEIGHT / dbi.getHeight()));
            g.drawRenderedImage(sbi, at);
        }
        return dbi;
    }

    public static BufferedImage toMedium(BufferedImage sbi) {
        BufferedImage dbi = null;
        if(sbi != null) {
            dbi = new BufferedImage(MEDIUM_WIDTH, MEDIUM_HEIGHT, TYPE_INT_RGB);
            Graphics2D g = dbi.createGraphics();
            AffineTransform at = AffineTransform.getScaleInstance((MEDIUM_WIDTH / dbi.getWidth()), (MEDIUM_HEIGHT / dbi.getHeight()));
            g.drawRenderedImage(sbi, at);
        }
        return dbi;
    }

    public static BufferedImage toLarge(BufferedImage sbi) {
        BufferedImage dbi = null;
        if(sbi != null) {
            dbi = new BufferedImage(LARGE_WIDTH, LARGE_HEIGHT, TYPE_INT_RGB);
            Graphics2D g = dbi.createGraphics();
            AffineTransform at = AffineTransform.getScaleInstance((LARGE_WIDTH / dbi.getWidth()), (LARGE_HEIGHT / dbi.getHeight()));
            g.drawRenderedImage(sbi, at);
        }
        return dbi;
    }

}
