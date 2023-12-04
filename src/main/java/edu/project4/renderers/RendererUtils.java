package edu.project4.renderers;

import edu.project4.FractalImage;
import edu.project4.Point;
import edu.project4.images.GammaCorrector;
import edu.project4.images.ImageProcessor;
import edu.project4.images.ImageUtils;
import edu.project4.transformations.Transformation;
import edu.project4.transformations.Variation;
import java.util.Random;
import lombok.experimental.UtilityClass;
import static edu.project4.transformations.TransformationLibrary.getVariations;

@UtilityClass
public class RendererUtils {
    public static final double X_LIMIT = 1.777;
    public static final double Y_LIMIT = 1;
    public static final int MULTIPLIER = 2;
    public static final Random RANDOM = new Random();
    private static final int SAMPLES_AMOUNT = 1250000;
    private static final short ITERATIONS_PER_SAMPLE = 100;
    private static final int WIDTH = 2 * 1920;
    private static final int HEIGHT = 2 * 1080;

    public static void run(Renderer renderer, Transformation function, String fileName) {
        FractalImage image = FractalImage.create(WIDTH, HEIGHT);
        renderer.render(
            image,
            getVariations(function),
            1,
            SAMPLES_AMOUNT,
            ITERATIONS_PER_SAMPLE
        );
        ImageProcessor processor = new GammaCorrector();
        processor.process(image);
        ImageUtils.savePng(image, fileName);
    }

    public static Point transform(Point point, Variation variation) {
        return variation.func().apply(variation.shift().apply(point));
    }

    public static Point rotate(Point point, double theta) {
        double rotatedX = point.x() * Math.cos(theta) - point.y() * Math.sin(theta);
        double rotatedY = point.x() * Math.sin(theta) + point.y() * Math.cos(theta);

        return new Point(rotatedX, rotatedY);
    }

    public static Point getRandomPoint() {
        double newX = RANDOM.nextDouble(-1 * X_LIMIT, X_LIMIT);
        double newY = RANDOM.nextDouble(-1 * Y_LIMIT, Y_LIMIT);
        return new Point(newX, newY);
    }
}
