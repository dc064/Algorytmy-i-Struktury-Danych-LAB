package pl.edu.pw.ee;

public class Edge {
    private String currentNode;
    private String anotherNode;
    private int value;

    public Edge(String currentNode, String anotherNode, int value) {
        if (currentNode == null || anotherNode == null) {
            throw new IllegalArgumentException("Found null node!");
        }

        this.currentNode = currentNode;
        this.anotherNode = anotherNode;
        this.value = value;

    }

    public int getValue() {
        return this.value;
    }

    public String toString() {
        return currentNode + "_" + value + "_" + anotherNode; 
    }

    public String getFirstNode() {
        return this.currentNode;
    }

    public String getSecondNode() {
        return this.anotherNode;
    }

}
