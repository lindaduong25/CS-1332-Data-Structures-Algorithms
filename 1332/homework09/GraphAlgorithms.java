import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;

/**
 * Your implementation of various graph algorithms.
 *
 * @author Linda Duong
 * @version 1.0
 * @userid lduong8
 * @GTID 903568287
 *
 * Collaborators: LIST ALL COLLABORATORS YOU WORKED WITH HERE
 *
 * Resources: LIST ALL NON-COURSE RESOURCES YOU CONSULTED HERE
 */
public class GraphAlgorithms {

    /**
     * Performs a depth first search (dfs) on the input graph, starting at
     * the parameterized starting vertex.
     *
     * When exploring a vertex, explore in the order of neighbors returned by
     * the adjacency list. Failure to do so may cause you to lose points.
     *
     * NOTE: You MUST implement this method recursively, or else you will lose
     * all points for this method.
     *
     * You may import/use java.util.Set, java.util.List, and
     * any classes that implement the aforementioned interfaces, as long as they
     * are efficient.
     *
     * The only instance of java.util.Map that you may use is the
     * adjacency list from graph. DO NOT create new instances of Map
     * for DFS (storing the adjacency list in a variable is fine).
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @param <T>   the generic typing of the data
     * @param start the vertex to begin the dfs on
     * @param graph the graph to search through
     * @return list of vertices in visited order
     * @throws IllegalArgumentException if any input is null, or if start
     *                                  doesn't exist in the graph
     */
    public static <T> List<Vertex<T>> dfs(Vertex<T> start, Graph<T> graph) {
        // public wrapper
        if (start == null || graph == null || !graph.getVertices().contains(start)) {
            throw new IllegalArgumentException(
                "Start cannot be null, the graph cannot be null, and/or start does not exist in the graph.");
        }
        Set<Vertex<T>> visitedSet = new HashSet<>(); // keeps track of visited vertices
        List<Vertex<T>> visitedList = new ArrayList<>(); // this is the resulting list
        dfsHelp(start, graph, visitedSet, visitedList);
        return visitedList;
    }

    /**
     * Helper method for depth first search method above
     * @param <T> the generic typing of the data
     * @param current the current vertex in the graph
     * @param graph the graph to search through
     * @param visitedSet the set that keeps track of visited vertices
     * @param visitedList the list that will be returned in the order that vertices were visited
     */
    private static <T> void dfsHelp(Vertex<T> current, Graph<T> graph, Set<Vertex<T>> visitedSet,
        List<Vertex<T>> visitedList) {
        visitedSet.add(current); // mark current as visited (adding to visited to keep track)
        visitedList.add(current); // adding to visited list to return
        for (VertexDistance<T> w : graph.getAdjList().get(current)) {
            if (!visitedSet.contains(w.getVertex())) {
                dfsHelp(w.getVertex(), graph, visitedSet, visitedList);
            }
        }
    }

    /**
     * Finds the single-source shortest distance between the start vertex and
     * all vertices given a weighted graph (you may assume non-negative edge
     * weights).
     *
     * Return a map of the shortest distances such that the key of each entry
     * is a node in the graph and the value for the key is the shortest distance
     * to that node from start, or Integer.MAX_VALUE (representing
     * infinity) if no path exists.
     *
     * You may import/use java.util.PriorityQueue,
     * java.util.Map, and java.util.Set and any class that
     * implements the aforementioned interfaces, as long as your use of it
     * is efficient as possible.
     *
     * You should implement the version of Dijkstra's where you use two
     * termination conditions in conjunction.
     *
     * 1) Check if all of the vertices have been visited.
     * 2) Check if the PQ is empty yet.
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @param <T>   the generic typing of the data
     * @param start the vertex to begin the Dijkstra's on (source)
     * @param graph the graph we are applying Dijkstra's to
     * @return a map of the shortest distances from start to every
     * other node in the graph
     * @throws IllegalArgumentException if any input is null, or if start
     *                                  doesn't exist in the graph.
     */
    public static <T> Map<Vertex<T>, Integer> dijkstras(Vertex<T> start,
                                                        Graph<T> graph) {
        if (start == null || graph == null || !graph.getVertices().contains(start)) {
            throw new IllegalArgumentException(
                "Start cannot be null, the graph cannot be null, and/or start does not exist in the graph.");
        }
        Set<Vertex<T>> visitedSet = new HashSet<>();
        Map<Vertex<T>, Integer> distanceMap = new HashMap<>();
        Queue<VertexDistance<T>> priorityQueue = new PriorityQueue<>();
        for (Vertex<T> v : graph.getVertices()) {
            distanceMap.put(v, Integer.MAX_VALUE);
        }
        priorityQueue.add(new VertexDistance<T>(start, 0));
        while (!priorityQueue.isEmpty() && visitedSet.size() != graph.getVertices().size()) {
            VertexDistance<T> current = priorityQueue.remove(); // v, cumulativePathCost) = dequeue from priority queue
            if (!visitedSet.contains(current.getVertex())) {
                visitedSet.add(current.getVertex());
                int d = current.getDistance();
                distanceMap.put(current.getVertex(), d);
                for (VertexDistance<T> w : graph.getAdjList().get(current.getVertex())) {
                    int d2 = w.getDistance();
                    int newPathDist = d + d2;
                    // if w is not visited AND the new path is cheaper than previous paths**:
                    if (!visitedSet.contains(w.getVertex()) && newPathDist < distanceMap.get(w.getVertex())) {
                        distanceMap.put(w.getVertex(), newPathDist); // map.put(key=u, value=(cumulativePathCost + c))
                        priorityQueue.add(new VertexDistance<T>(w.getVertex(), newPathDist));
                    }
                }
            }
        }
        return distanceMap;
    }

    /**
     * Runs Prim's algorithm on the given graph and returns the Minimum
     * Spanning Tree (MST) in the form of a set of Edges. If the graph is
     * disconnected and therefore no valid MST exists, return null.
     *
     * You may assume that the passed in graph is undirected. In this framework,
     * this means that if (u, v, 3) is in the graph, then the opposite edge
     * (v, u, 3) will also be in the graph, though as a separate Edge object.
     *
     * The returned set of edges should form an undirected graph. This means
     * that every time you add an edge to your return set, you should add the
     * reverse edge to the set as well. This is for testing purposes. This
     * reverse edge does not need to be the one from the graph itself; you can
     * just make a new edge object representing the reverse edge.
     *
     * You may assume that there will only be one valid MST that can be formed.
     *
     * You should NOT allow self-loops or parallel edges in the MST.
     *
     * You may import/use java.util.PriorityQueue, java.util.Set, and any
     * class that implements the aforementioned interface.
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * The only instance of java.util.Map that you may use is the adjacency
     * list from graph. DO NOT create new instances of Map for this method
     * (storing the adjacency list in a variable is fine).
     *
     * @param <T>   the generic typing of the data
     * @param start the vertex to begin Prims on
     * @param graph the graph we are applying Prims to
     * @return the MST of the graph or null if there is no valid MST
     * @throws IllegalArgumentException if any input is null, or if start
     *                                  doesn't exist in the graph
     */
    public static <T> Set<Edge<T>> prims(Vertex<T> start, Graph<T> graph) {
        if (start == null || graph == null || !graph.getVertices().contains(start)) {
            throw new IllegalArgumentException(
                "Start cannot be null, the graph cannot be null, and/or start does not exist in the graph.");
        }
        Set<Vertex<T>> visitedSet = new HashSet<>(); // help to keep track of what vertices have been visited
        Set<Edge<T>> mstSet = new HashSet<>(); // resulting MST set
        Queue<Edge<T>> priorityQueue = new PriorityQueue<>();
        // enqueue all edges adjacent to start to the priority queue
        for (VertexDistance<T> v : graph.getAdjList().get(start)) {
            priorityQueue.add(new Edge<>(start, v.getVertex(), v.getDistance()));
        }
        visitedSet.add(start); // mark s as visited
        while (!priorityQueue.isEmpty() && visitedSet.size() != graph.getVertices().size()) {
            Edge<T> current = priorityQueue.remove(); // current edge
            if (!visitedSet.contains(current.getV())) {
                visitedSet.add(current.getV());
                // since graph is undirected we have to do (U, V, C) & (V, U, C)
                mstSet.add(current);
                // same as mstSet.add(new Edge<T>(current.getU(), current.getV(), current.getWeight()));
                mstSet.add(new Edge<T>(current.getV(), current.getU(), current.getWeight()));
                // enqueue all of v’s adjacent edges
                for (VertexDistance<T> v : graph.getAdjList().get(current.getV())) {
                    if (!visitedSet.contains(v.getVertex())) {
                        priorityQueue.add(new Edge<T>(current.getV(), v.getVertex(), v.getDistance()));
                    }
                }
            }
        }
        // make sure mst has correct number** of adjacent edges before returning
        // graph is undirected so |V| - 1 & divide size by 2 since we added reversed edges
        if (mstSet.size() / 2 == graph.getVertices().size() - 1) {
            return mstSet;
        }
        // graph is disconnected and therefore no valid MST exists, return null
        return null;
    }
}
