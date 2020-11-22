package ru.komarov.sd.bridge.api;

import ru.komarov.sd.bridge.api.model.Circle;
import ru.komarov.sd.bridge.api.model.Line;
import ru.komarov.sd.bridge.api.model.Point;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class JavaFxDrawingApi extends Application implements DrawingApi {
    public static int DEFAULT_AREA_WIDTH = 600;
    public static int DEFAULT_AREA_HEIGHT = 600;

    private static int drawingAreaWidth;
    private static int drawingAreaHeight;

    private static final List<Circle> circlesToDraw = new ArrayList<>();
    private static final List<Line> linesToDraw = new ArrayList<>();

    public JavaFxDrawingApi() {
        super();
        drawingAreaWidth = DEFAULT_AREA_WIDTH;
        drawingAreaHeight = DEFAULT_AREA_HEIGHT;
    }

    public static void dumpState() {
        circlesToDraw.clear();
        linesToDraw.clear();
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("JavaFX Graph visualizer");
        Group root = new Group();
        Canvas canvas = new Canvas(drawingAreaWidth, drawingAreaHeight);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        for (int i = 0; i < circlesToDraw.size(); ++i) {
            Circle circle = circlesToDraw.get(i);
            Point leftCorner = circle.getRectangleLeftCorner();
            int radius = circle.getRadius();
            gc.setFill(Color.GREEN);
            gc.fillOval(leftCorner.x, leftCorner.y, radius * 2, radius * 2);
            gc.setFill(Color.WHITE);
            gc.fillText(i + 1 + "", leftCorner.x + radius, leftCorner.y + radius);
        }
        gc.setFill(Color.BLACK);
        linesToDraw.forEach(line -> {
            Point p1, p2;
            p1 = line.getP1();
            p2 = line.getP2();
            gc.strokeLine(p1.x, p1.y, p2.x, p2.y);
        });
        root.getChildren().add(canvas);
        stage.setScene(new Scene(root));
        stage.show();
    }

    @Override
    public int getDrawingAreaWidth() {
        return drawingAreaWidth;
    }

    @Override
    public int getDrawingAreaHeight() {
        return drawingAreaHeight;
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
