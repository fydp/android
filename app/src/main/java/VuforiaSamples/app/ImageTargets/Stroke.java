package VuforiaSamples.app.ImageTargets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Stroke {
    private final String id;
    private final String colour;
    private final String drawingId;
    private final String userId;
    private final List<Point> points;

    public Stroke(String id, String colour, String drawingId, String userId) {
        this.id = id;
        this.colour = colour;
        this.drawingId = drawingId;
        this.userId = userId;
        this.points = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getColour() {
        return colour;
    }

    public String getDrawingId() {
        return drawingId;
    }

    public String getUserId() {
        return userId;
    }

    public void addPoint(Point p) {
        points.add(p);
    }

    public void sort() { Collections.sort(points); }

    public List<Point> getPoints() {
        return points;
    }

}
