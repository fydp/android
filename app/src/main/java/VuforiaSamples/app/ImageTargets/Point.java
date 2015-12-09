package VuforiaSamples.app.ImageTargets;

public class Point implements Comparable<Point>{
    private final String id;
    private final android.graphics.Point point;
    private final String strokeId;
    private int index;

    public Point(String id, String strokeId, int x, int y) {
        this.id = id;
        this.strokeId = strokeId;
        this.point = new android.graphics.Point(x, y);
    }

    public String getId() {
        return id;
    }

    public String getStrokeId() {
        return strokeId;
    }

    public android.graphics.Point getPoint() {
        return point;
    }

    public int getX() {
        return point.x;
    }

    public int getY() {
        return point.y;
    }

    public void setIndex(int i) {index = i;}


    @Override
    public int compareTo(Point another) {
        return this.index - another.index;
    }
}

