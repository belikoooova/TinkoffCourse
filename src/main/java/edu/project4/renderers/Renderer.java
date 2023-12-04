package edu.project4.renderers;

import edu.project4.FractalImage;
import edu.project4.transformations.Variation;
import java.util.List;

public interface Renderer {
    FractalImage render(
        FractalImage canvas,
        List<Variation> variations,
        int symmetry,
        int samples,
        short iterPerSample
    );
}
