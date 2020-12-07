package ru.komarov.sd.bridge.graph;

import ru.komarov.sd.bridge.api.DrawingApi;

import java.util.List;

public class EdgeListedGraph extends Graph {
    public EdgeListedGraph(List<List<Integer>> graph, DrawingApi drawingApi) {
        super(graph, drawingApi);

    }

    @Override
    public void drawGraph() {
        super.drawGraph();
        for (int i = 0; i < graph.size(); i++) {
            for (Integer vert : graph.get(i)) {
                drawingApi.drawLine(vertexCentersById.get(i), vertexCentersById.get(vert));
            }
        }
    }
}
