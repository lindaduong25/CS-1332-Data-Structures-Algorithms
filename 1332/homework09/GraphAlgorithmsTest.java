import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;


/**
 * Passing these tests doesn't guarantee any grade on the assignment.
 *
 * Some JUnit versions seem to have a problem with assertThrows.
 * I tested my code successfully using JUnit 4.13. If you cannot
 * get it to work for your version, remove the assertThrows lines
 * and you should be fine.
 *
 * @author Prakhar Mittal
 * @version 1.0
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GraphAlgorithmsTest {

    private static final int TIMEOUT = 200;

    @Test(timeout = TIMEOUT)
    public void t01_Exceptions() {
        assertThrows(IllegalArgumentException.class, () -> {
            GraphAlgorithms.dfs(null, setupGraph(1));
        });

        assertThrows(IllegalArgumentException.class, () -> {
            GraphAlgorithms.dfs(new Vertex<>("A"), null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            GraphAlgorithms.dfs(new Vertex<>("Z"), setupGraph(1));
        });

        assertThrows(IllegalArgumentException.class, () -> {
            GraphAlgorithms.dijkstras(null, setupGraph(1));
        });

        assertThrows(IllegalArgumentException.class, () -> {
            GraphAlgorithms.dijkstras(new Vertex<>("A"), null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            GraphAlgorithms.dijkstras(new Vertex<>("Z"), setupGraph(1));
        });

        assertThrows(IllegalArgumentException.class, () -> {
            GraphAlgorithms.prims(null, setupGraph(1));
        });

        assertThrows(IllegalArgumentException.class, () -> {
            GraphAlgorithms.prims(new Vertex<>("A"), null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            GraphAlgorithms.prims(new Vertex<>("Z"), setupGraph(1));
        });
    }

    @Test(timeout = TIMEOUT)
    public void t02_DFS_SingleVertex() {
        assertEquals(makeVertexList(new String[] {"A"}),
                GraphAlgorithms.dfs(new Vertex<>("A"), setupGraph(1)));
    }

    @Test(timeout = TIMEOUT)
    public void t03_DFS_DirectedRandom() {
        assertEquals(makeVertexList(new String[] {"A", "B", "C", "D", "E", "F", "G", "H", "I"}),
                GraphAlgorithms.dfs(new Vertex<>("A"), setupGraph(2)));
    }

    @Test(timeout = TIMEOUT)
    public void t04_DFS_DirectedBranched() {
        assertEquals(makeVertexList(new String[] {"A", "B", "D", "E", "F", "G", "C"}),
                GraphAlgorithms.dfs(new Vertex<>("A"), setupGraph(3)));
    }

    @Test(timeout = TIMEOUT)
    public void t05_DFS_DirectedComplete() {
        assertEquals(makeVertexList(new String[] {"A", "B", "C", "D", "E"}),
                GraphAlgorithms.dfs(new Vertex<>("A"), setupGraph(4)));
    }

    @Test(timeout = TIMEOUT)
    public void t06_DFS_DirectedInescapable() {
        assertEquals(makeVertexList(new String[] {"A"}),
                GraphAlgorithms.dfs(new Vertex<>("A"), setupGraph(5)));
    }

    @Test(timeout = TIMEOUT)
    public void t07_DFS_DirectedUnreachable() {
        assertEquals(makeVertexList(new String[] {"A", "B", "E"}),
                GraphAlgorithms.dfs(new Vertex<>("A"), setupGraph(6)));
    }

    @Test(timeout = TIMEOUT)
    public void t08_Dijkstras_SingleVertex() {
        assertEquals(makeDistanceMap(new String[] {"A-0"}),
                GraphAlgorithms.dijkstras(new Vertex<>("A"), setupGraph(1)));
    }

    @Test(timeout = TIMEOUT)
    public void t09_Dijkstras_DirectedRandom() {
        assertEquals(makeDistanceMap(new String[] {"A-0", "B-1", "C-4", "D-6", "E-6",
                                                   "F-7", "G-0", "H-0", "I-0"}),
                GraphAlgorithms.dijkstras(new Vertex<>("A"), setupGraph(2)));
    }

    @Test(timeout = TIMEOUT)
    public void t10_Dijkstras_DirectedBranched() {
        assertEquals(makeDistanceMap(new String[] {"A-0", "B-1", "C-10", "D-5", "E-5",
                                                   "F-5", "G-3"}),
                GraphAlgorithms.dijkstras(new Vertex<>("A"), setupGraph(3)));
    }

    @Test(timeout = TIMEOUT)
    public void t11_Dijkstras_DirectedComplete() {
        assertEquals(makeDistanceMap(new String[] {"A-0", "B-0", "C-1", "D-2", "E-2"}),
                GraphAlgorithms.dijkstras(new Vertex<>("A"), setupGraph(4)));
    }

    @Test(timeout = TIMEOUT)
    public void t12_Dijkstras_DirectedInescapable() {
        assertEquals(makeDistanceMap(new String[] {"A-0", "B-inf", "C-inf"}),
                GraphAlgorithms.dijkstras(new Vertex<>("A"), setupGraph(5)));
    }

    @Test(timeout = TIMEOUT)
    public void t13_Dijkstras_DirectedUnreachable() {
        assertEquals(makeDistanceMap(new String[] {"A-0", "B-1", "C-inf", "D-inf", "E-2",
                                                   "F-inf", "G-inf"}),
                GraphAlgorithms.dijkstras(new Vertex<>("A"), setupGraph(6)));
    }

    @Test(timeout = TIMEOUT)
    public void t14_Dijkstras_UndirectedRandom() {
        assertEquals(makeDistanceMap(new String[] {"A-0", "B-4", "C-12", "D-19", "E-21",
                                                   "F-11", "G-9", "H-8", "I-14"}),
                GraphAlgorithms.dijkstras(new Vertex<>("A"), setupGraph(7)));
    }

    @Test(timeout = TIMEOUT)
    public void t15_Dijkstras_UndirectedDisconnected() {
        assertEquals(makeDistanceMap(new String[] {"A-0", "B-1", "C-4", "D-4", "E-2",
                                                   "F-inf", "G-inf"}),
                GraphAlgorithms.dijkstras(new Vertex<>("A"), setupGraph(8)));
    }

    @Test(timeout = TIMEOUT)
    public void t16_Dijkstras_UndirectedComplete() {
        assertEquals(makeDistanceMap(new String[] {"A-0", "B-6", "C-9", "D-5", "E-1", "F-6"}),
                GraphAlgorithms.dijkstras(new Vertex<>("A"), setupGraph(9)));
    }

    @Test(timeout = TIMEOUT)
    public void t17_Dijkstras_UltimateTest() {
        assertEquals(makeDistanceMap(new String[] {"A-0", "B-4", "C-10", "D-24", "E-49", "F-3",
                                                   "G-2", "H-4", "I-23", "J-4", "K-1", "L-2",
                                                   "M-4", "N-4", "O-5", "P-5", "Q-55", "R-3"}),
                GraphAlgorithms.dijkstras(new Vertex<>("A"), setupGraph(10)));
    }

    @Test(timeout = TIMEOUT)
    public void t18_Prims_SingleVertex() {
        assertEquals(makeEdgeSet(new String[] {}),
                GraphAlgorithms.prims(new Vertex<>("A"), setupGraph(1)));
    }

    @Test(timeout = TIMEOUT)
    public void t19_Prims_UndirectedRandom() {
        Set<Edge<String>> set1 = makeEdgeSet(new String[] {"A-B-4", "B-A-4", "B-C-8", "C-B-8",
                                                           "C-I-2", "I-C-2", "C-F-4", "F-C-4",
                                                           "F-G-2", "G-F-2", "G-H-1", "H-G-1",
                                                           "C-D-7", "D-C-7", "D-E-9", "E-D-9"});
        Set<Edge<String>> set2 = makeEdgeSet(new String[] {"A-B-4", "B-A-4", "A-H-8", "H-A-8",
                                                           "C-I-2", "I-C-2", "C-F-4", "F-C-4",
                                                           "F-G-2", "G-F-2", "G-H-1", "H-G-1",
                                                           "C-D-7", "D-C-7", "D-E-9", "E-D-9"});
        Set<Edge<String>> actual = GraphAlgorithms.prims(new Vertex<>("A"), setupGraph(7));
        assertNotNull(actual);
        assertTrue(actual.equals(set1) || actual.equals(set2));
    }

    @Test(timeout = TIMEOUT)
    public void t20_Prims_UndirectedDisconnected() {
        assertNull(GraphAlgorithms.prims(new Vertex<>("A"), setupGraph(8)));
    }

    @Test(timeout = TIMEOUT)
    public void t21_Prims_UndirectedComplete() {
        assertEquals(32, sumEdgeSet(GraphAlgorithms.prims(
                new Vertex<>("A"), setupGraph(9))));
    }

    @Test(timeout = TIMEOUT)
    public void t22_Prims_UltimateTest() {
        assertEquals(296, sumEdgeSet(GraphAlgorithms.prims(
                new Vertex<>("A"), setupGraph(10))));
    }

    private static Graph<String> setupGraph(int id) {
        switch (id) {
            case 1:     // Single Vertex
                return makeGraph(new String[] {"A"},
                        new String[] {});

            case 2:     // Directed Random
                return makeGraph(new String[] {"A", "B", "C", "D", "E", "F", "G", "H", "I"},
                        new String[] {"A-B-1", "A-I-0", "B-C-3", "B-I-2", "C-D-2", "C-H-1",
                                      "D-E-0", "D-F-3", "E-F-1", "F-C-0", "F-G-2", "G-H-0",
                                      "H-I-1", "I-G-0"});

            case 3:     // Directed Branched
                return makeGraph(new String[] {"A", "B", "C", "D", "E", "F", "G"},
                        new String[] {"A-B-1", "C-A-2", "A-G-3", "B-D-4", "C-G-5", "D-E-0",
                                      "D-F-0", "F-B-6", "G-C-7"});

            case 4:     // Directed Complete
                return makeGraph(new String[] {"A", "B", "C", "D", "E"},
                        new String[] {"A-B-0", "A-C-1", "A-D-2", "A-E-3", "B-A-0", "B-C-1",
                                      "B-D-2", "B-E-2", "C-A-0", "C-B-1", "C-D-2", "C-E-3",
                                      "D-A-0", "D-B-1", "D-C-2", "D-E-3", "E-A-0", "E-B-1",
                                      "E-C-2", "E-D-3"});

            case 5:     // Directed Inescapable
                return makeGraph(new String[] {"A", "B", "C"},
                        new String[] {"B-A-0", "C-A-0"});

            case 6:     // Directed Unreachable
                return makeGraph(new String[] {"A", "B", "C", "D", "E", "F", "G"},
                        new String[] {"A-B-1", "A-E-2", "C-B-3", "C-D-4", "D-B-3", "F-G-0"});

            case 7:     // Undirected Random
                return makeGraph(new String[] {"A", "B", "C", "D", "E", "F", "G", "H", "I"},
                        new String[] {"A-B-4", "B-A-4", "A-H-8", "H-A-8", "B-H-11", "H-B-11",
                                      "B-C-8", "C-B-8", "C-I-2", "I-C-2", "H-I-7", "I-H-7",
                                      "I-G-6", "G-I-6", "G-H-1", "H-G-1", "G-F-2", "F-G-2",
                                      "C-F-4", "F-C-4", "C-D-7", "D-C-7", "D-F-14", "F-D-14",
                                      "D-E-9", "E-D-9", "F-E-10", "E-F-10"});

            case 8:     // Undirected Disconnected
                return makeGraph(new String[] {"A", "B", "C", "D", "E", "F", "G"},
                        new String[] {"A-B-1", "B-A-1", "A-E-2", "E-A-2", "C-B-3", "B-C-3",
                                      "C-D-4", "D-C-4", "D-B-3", "B-D-3", "F-G-0", "G-F-0"});

            case 9:     // Undirected Complete
                return makeGraph(new String[] {"A", "B", "C", "D", "E", "F"},
                        new String[] {"A-B-10", "A-C-15", "A-D-5", "A-E-1", "A-F-20",
                                      "B-A-10", "B-C-4", "B-D-5", "B-E-5", "B-F-15",
                                      "C-A-15", "C-B-4", "C-D-5", "C-E-8", "C-F-10",
                                      "D-A-5", "D-B-5", "D-C-5", "D-E-15", "D-F-1",
                                      "E-A-1", "E-B-5", "E-C-8", "E-D-15", "E-F-5",
                                      "F-A-20", "F-B-15", "F-C-10", "F-D-1", "F-E-5"});

            default:    // The Ultimate Test
                return makeGraph(new String[] {"A", "B", "C", "D", "E", "F", "G", "H", "I",
                                               "J", "K", "L", "M", "N", "O", "P", "Q", "R"},
                        new String[] {"A-B-4", "B-A-4", "B-C-6", "C-B-6", "C-E-40", "E-C-40",
                                      "E-D-30", "D-E-30", "D-B-20", "B-D-20", "A-F-3", "F-A-3",
                                      "F-H-1", "H-F-1", "F-I-20", "I-F-20", "A-G-2", "G-A-2",
                                      "G-J-2", "J-G-2", "A-K-1", "K-A-1", "K-L-1", "L-K-1",
                                      "L-M-2", "M-L-2", "M-P-1", "P-M-1", "P-Q-50", "Q-P-50",
                                      "K-R-2", "R-K-2", "R-P-2", "P-R-2", "K-N-3", "N-K-3",
                                      "N-L-4", "L-N-4", "N-R-2", "R-N-2", "N-O-1", "O-N-1",
                                      "O-L-3", "L-O-3", "O-R-2", "R-O-2", "O-M-3", "M-O-3",
                                      "O-P-2", "P-O-2", "P-E-44", "E-P-44"});
        }
    }

    private static Graph<String> makeGraph(String[] vertices, String[] edges) {
        return new Graph<>(makeVertexSet(vertices), makeEdgeSet(edges));
    }

    private static Set<Edge<String >> makeEdgeSet(String[] edges) {
        Set<Edge<String>> edgeSet = new HashSet<>();
        for (String edge: edges) {
            String[] parameters = edge.split("-");
            edgeSet.add(new Edge<>(new Vertex<>(parameters[0]),
                    new Vertex<>(parameters[1]), Integer.parseInt(parameters[2])));
        }
        return edgeSet;
    }

    private static Set<Vertex<String >> makeVertexSet(String[] vertices) {
        Set<Vertex<String>> vertexSet = new HashSet<>();
        for (String vertex: vertices) {
            vertexSet.add(new Vertex<>(vertex));
        }
        return vertexSet;
    }

    private static List<Vertex<String>> makeVertexList(String[] vertices) {
        List<Vertex<String>> vertexList = new ArrayList<>();
        for (String vertex: vertices) {
            vertexList.add(new Vertex<>(vertex));
        }
        return vertexList;
    }

    private static Map<Vertex<String>, Integer> makeDistanceMap(String[] entries) {
        Map<Vertex<String>, Integer> distanceMap = new HashMap<>();
        for (String entry: entries) {
            String[] parameters = entry.split("-");
            distanceMap.put(new Vertex<>(parameters[0]), parameters[1].equals("inf")
                    ? Integer.MAX_VALUE : Integer.parseInt(parameters[1]));
        }
        return distanceMap;
    }

    private static int sumEdgeSet(Set<Edge<String>> edgeSet) {
        int sum = 0;
        if (edgeSet != null) {
            for (Edge<String> edge: edgeSet) {
                sum += edge.getWeight();
            }
        }
        return sum;
    }
}
