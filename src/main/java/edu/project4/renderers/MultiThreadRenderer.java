package edu.project4.renderers;

import edu.project4.FractalImage;
import edu.project4.Pixel;
import edu.project4.Point;
import edu.project4.transformations.Variation;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import static edu.project4.renderers.RendererUtils.MULTIPLIER;
import static edu.project4.renderers.RendererUtils.X_LIMIT;
import static edu.project4.renderers.RendererUtils.Y_LIMIT;
import static edu.project4.renderers.RendererUtils.getRandomPoint;
import static edu.project4.renderers.RendererUtils.rotate;
import static edu.project4.renderers.RendererUtils.transform;

public class MultiThreadRenderer implements Renderer {
    private static final ReadWriteLock LOCK = new ReentrantReadWriteLock();
    private static final int THREADS_AMOUNT = 2;
    private static final int TIMEOUT = 60;

    @Override
    public FractalImage render(
        FractalImage canvas,
        List<Variation> variations,
        int symmetry,
        int samples,
        short iterPerSample
    ) {
        ExecutorService service = Executors.newFixedThreadPool(THREADS_AMOUNT);
        for (int num = 0; num < samples; ++num) {
            Point point = getRandomPoint();
            service.submit(() -> {
                for (int i = 0; i < THREADS_AMOUNT; ++i) {
                    renderSample(point, variations, symmetry, canvas, iterPerSample / THREADS_AMOUNT);
                }
            });
        }

        service.shutdown();
        try {
            if (!service.awaitTermination(TIMEOUT, TimeUnit.SECONDS)) {
                service.shutdownNow();
            }
        } catch (InterruptedException e) {
            service.shutdownNow();
            Thread.currentThread().interrupt();
        }
        return canvas;
    }

    @SuppressWarnings("ParameterAssignment")
    private static void renderSample(
        Point point,
        List<Variation> variations,
        int symmetry,
        FractalImage canvas,
        int repeats
    ) {
        ThreadLocalRandom random = ThreadLocalRandom.current();

        for (int step = 0; step < repeats; ++step) {
            Variation variation = variations.get(random.nextInt(variations.size()));
            point = transform(point, variation);

            for (int s = 0; s < symmetry; ++s) {
                double theta = s * Math.PI * 2 / symmetry;
                var rotadedPoint = rotate(point, theta);

                int x = (int) (canvas.width() - (X_LIMIT - rotadedPoint.x()) / (X_LIMIT * MULTIPLIER) * canvas.width());
                int y =
                    (int) (canvas.height() - (Y_LIMIT - rotadedPoint.y()) / (Y_LIMIT * MULTIPLIER) * canvas.height());

                LOCK.readLock().lock();
                Pixel pixel = canvas.pixel(x, y);

                if (pixel == null) {
                    LOCK.readLock().unlock();
                    continue;
                }

                if (pixel.hitCount() == 0) {
                    pixel = new Pixel(variation.red(), variation.green(), variation.blue(), 0, 1);
                } else {
                    pixel = new Pixel(
                        (variation.red() + pixel.r()) / MULTIPLIER,
                        (variation.green() + pixel.g()) / MULTIPLIER,
                        (variation.blue() + pixel.b()) / MULTIPLIER,
                        pixel.normal(),
                        pixel.hitCount() + 1
                    );
                }
                LOCK.readLock().unlock();
                LOCK.writeLock().lock();
                canvas.setPixel(pixel, x, y);
                LOCK.writeLock().unlock();
            }
        }
    }
}
