package pl.edu.pw.ee;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import pl.edu.pw.ee.services.MinSpanningTree;

public class KluskalAlgorithm implements MinSpanningTree {
    File data;
    private ArrayList<String> nodesNames;
    private RedBlackTree<String, KluskalNode> rbtForNodes;
    private ArrayList<Edge> edges;
    private PriorityQueue priorityQueue;
    private int numberOfNodes = 0;
    private int iterations = 0;

    public String findMST(String pathToFile){

        if (pathToFile == null) {
            throw new IllegalArgumentException("Path to file cannot be null!");
        }
        set(pathToFile);
        if (nodesNames.size() == 0) {
            throw new IllegalArgumentException("File cannot be empty!");
        }

        while (!allNodesVisited()) {
            
            Edge currentEdge = priorityQueue.get();
            if (currentEdge == null) {
                throw new IllegalArgumentException("Cannot find MST! Incorrect data. Not all nodes are connected at the begging!");
            }
            String nameOfFirstNode = currentEdge.getFirstNode();
            String nameOfSecondNode = currentEdge.getSecondNode();
            KluskalNode firstCurrentNode = rbtForNodes.get(nameOfFirstNode);
            KluskalNode secondCurrentNode = rbtForNodes.get(nameOfSecondNode);

            int treeNumberOfFirstNode = firstCurrentNode.getTreeName();
            int treeNumberOfSecondNode = secondCurrentNode.getTreeName();


            if(connectsTwoTrees(treeNumberOfFirstNode, treeNumberOfSecondNode)) {
                edges.add(currentEdge);

                for (String currentNodeName : nodesNames) {
                    KluskalNode currentNode = rbtForNodes.get(currentNodeName);
                    if (currentNode.getTreeName() == treeNumberOfSecondNode) {
                        currentNode.setTreeName(treeNumberOfFirstNode);
                        rbtForNodes.put(currentNodeName, currentNode);
                    }
                }
            }

        }

        String mst = "";
        mst += edges.get(0).toString();
        for (int i=1; i<edges.size(); i++) {
            mst += "|" + edges.get(i).toString();
        }
        return mst;
    }

    private boolean connectsTwoTrees(int treeNumberOfFirstNode, int treeNumberOfSecondNode) {
        return !(treeNumberOfFirstNode == treeNumberOfSecondNode);
    }


    private void set(String pathToFile) {

        rbtForNodes = new RedBlackTree<String, KluskalNode>();
        priorityQueue = new PriorityQueue();
        data = new File(pathToFile);
        nodesNames = new ArrayList<String>();
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

                KluskalNode firstCurrentNode = rbtForNodes.get(nameOfFirstNode);
                if (firstCurrentNode == null) {
                    firstCurrentNode = new KluskalNode(nameOfFirstNode, numberOfNodes++);
                    nodesNames.add(nameOfFirstNode);
                    rbtForNodes.put(nameOfFirstNode, firstCurrentNode);
                }

                KluskalNode secondCurrentNode = rbtForNodes.get(nameOfSecondNode);
                if (secondCurrentNode == null) {
                    secondCurrentNode = new KluskalNode(nameOfSecondNode, numberOfNodes++);
                    nodesNames.add(nameOfSecondNode);
                    rbtForNodes.put(nameOfSecondNode, secondCurrentNode);
                }

                Edge currentEdge = new Edge(nameOfFirstNode, nameOfSecondNode, value);
                priorityQueue.put(currentEdge);

            }
            scanner.close();

        } catch (IOException e) {
            System.out.println("There is a problem with data file");
        }

    }

    private boolean allNodesVisited() {
        String nameOfFirstNodeInList = nodesNames.get(0);
        KluskalNode firstNodeInList = rbtForNodes.get(nameOfFirstNodeInList);
        int treeNumberOfFirstNode = firstNodeInList.getTreeName();
        
        for (String iterator : nodesNames) {
            KluskalNode nodetoCheck = rbtForNodes.get(iterator);
            if (nodetoCheck.getTreeName() != treeNumberOfFirstNode){
                return false;
            }
        }
        return true;
    }

}
