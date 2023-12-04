package edu.project4;

import edu.project4.renderers.Renderer;
import edu.project4.renderers.RendererUtils;
import edu.project4.renderers.SingleThreadRenderer;
import edu.project4.transformations.Sin;
import edu.project4.transformations.TransformationLibrary;
import edu.project4.transformations.Variation;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RenderTest {
    private static final int WIDTH = 10;
    private static final int HEIGHT = 10;
    private static final double DELTA = 0.01;
    private static final int SAMPLES = 1000;
    private static final int ITER_PER_SAMPLES = 100;
    private static final int MAX_COLOR = 255;

    @Test
    void testSingleThreadRender() {
        // given
        FractalImage canvas = FractalImage.create(WIDTH, HEIGHT);
        List<Variation> variations = TransformationLibrary.getVariations(new Sin());
        Renderer renderer = new SingleThreadRenderer();

        // when
        renderer.render(canvas, variations, 1, SAMPLES, (short) ITER_PER_SAMPLES);

        // then
        for (int x = 0; x < canvas.width(); ++x) {
            for (int y = 0; y < canvas.height(); ++y) {
                Pixel pixel = canvas.pixel(x, y);
                assertNotNull(pixel);
                assertTrue(pixel.r() >= 0 && pixel.r() <= MAX_COLOR);
                assertTrue(pixel.g() >= 0 && pixel.g() <= MAX_COLOR);
                assertTrue(pixel.b() >= 0 && pixel.b() <= MAX_COLOR);
            }
        }
    }

    @Test
    void testMultiThreadRender() {
        // given
        FractalImage canvas = FractalImage.create(WIDTH, HEIGHT);
        List<Variation> variations = TransformationLibrary.getVariations(new Sin());
        Renderer renderer = new SingleThreadRenderer();

        // when
        renderer.render(canvas, variations, 1, SAMPLES, (short) ITER_PER_SAMPLES);

        // then
        for (int x = 0; x < canvas.width(); ++x) {
            for (int y = 0; y < canvas.height(); ++y) {
                Pixel pixel = canvas.pixel(x, y);
                assertNotNull(pixel);
                assertTrue(pixel.r() >= 0 && pixel.r() <= MAX_COLOR);
                assertTrue(pixel.g() >= 0 && pixel.g() <= MAX_COLOR);
                assertTrue(pixel.b() >= 0 && pixel.b() <= MAX_COLOR);
            }
        }
    }

    @Test
    void testRotate() {
        // given
        Point point = new Point(1, 0);

        // when
        Point rotated = RendererUtils.rotate(point, Math.PI / 2);

        // then
        assertEquals(0, rotated.x(), DELTA);
        assertEquals(1, rotated.y(), DELTA);
    }
}
