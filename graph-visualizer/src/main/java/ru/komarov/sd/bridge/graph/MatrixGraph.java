package ru.komarov.sd.bridge.graph;

import ru.komarov.sd.bridge.api.DrawingApi;

import java.util.List;

public class MatrixGraph extends AbstractGraph {
    public MatrixGraph(List<List<Integer>> graph, DrawingApi drawingApi) {
        super(graph, drawingApi);
    }

    @Override
    public void drawGraph() {
        super.drawGraph();
        for (int i = 0; i < graph.size(); i++) {
            for (int j = 0; j < graph.get(i).size(); j++) {
                if (graph.get(i).get(j) == 1)
                    drawingApi.drawLine(vertexCentersById.get(i), vertexCentersById.get(j));
            }
        }
    }
}
