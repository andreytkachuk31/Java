package structure.graph.impl;

import structure.graph.Graph;

import java.util.Scanner;

/**
 * Матрица инцидентности — одна из форм представления графа, в которой указываются связи между инцидентными элементами графа (ребро(дуга) и вершина).
 * Столбцы матрицы соответствуют ребрам, строки — вершинам. Ненулевое значение в ячейке матрицы указывает связь между вершиной и ребром (их инцидентность).
 *
 * @author Andrii_Tkachuk
 * @since 12/26/2014
 */
public class GraphIncidenceMatrix implements Graph {

    private int numberOfVertices;
    private int numberOfEdges;
    private int incidenceMatrix[][];

    private GraphIncidenceMatrix(int numberOfVertices, int numberOfEdges) {
        this.numberOfEdges = numberOfEdges;
        this.numberOfVertices = numberOfVertices;
        this.incidenceMatrix = new int[numberOfVertices][numberOfEdges];
    }

    public static GraphIncidenceMatrix createGraph() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter The Number Of Vertices: ");
        int numberOfVertices = scan.nextInt();
        System.out.println("Enter The Number Of Edges: ");
        int numberOfEdges = scan.nextInt();
        GraphIncidenceMatrix graphIncidenceMatrix = new GraphIncidenceMatrix(numberOfVertices, numberOfEdges);
        System.out.println("Enter the Egdes Format : <source index> <destination index> <edge-number>  ");
        for (int edgeCount = 0; edgeCount <= numberOfEdges; edgeCount++) {
            String lineEgdesFormat = scan.nextLine();
            if (lineEgdesFormat != null && lineEgdesFormat.length() > 0) {
                String[] format = lineEgdesFormat.split(" ");
                int source = Integer.parseInt(format[0]);
                int destination = Integer.parseInt(format[1]);
                int edgeNumber = Integer.parseInt(format[2]);
                graphIncidenceMatrix.addEgde(source, destination, edgeNumber);
            }
        }
        return graphIncidenceMatrix;
    }

    public void showGraph() {
        for (int i = 0; i < numberOfVertices; i++) {
            for (int j = 0; j < numberOfEdges; j++) {
                System.out.print(incidenceMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    private void addEgde(int fromVertex, int toVertex, int edgeNum) {
        try {
            incidenceMatrix[fromVertex - 1][edgeNum - 1] = 1;
            incidenceMatrix[toVertex - 1][edgeNum - 1] = 1;
        } catch (ArrayIndexOutOfBoundsException indexBounce) {
            System.out.println("the vertex entered is not present");
        }
    }
}
