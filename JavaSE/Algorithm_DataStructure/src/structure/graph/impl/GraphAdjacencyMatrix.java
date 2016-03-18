package structure.graph.impl;

import structure.graph.Graph;

import java.util.Scanner;

/**
 * Матрица смежности графа G с конечным числом вершин n (пронумерованных числами от 1 до n) — это квадратная матрица A размера n,
 * в которой значение элемента aij равно числу рёбер из i-й вершины графа в j-ю вершину.
 *
 * @author Andrii_Tkachuk
 * @since 12/29/2014
 */
public class GraphAdjacencyMatrix implements Graph {

    private int numberOfVertices;
    private int numberOfEdges;
    private int adjacencyMatrix[][];

    private GraphAdjacencyMatrix(int numberOfVertices, int numberOfEdges) {
        this.numberOfVertices = numberOfVertices;
        this.numberOfEdges = numberOfEdges;
        this.adjacencyMatrix = new int[numberOfVertices][numberOfVertices];
    }

    public static GraphAdjacencyMatrix createGraph() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter The Number Of Vertices: ");
        int numberOfVertices = scan.nextInt();
        System.out.println("Enter The Number Of Edges: ");
        int numberOfEdges = scan.nextInt();
        GraphAdjacencyMatrix graphAdjacencyMatrix = new GraphAdjacencyMatrix(numberOfVertices, numberOfEdges);
        System.out.println("Enter the Vertices Format : <source index> <destination index> ");
        for (int edgeCount = 0; edgeCount <= numberOfEdges; edgeCount++) {
            String lineEgdesFormat = scan.nextLine();
            if (lineEgdesFormat != null && lineEgdesFormat.length() > 0) {
                String[] format = lineEgdesFormat.split(" ");
                int source = Integer.parseInt(format[0]);
                int destination = Integer.parseInt(format[1]);
                graphAdjacencyMatrix.addEdge(source, destination);
            }
        }
        return graphAdjacencyMatrix;
    }

    public void showGraph() {
        for (int i = 0; i < numberOfVertices; i++) {
            for (int j = 0; j < numberOfVertices; j++) {
                System.out.print(adjacencyMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    private void addEdge(int i, int j) {
        try {
            adjacencyMatrix[i - 1][j - 1] = 1;
            adjacencyMatrix[j - 1][i - 1] = 1;
        } catch (ArrayIndexOutOfBoundsException indexBounce) {
            System.out.println("the vertex entered is not present");
        }
    }
}
