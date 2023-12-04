package edu.project4;

import edu.project4.images.ImageUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.File;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ImageTest {
    private static final int WIDTH = 10;
    private static final int HEIGHT = 10;
    private static final int R = 100;
    private static final int G = 150;
    private static final int B = 200;
    private static final double NORMAL = 1.0;
    private static final int HIT_COUNT = 10;

    @Test
    void testSavePng(@TempDir Path tempDir) {
        // given
        FractalImage canvas = createTestImage();
        String filename = tempDir.resolve("test.png").toString();

        // when
        ImageUtils.savePng(canvas, filename);

        // then
        File outputFile = new File(filename);
        assertTrue(outputFile.exists());
    }

    private FractalImage createTestImage() {
        FractalImage image = FractalImage.create(WIDTH, HEIGHT);

        for (int x = 0; x < WIDTH; ++x) {
            for (int y = 0; y < HEIGHT; ++y) {
                image.setPixel(new Pixel(R, G, B, NORMAL, HIT_COUNT), x, y);
            }
        }
        return image;
    }
}
