package ru.komarov.sd.bridge.api;

import ru.komarov.sd.bridge.models.Point;

public interface DrawingApi {
    int getDrawingAreaWidth();

    int getDrawingAreaHeight();

    void drawCircle(Point center, int radius);

    void drawLine(Point p1, Point p2);
}
