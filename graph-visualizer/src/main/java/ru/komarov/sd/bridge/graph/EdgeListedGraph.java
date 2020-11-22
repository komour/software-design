package ru.komarov.sd.bridge.graph;

import ru.komarov.sd.bridge.api.DrawingApi;

import java.util.List;

public class EdgeListedGraph extends CircleStyleGraph {
    public EdgeListedGraph(List<Integer>[] graph, DrawingApi drawingApi) {
        super(graph, drawingApi);

    }

    @Override
    public void drawGraph() {
        super.drawGraph();
        for (int i = 0; i < graph.length; ++i) {
            for (Integer vert : graph[i]) {
                drawingApi.drawLine(vertexNumToCenterPoint.get(i), vertexNumToCenterPoint.get(vert));
            }
        }
    }
}
