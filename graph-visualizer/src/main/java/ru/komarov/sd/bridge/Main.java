package ru.komarov.sd.bridge;

import javafx.application.Application;
import ru.komarov.sd.bridge.api.AwtDrawingApi;
import ru.komarov.sd.bridge.api.DrawingApi;
import ru.komarov.sd.bridge.api.JavaFxDrawingApi;
import ru.komarov.sd.bridge.graph.EdgeListedGraph;
import ru.komarov.sd.bridge.graph.Graph;
import ru.komarov.sd.bridge.graph.MatrixGraph;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        print usage and read data
        System.out.println("Usage: java Drawer <library> <storage_type>");
        System.out.println("library: `awt` or `javafx`");
        System.out.println("storage_type: `edge-list` or `matrix`");
        System.out.println("edge-list usage: <n - amount of vertices> <m - amount of edges>, then m lines describing edges");
        System.out.println("matrix usage: <n - matrix dimension>, then n lines describing matrix");
        System.out.println("Current configuration: library `" + args[0] + "`, storage_type: `" + args[1] + "`");
        if (args.length != 2) {
            System.out.println("Wrong arguments: <library> <storage_type> expected");
        } else {
            String library = args[0];
            String storageType = args[1];
            if (!library.equals("awt") && !library.equals("javafx")) {
                System.out.println("Wrong arguments: `awt` or `javafx` library expected");
                return;
            }
            DrawingApi api = library.equals("awt") ? new AwtDrawingApi() : new JavaFxDrawingApi();

            if (!storageType.equals("edge-list") && !storageType.equals("matrix")) {
                System.out.println("Wrong arguments: `edge-list` or `matrix` storage_type expected");
                return;
            }
            List<List<Integer>> g;
            if (storageType.equals("edge-list")) {
                g = readEdgeListedGraph();
            } else {
                g = readMatrixGraph();
            }
            Graph graph = storageType.equals("edge-list") ? new EdgeListedGraph(g, api) : new MatrixGraph(g, api);

//            draw graph
            if (library.equals("awt")) {
                graph.drawGraph();
                AwtDrawingApi frameApi = (AwtDrawingApi) api;
                frameApi.addWindowListener(new WindowAdapter() {
                    public void windowClosing(WindowEvent we) {
                        System.exit(0);
                    }
                });
                frameApi.setSize(frameApi.getDrawingAreaWidth(), frameApi.getDrawingAreaHeight());
                frameApi.setVisible(true);
                frameApi.setTitle("AWT Graph visualizer");
            } else {
                JavaFxDrawingApi.dumpState();
                graph.drawGraph();
                Application.launch(JavaFxDrawingApi.class);
            }
        }
    }

    private static List<List<Integer>> readEdgeListedGraph() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        List<List<Integer>> g = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            g.add(new ArrayList<>());
        }
        for (int i = 0; i < m; ++i) {
            int u, v;
            u = in.nextInt();
            v = in.nextInt();
            g.get(--u).add(--v);
        }
        return g;
    }

    private static List<List<Integer>> readMatrixGraph() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        List<List<Integer>> g = new ArrayList<>(n);
        for (int i = 0; i < n; ++i) {
            g.add(new ArrayList<>());
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                g.get(i).add(in.nextInt());
            }
        }
        return g;
    }
}
