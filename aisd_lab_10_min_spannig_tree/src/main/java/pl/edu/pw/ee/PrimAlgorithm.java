package pl.edu.pw.ee;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import pl.edu.pw.ee.services.MinSpanningTree;

public class PrimAlgorithm implements MinSpanningTree {
    File data;
    private ArrayList<String> nodesNames;
    private RedBlackTree<String, PrimNode> hashForNodes;
    private ArrayList<Edge> edges;
    private int iterations = 0;

    public String findMST(String pathToFile){

        if (pathToFile == null) {
            throw new IllegalArgumentException("Path to file cannot be null!");
        }
        set(pathToFile);
        if (nodesNames.size() == 0) {
            throw new IllegalArgumentException("File cannot be empty!");
        }
        // TODO

        PriorityQueue edgesQueue = new PriorityQueue();
        String actualNodeName = nodesNames.get(0);
        PrimNode firstCurrentNode;
        PrimNode secondCurrentNode;

        while (!allNodesVisited()) {
            if (iterations > nodesNames.size()) {
                throw new IllegalArgumentException("Cannot find MST! Incorrect data. Not all nodes are connected at the begging!");
            }
            PrimNode actualNode = hashForNodes.get(actualNodeName);
            actualNode.setIfIncluded(true);
            hashForNodes.put(actualNodeName, actualNode);
            Edge currentEdge;

            ArrayList<Edge> actualEdges = new ArrayList<Edge>();
            actualEdges = actualNode.getEdges();

            for (int i = 0; i < actualEdges.size(); i++) {
                edgesQueue.put(actualEdges.get(i));
            }
            do {
                currentEdge = edgesQueue.get();
                String nameOfFirstNode = currentEdge.getFirstNode();
                String nameOfSecondNode = currentEdge.getSecondNode();
                firstCurrentNode = hashForNodes.get(nameOfFirstNode);
                secondCurrentNode = hashForNodes.get(nameOfSecondNode);
            } while (secondCurrentNode.getIsIncluded() && edgesQueue.getNumberOfEdgesInQueue() > 0);

            if (!secondCurrentNode.getIsIncluded()) {
                edges.add(currentEdge);
                actualNodeName = secondCurrentNode.getName();
            }
            iterations++;
        } 

        String mst = "";
        mst += edges.get(0).toString();
        for (int i=1; i<edges.size(); i++) {
            mst += "|" + edges.get(i).toString();
        }
        return mst;
    }

    private void set(String pathToFile) {

        data = new File(pathToFile);
        nodesNames = new ArrayList<String>();
        hashForNodes = new RedBlackTree<String, PrimNode>();
        edges = new ArrayList<Edge>();

        String currentLine;
        try (Scanner scanner = new Scanner(data)) {

            while (scanner.hasNextLine()) {
                currentLine = scanner.nextLine();

                String[] ingredientsOfNewEdge = currentLine.split(" ");
                if (ingredientsOfNewEdge.length != 3) {
                    throw new IllegalArgumentException("Found a mistake in data file!");
                }

                String nameOfFirstNode = ingredientsOfNewEdge[0];
                String nameOfSecondNode = ingredientsOfNewEdge[1];
                int value = Integer.parseInt(ingredientsOfNewEdge[2]);
                if (nameOfFirstNode.equals(nameOfSecondNode)) {
                    throw new IllegalArgumentException("Found invalid relation: road between the same node");
                }

                if (value <= 0) {
                    throw new IllegalArgumentException("Found invalid edge value in data file!");
                }

                PrimNode firstCurrentNode = hashForNodes.get(nameOfFirstNode);
                if (firstCurrentNode == null) {
                    firstCurrentNode = new PrimNode(nameOfFirstNode);
                    nodesNames.add(nameOfFirstNode);
                }

                PrimNode secondCurrentNode = hashForNodes.get(nameOfSecondNode);
                if (secondCurrentNode == null) {
                    secondCurrentNode = new PrimNode(nameOfSecondNode);
                    nodesNames.add(nameOfSecondNode);
                }

                Edge currentEdgeForFirstNode = new Edge(nameOfFirstNode, nameOfSecondNode, value);
                Edge currentEdgeForSecondNode = new Edge(nameOfSecondNode, nameOfFirstNode, value);

                firstCurrentNode.addEgde(currentEdgeForFirstNode);
                secondCurrentNode.addEgde(currentEdgeForSecondNode);

                hashForNodes.put(nameOfFirstNode, firstCurrentNode);
                hashForNodes.put(nameOfSecondNode, secondCurrentNode);

            }
            scanner.close();

        } catch (IOException e) {
            System.out.println("There is a problem with data file");
        }

    }

    private boolean allNodesVisited() {
        for (String iterator : nodesNames) {
            PrimNode nodetoCheck = hashForNodes.get(iterator);
            if (!nodetoCheck.getIsIncluded()) {
                return false;
            }
        }
        return true;
    }

}
