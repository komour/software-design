package ru.komarov.sd.bridge.graph;

import ru.komarov.sd.bridge.api.DrawingApi;
import ru.komarov.sd.bridge.models.Point;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Graph {
    /**
     * Bridge to drawing api
     */
    protected DrawingApi drawingApi;

    public static final int CIRCLE_ANGLE = 360;
    public static final double ANGLE_TO_RADIAN_K = 0.0174532925;
    private static final int VERTEX_RADIUS = 5;
    private static final double SPACE_K = 0.9;

    protected List<List<Integer>> graph;
    protected Point drawingAreaCenter;
    protected Map<Integer, Point> vertexCentersById = new HashMap<>();

    public Graph(List<List<Integer>> graph, DrawingApi drawingApi) {
        this.drawingApi = drawingApi;
        this.graph = graph;
        drawingAreaCenter = new Point(drawingApi.getDrawingAreaWidth() / 2, drawingApi.getDrawingAreaHeight() / 2);
    }

    protected double getOneVertexArcAngle() {
        return CIRCLE_ANGLE / (double) this.graph.size();
    }

    protected int getPlaceDistance() {
        return (int) Math.min(Math.abs(drawingAreaCenter.x - drawingApi.getDrawingAreaWidth() * SPACE_K),
                Math.abs(drawingAreaCenter.y - drawingApi.getDrawingAreaHeight() * SPACE_K));
    }

    public void drawGraph() {
        double angleStep = getOneVertexArcAngle();
        int placeDistance = getPlaceDistance();
        double curAngle = 0;
        for (int i = 0; i < graph.size(); i++, curAngle += angleStep) {
            int x = (int) (drawingAreaCenter.x + placeDistance * Math.cos(curAngle * ANGLE_TO_RADIAN_K));
            int y = (int) (drawingAreaCenter.y - placeDistance * Math.sin(curAngle * ANGLE_TO_RADIAN_K));
            Point vertexCenter = new Point(x, y);
            vertexCentersById.put(i, vertexCenter);
            drawingApi.drawCircle(vertexCenter, VERTEX_RADIUS);
        }
    }
}
