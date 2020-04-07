# graph-example

## Description
First of all, read graph datas in order to generate the graph. In the graph, there will be edges between connected people and edges will be weighted with respect to relationship between two people. Give weights to types of relationships such as CloseFriend = 1, Friend = 2 and Acquaintance = 3.

## Functionality
- Firstly, implement a function "isConnected(v1, v2)" that looks the paths from one person to another person with given two people as vertices. If there is a possible path from v1 to v2 it returns true, otherwise it returns false. Use Depth First Search (DFS) algorithm. 

- Second, implement a function "shortestPath(v1, v2)" that finds the shortest path from v1 to v2 and return the vertices between v1 and v2 in a LinkedList. For your shortest path algorithm, you must use Breadth-First Search (BFS). 

- Third, you will implement a function "suggestFriend(v1)" that gives you a list of closest friends from v1 to all vertices checking the weights. Use Dijkstra's algorithm to compute the closest friends of v1. Then, return the sugessted friends as a LinkedList.
