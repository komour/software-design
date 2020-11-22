package ru.komarov.sd.bridge.graph;

import ru.komarov.sd.bridge.api.DrawingApi;

import java.util.List;

public class MatrixGraph extends CircleStyleGraph {
    public MatrixGraph(List<Integer>[] graph, DrawingApi drawingApi) {
        super(graph, drawingApi);
    }

    @Override
    public void drawGraph() {
        super.drawGraph();
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].size(); j++) {
                if (graph[i].get(j) == 1)
                    drawingApi.drawLine(vertexNumToCenterPoint.get(i), vertexNumToCenterPoint.get(j));
            }
        }
    }
}
