package com.company;

import java.io.FileNotFoundException;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        try {
            Graph g = new Graph("facebook-data");
            g.printGraph();

            // Checks Connectivity
            System.out.println();
            System.out.println("Connectivity");
            System.out.println(g.isConnected("Haydar" ,"AliBugra"));

            // Shortest Path
            System.out.println();
            System.out.println("Shortest Path:");
            System.out.println(Arrays.toString(g.shortestPath("Mahmut", "Olcay").toArray()));


            // DijkstraAlgorithm Suggested Friend
            System.out.println();
            System.out.println("Suggest Friend:");
            System.out.println(g.suggestFriend("Olcay").toString());

        } catch (FileNotFoundException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
    }
}
