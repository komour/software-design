package ru.komarov.sd.bridge.graph;

import ru.komarov.sd.bridge.api.DrawingApi;
import ru.komarov.sd.bridge.api.model.Point;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class CircleStyleGraph extends Graph {
    public static final int CIRCLE_ANGLE = 360;
    public static final double ANGLE_TO_RADIAN_K = 0.0174532925;

    protected List<Integer>[] graph;
    protected Point drawingAreaCenter;
    protected Map<Integer, Point> vertexNumToCenterPoint = new HashMap<>();

    public CircleStyleGraph(List<Integer>[] graph, DrawingApi drawingApi) {
        super(drawingApi);
        this.graph = graph;
        drawingAreaCenter = new Point(drawingApi.getDrawingAreaWidth() / 2, drawingApi.getDrawingAreaHeight() / 2);
    }

    protected int getNumOfVertices() {
        return this.graph.length;
    }

    protected double getOneVertexArcAngle() {
        return CIRCLE_ANGLE / (double) this.graph.length;
    }

    protected int getPlaceDistance() {
        return (int) Math.min(Math.abs(drawingAreaCenter.x - drawingApi.getDrawingAreaWidth() * 0.8),
                Math.abs(drawingAreaCenter.y - drawingApi.getDrawingAreaHeight() * 0.8));
    }

    protected int getVertexRadius() {
        int placeDistance = getPlaceDistance();
        double placeRadius = 2 * Math.PI * placeDistance;
        return vertexRadiusBinarySearch(0, 0.05, placeRadius);
    }

    private int vertexRadiusBinarySearch(double l, double r, double placeRadius) {
        double mid;
        int vertexRadius = 0;
        while (r - l > 0.001) {
            mid = (l + r) / 2;
            vertexRadius = (int) Math.min(drawingApi.getDrawingAreaWidth() * mid, drawingApi.getDrawingAreaHeight() * mid);
            if (2 * Math.PI * vertexRadius * getNumOfVertices() > placeRadius * 0.90) {
                r = mid;
            } else {
                l = mid;
            }
        }
        return vertexRadius;
    }

    @Override
    public void drawGraph() {
        double angleStep = getOneVertexArcAngle();
        int vertexRadius = getVertexRadius();
        double curAngle = 0;
        int placeDistance = getPlaceDistance();
        for (int i = 0; i < graph.length; ++i) {
            int x = (int) (drawingAreaCenter.x + placeDistance * Math.cos(curAngle * ANGLE_TO_RADIAN_K));
            int y = (int) (drawingAreaCenter.y - placeDistance * Math.sin(curAngle * ANGLE_TO_RADIAN_K));
            Point vertexCenter = new Point(x, y);
            vertexNumToCenterPoint.put(i, vertexCenter);
            drawingApi.drawCircle(vertexCenter, vertexRadius);
            curAngle += angleStep;
        }
    }
}
