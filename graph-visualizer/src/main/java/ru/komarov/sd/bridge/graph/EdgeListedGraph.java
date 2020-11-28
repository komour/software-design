package ru.komarov.sd.bridge.graph;

import ru.komarov.sd.bridge.api.DrawingApi;

import java.util.List;

public class EdgeListedGraph extends AbstractGraph {
    public EdgeListedGraph(List<Integer>[] graph, DrawingApi drawingApi) {
        super(graph, drawingApi);

    }

    @Override
    public void drawGraph() {
        super.drawGraph();
        for (int i = 0; i < vertexesIds.length; ++i) {
            for (Integer vert : vertexesIds[i]) {
                drawingApi.drawLine(vertexCentersById.get(i), vertexCentersById.get(vert));
            }
        }
    }
}
