package VuforiaSamples.app.ImageTargets;

import java.util.List;

public interface PointsCallback {
    void onPointsAvailable(List<Point> points, String colour);
    void onStrokesAvailable(List<Stroke> points);
    void onClear();
}
