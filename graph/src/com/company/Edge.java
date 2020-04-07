package com.company;

public class Edge {

    private String source;
    private String destination;
    private int weight;

    public Edge(String src, String dest, int weight) {
        this.source = src;
        this.destination = dest;
        this.weight = weight;
    }

    public String getDestination() {
        return destination;
    }

    public String getSource() {
        return source;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "" + source + " " + destination + " " + weight;
    }
}
