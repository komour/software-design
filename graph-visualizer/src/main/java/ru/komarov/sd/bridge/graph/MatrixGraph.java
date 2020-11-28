package ru.komarov.sd.bridge.graph;

import ru.komarov.sd.bridge.api.DrawingApi;

import java.util.List;

public class MatrixGraph extends AbstractGraph {
    public MatrixGraph(List<Integer>[] graph, DrawingApi drawingApi) {
        super(graph, drawingApi);
    }

    @Override
    public void drawGraph() {
        super.drawGraph();
        for (int i = 0; i < vertexesIds.length; i++) {
            for (int j = 0; j < vertexesIds[i].size(); j++) {
                if (vertexesIds[i].get(j) == 1)
                    drawingApi.drawLine(vertexCentersById.get(i), vertexCentersById.get(j));
            }
        }
    }
}
