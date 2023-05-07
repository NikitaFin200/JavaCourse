package ru.academits.findyurov.graph;

import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        int[][] graph = {
                {1, 2},
                {0, 2, 3},
                {0, 1},
                {1, 4},
                {3},
                {6},
                {5}
        };

        runDfs(graph);
        //System.out.println(Arrays.deepToString(graph));

        System.out.println();

        int[][] graph1 = {
                {0, 1, 0, 1, 0},
                {1, 0, 1, 0, 0},
                {0, 1, 0, 1, 1},
                {1, 0, 1, 0, 0},
                {0, 0, 1, 0, 0}
        };

        boolean[] visited = new boolean[graph1.length];

        for (int i = 0; i < graph1.length; i++) {
            if (!visited[i]) {
                bfs(graph1, visited, i);
            }
        }
    }

    public static void dfs(int[][] graph, int v, boolean[] visited) {
        visited[v] = true;
        System.out.print(v + " ");

        for (int i = 0; i < graph[v].length; i++) {
            int nextVertex = graph[v][i];

            if (!visited[nextVertex]) {
                dfs(graph, nextVertex, visited);
            }
        }
    }

    public static void runDfs(int[][] graph) {
        boolean[] visited = new boolean[graph.length];

        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                dfs(graph, i, visited);
            }
        }
    }

    public static void bfs(int[][] graph, boolean[] visited, int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int current = queue.remove();
            System.out.print(current + " ");

            for (int i = 0; i < graph.length; i++) {
                if (graph[current][i] == 1 && !visited[i]) {
                    queue.add(i);
                    visited[i] = true;
                }
            }
        }

        System.out.println();
    }
}

