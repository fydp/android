package models;

public class Point {
    private final String id;
    private final String colour;
    private final String drawingId;
    private final String userId;
    private final android.graphics.Point point;

    public Point(String id, String colour, String drawingId, String userId, int x, int y) {
        this.id = id;
        this.colour = colour;
        this.drawingId = drawingId;
        this.userId = userId;
        this.point = new android.graphics.Point(x, y);
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

    public android.graphics.Point getPoint() {
        return point;
    }
}