package org.example.solutions;

import org.example.utils.MyFileReader;
import org.jgrapht.Graph;
import org.jgrapht.alg.StoerWagnerMinimumCut;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultUndirectedGraph;

import java.util.*;

public class Day25 implements ISolution{
    @Override
    public long solutionPart1() {
        List<String> lines = MyFileReader.ReadFromFile("_Resources/Day25.txt");
        Map<String, Set<String>> edges = getEdges(getConnections(lines));

        Set<String> added = new HashSet<>();
        Graph<String, DefaultEdge> graph = getGraph(edges, added);

        var cut = new StoerWagnerMinimumCut<>(graph);
        Set<String> left = cut.minCut();
        added.removeAll(left);

        return (long) left.size() * added.size();
    }

    private Set<Set<String>> getConnections(List<String> lines){
        Set<Set<String>> connections = new HashSet<>();
        for (String line : lines) {
            String[] parts = line.split(": ");
            String left = parts[0];
            for (String s : parts[1].split(" ")) {
                connections.add(Set.of(left, s));
            }
        }
        return connections;
    }

    private Map<String, Set<String>> getEdges(Set<Set<String>> connections){
        Map<String, Set<String>> edges = new HashMap<>();
        for (Set<String> connection : connections) {
            var i = connection.iterator();
            var next = i.next();
            var next1 = i.next();
            edges.computeIfAbsent(next, it -> new HashSet<>()).add(next1);
            edges.computeIfAbsent(next1, it -> new HashSet<>()).add(next);
        }
        return edges;
    }

    private Graph<String, DefaultEdge> getGraph(Map<String, Set<String>> edges, Set<String> added){
        Graph<String, DefaultEdge> graph = new DefaultUndirectedGraph<>(DefaultEdge.class);

        for (Map.Entry<String, Set<String>> entry : edges.entrySet()) {
            if (added.add(entry.getKey())) {
                graph.addVertex(entry.getKey());
            }
            for (String s : entry.getValue()) {
                if (added.add(s)) {
                    graph.addVertex(s);
                }
                graph.addEdge(entry.getKey(), s);
            }
        }
        return graph;
    }

    @Override
    public long solutionPart2() {
        return 0;
    }


}
