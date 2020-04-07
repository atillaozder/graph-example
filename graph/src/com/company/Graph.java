package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Graph {

    private Map<String, LinkedList<Edge>> list;
    private Map<String, Boolean> visited;

    public Graph(String fileName) throws FileNotFoundException {
        Scanner scn = new Scanner(new File(System.getProperty("user.dir") + "/resources/" + fileName + ".txt"));
        HashSet<String> set = new HashSet<>();
        while (scn.hasNext()) {
            String vertex = scn.next();
            if (!set.contains(vertex))
                set.add(vertex);
            scn.nextLine();
        }

        list = new HashMap<>();
        visited = new HashMap<>();
        for (String key : set) {
            list.put(key, new LinkedList<>());
            visited.put(key, false);
        }

        scn = new Scanner(new File(System.getProperty("user.dir") + "/resources/" + fileName + ".txt"));
        while (scn.hasNext()) {
            String source = scn.next();
            String destination = scn.next();
            String relation = scn.next();

            int weight;
            switch (relation) {
                case "CloseFriend":
                    weight = 1;
                    break;
                case "Friend":
                    weight = 2;
                    break;
                case "Acquaintance":
                    weight = 3;
                    break;
                default:
                    weight = 0;
                    break;
            }
            addEdge(source, destination, weight);
        }
    }

    private void addEdge(String src, String dest, int weight) {
        list.get(src).add(new Edge(src, dest, weight));
    }

    public boolean isConnected(String src, String dest) {
        DFS(src);
        if (!visited.get(dest))
            return false;
        return true;
    }

    private void DFS(String src) {
        visited.put(src, true);
        String next;
        Iterator<Edge> i = list.get(src).iterator();
        while (i.hasNext()) {
            next = i.next().getDestination();
            if (!visited.get(next))
                DFS(next);
        }
    }

    public LinkedList<String> shortestPath(String src, String dest) {
        LinkedList<String> path = new LinkedList<>();
        if (src.equals(dest) || !isConnected(src, dest))
            return path;

        resetVisited();
        path.add(src);

        Queue<String> queue = new LinkedList<>();
        HashMap<String, String> prev = new HashMap<>();
        for (String k : visited.keySet()) {
            prev.put(k, null);
        }

        queue.add(src);
        visited.put(src, true);

        while (!queue.isEmpty()) {
            String node = queue.poll();
            for (Edge e : list.get(node)) {
                String nei = e.getDestination();
                if (!visited.get(nei)) {
                    visited.put(nei, true);
                    prev.put(nei, node);
                    queue.add(nei);
                    if (nei.equals(dest))
                        break;
                }
            }
        }

        String current = dest;
        while (!prev.get(current).equals(src)) {
            current = prev.get(current);
            path.add(current);
        }

        path.add(dest);
        return path;
    }

    public Map<String, Integer> suggestFriend(String vertex) {
        resetVisited();
        HashMap<String, Integer> distance = new HashMap<>();

        for (String k : visited.keySet())
            distance.put(k, Integer.MAX_VALUE);
        distance.put(vertex, 0);

        PriorityQueue<String> queue = new PriorityQueue<>();
        queue.add(vertex);

        while (!queue.isEmpty()) {
            String v = queue.remove();
            visited.put(v, true);

            for (Edge edge : list.get(v)) {
                String neighbour = edge.getDestination();
                if (!visited.get(neighbour)) {
                    queue.add(neighbour);
                    if (edge.getWeight() + distance.get(v) < distance.get(neighbour)) {
                        distance.put(neighbour, edge.getWeight() + distance.get(v));
                    }
                }
            }
        }

        Map<String, Integer> sortedMap = sortByValues(distance);

        // If the list needs to contain only accessable friends -
        sortedMap.remove(vertex);
        for (String key: sortedMap.keySet()) {
            Integer value = sortedMap.get(key);
            if (value == Integer.MAX_VALUE)
                sortedMap.remove(key);
        }

        return sortedMap;
    }

    public void printGraph() {
        for (String key : list.keySet()) {
            System.out.print(key + " -> ");
            System.out.println(Arrays.toString(list.get(key).toArray()));
        }
    }

    private void resetVisited() {
        for (String key : visited.keySet())
            visited.put(key, false);
    }

    public <K extends Comparable, V extends Comparable> Map<K, V> sortByValues(Map<K, V> map) {
        List<Map.Entry<K, V>> entries = new LinkedList<>(map.entrySet());
        Collections.sort(entries, Comparator.comparing(Map.Entry::getValue));

        Map<K, V> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : entries) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }
}
