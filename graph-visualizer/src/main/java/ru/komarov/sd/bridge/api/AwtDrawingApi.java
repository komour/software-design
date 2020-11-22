package ru.komarov.sd.bridge.api;

import ru.komarov.sd.bridge.api.model.Circle;
import ru.komarov.sd.bridge.api.model.Line;
import ru.komarov.sd.bridge.api.model.Point;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;

public class AwtDrawingApi extends Frame implements DrawingApi {
    private final int drawingAreaWidth;
    private final int drawingAreaHeight;

    public static int DEFAULT_AREA_WIDTH = 600;
    public static int DEFAULT_AREA_HEIGHT = 600;

    private final List<Circle> circlesToDraw = new ArrayList<>();
    private final List<Line> linesToDraw = new ArrayList<>();

    public AwtDrawingApi() {
        super();
        this.drawingAreaWidth = DEFAULT_AREA_WIDTH;
        this.drawingAreaHeight = DEFAULT_AREA_HEIGHT;
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D ga = (Graphics2D) g;
        for (int i = 0; i < circlesToDraw.size(); ++i) {
            Circle circle = circlesToDraw.get(i);
            Point leftCorner = circle.getRectangleLeftCorner();
            int radius = circle.getRadius();
            ga.setPaint(Color.GREEN);
            ga.fill(new Ellipse2D.Double(leftCorner.x, leftCorner.y, radius * 2, radius * 2));
            ga.setPaint(Color.BLACK);
            ga.drawString(i + 1 + "", leftCorner.x + radius, leftCorner.y + radius);
        }
        linesToDraw.forEach(line -> {
            Point p1;
            Point p2;
            p1 = line.getP1();
            p2 = line.getP2();
            ga.drawLine(p1.x, p1.y, p2.x, p2.y);
        });
    }

    @Override
    public int getDrawingAreaWidth() {
        return this.drawingAreaWidth;
    }

    @Override
    public int getDrawingAreaHeight() {
        return this.drawingAreaHeight;
    }

    @Override
    public void drawCircle(Point center, int radius) {
        circlesToDraw.add(new Circle(center, radius));
    }

    @Override
    public void drawLine(Point p1, Point p2) {
        linesToDraw.add(new Line(p1, p2));
    }
}
