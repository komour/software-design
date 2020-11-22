package ru.komarov.sd.bridge.api.model;

public class Circle {
    private Point rectangleLeftCorner;
    private int radius;

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
