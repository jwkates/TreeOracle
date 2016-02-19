import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by jackkates on 2/4/16.
 */
public class Main {

    public static void main(String[] args) {
        new Main().run();
    }

    public void run() {
        int counter = 1;
        Graph g = new Graph();

        Scanner sc = new Scanner(System.in);
        while(sc.hasNextInt()) {
            int source = sc.nextInt();
            int destination = sc.nextInt();

            if (source == 0 && destination == 0) {
                if (g.isTree()) {
                    System.out.println("Case " + counter + " is a tree.");
                } else {
                    System.out.println("Case " + counter + " is not a tree.");
                }
                counter++;
                g = new Graph();
            } else if (source == -1 && destination == -1) {
                break;
            } else {
                g.addEdge(source, destination);
            }
        }
    }

    private class Graph {

        private Map<Integer, Vertex> vertices;

        public Graph() {
            vertices = new HashMap<Integer, Vertex>();
        }

        public void addEdge(int source, int destination) {
            if (!vertices.containsKey(source)) {
                vertices.put(source, new Vertex());
            }
            if (!vertices.containsKey(destination)) {
                vertices.put(destination, new Vertex());
            }
            vertices.get(destination).addParent();


        }

        public boolean isTree() {
            if (vertices.isEmpty()) {
                return false;
            }
            int nodesWithNoParents = 0;
            int nodesWithOneParent = 0;
            for (Vertex v : vertices.values()) {
                if (v.numParents == 0) {
                    nodesWithNoParents++;
                } else if (v.numParents == 1) {
                    nodesWithOneParent++;
                }
            }
            return (nodesWithNoParents == 1) && (nodesWithOneParent == vertices.size() - 1);
        }
    }

    private class Vertex {
        public int numParents;

        public Vertex() {
            numParents = 0;
        }

        public void addParent() {
            numParents++;
        }
    }

}
