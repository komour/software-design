package ru.komarov.sd.bridge.models;

public class Circle {
    private final Point rectangleLeftCorner;
    private final int radius;

    public Circle(Point center, int radius) {
        this.rectangleLeftCorner = new Point(center.x - radius, center.y - radius);
        this.radius = radius;
    }

    public Point getRectangleLeftCorner() {
        return rectangleLeftCorner;
    }

    public int getRadius() {
        return radius;
    }
}
