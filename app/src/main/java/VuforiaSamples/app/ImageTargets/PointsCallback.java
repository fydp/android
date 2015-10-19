package VuforiaSamples.app.ImageTargets;

import java.util.List;

import models.Point;

public interface PointsCallback {
    void onPointsAvailable(List<Point> points);
}
