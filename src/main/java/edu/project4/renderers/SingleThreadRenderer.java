package edu.project4.renderers;

import edu.project4.FractalImage;
import edu.project4.Pixel;
import edu.project4.Point;
import edu.project4.transformations.Variation;
import java.util.List;
import static edu.project4.renderers.RendererUtils.MULTIPLIER;
import static edu.project4.renderers.RendererUtils.RANDOM;
import static edu.project4.renderers.RendererUtils.X_LIMIT;
import static edu.project4.renderers.RendererUtils.Y_LIMIT;
import static edu.project4.renderers.RendererUtils.getRandomPoint;
import static edu.project4.renderers.RendererUtils.transform;

public class SingleThreadRenderer implements Renderer {
    @Override
    public FractalImage render(
        FractalImage canvas,
        List<Variation> variations,
        int symmetry,
        int samples,
        short iterPerSample
    ) {
        for (int num = 0; num < samples; ++num) {
            Point point = getRandomPoint();

            for (int step = 0; step < iterPerSample; ++step) {
                Variation variation = variations.get(RANDOM.nextInt(variations.size()));
                point = transform(point, variation);

                for (int s = 0; s < symmetry; ++s) {
                    double theta = s * Math.PI * 2 / symmetry;
                    var rotadedPoint = rotate(point, theta);

                    int x = (int)
                        (canvas.width() - (X_LIMIT - rotadedPoint.x()) / (X_LIMIT * MULTIPLIER) * canvas.width());
                    int y = (int)
                            (canvas.height() - (Y_LIMIT - rotadedPoint.y()) / (Y_LIMIT * MULTIPLIER) * canvas.height());

                    Pixel pixel = canvas.pixel(x, y);

                    if (pixel == null) {
                        continue;
                    }

                    Pixel newPixel;
                    if (pixel.hitCount() == 0) {
                        newPixel =
                            new Pixel(variation.red(), variation.green(), variation.blue(), 0, 1);
                    } else {
                        newPixel = new Pixel(
                            (variation.red() + pixel.r()) / 2,
                            (variation.green() + pixel.g()) / 2,
                            (variation.blue() + pixel.b()) / 2,
                            pixel.normal(),
                            pixel.hitCount() + 1
                        );
                    }
                    canvas.setPixel(newPixel, x, y);
                }
            }
        }
        return canvas;
    }

    private static Point rotate(Point point, double theta) {
        double deltaX = point.x();
        double deltaY = point.y();

        double rotatedX = deltaX * Math.cos(theta) - deltaY * Math.sin(theta);
        double rotatedY = deltaX * Math.sin(theta) + deltaY * Math.cos(theta);

        return new Point(rotatedX, rotatedY);
    }
}
