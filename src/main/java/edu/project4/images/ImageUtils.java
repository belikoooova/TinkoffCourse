package edu.project4.images;

import edu.project4.FractalImage;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class ImageUtils {
    private static final int MAX = 255;
    private static final int A = 24;
    private static final int R = 16;
    private static final int G = 8;
    private static final int BLACK = MAX << A;

    @SneakyThrows
    public static void savePng(FractalImage canvas, String filename) {
        BufferedImage image = new BufferedImage(canvas.width(), canvas.height(), BufferedImage.TYPE_INT_ARGB);

        for (int x = 0; x < canvas.width(); ++x) {
            for (int y = 0; y < canvas.height(); ++y) {
                var pixel = canvas.pixel(x, y);
                if (pixel.hitCount() == 0) {
                    image.setRGB(x, y, BLACK);
                    continue;
                }
                int color = (MAX << A) | (pixel.r() << R) | (pixel.g() << G) | pixel.b();
                image.setRGB(x, y, color);
            }
        }

        File outputFile = new File(filename);
        ImageIO.write(image, "png", outputFile);
    }
}
