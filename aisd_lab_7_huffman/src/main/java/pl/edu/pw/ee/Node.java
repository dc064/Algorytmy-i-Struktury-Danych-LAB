package pl.edu.pw.ee;

public class Node {
    private int frequency;
    private char sign;
    public Node leftChild;
    public Node rightChild;
    private String codeOfNode = "";

    public Node(int i) {
        this.frequency = 0;
        this.sign = (char) i;
        this.leftChild = null;
        this.rightChild = null;
    }
    public Node(int frequency, char sign, Node leftChild, Node rightChild) {
        this.frequency = frequency;
        this.sign = sign;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public Node(int frequency, char sign, String codeOfNode) {
        this.frequency = frequency;
        this.sign = sign;
        this.leftChild = null;
        this.rightChild = null;
        this.codeOfNode = codeOfNode;
    }

    public void setRight(Node right) {
        this.leftChild = right;
    }

    public void setLeft(Node left) {
        this.leftChild = left;
    }

    public int getFrequency() {
        return this.frequency;
    }

    public void increaseFrequency() {
        this.frequency++;
    }

    public char getSign() {
        return this.sign;
    }

    public Node getLeftChild() {
        return this.leftChild;
    }

    public Node getRightChild() {
        return this.rightChild;
    }

    public String getCodeOfNode() {
        return this.codeOfNode;
    }

    public void addToString(String symbol) {       
        String tmp = this.codeOfNode;  
        tmp += symbol;
        this.codeOfNode = tmp;  
    }

    public void addToStringOfLeftChild(String symbol) {       
        String tmp = this.codeOfNode;  
        tmp += symbol;
        leftChild.codeOfNode = tmp; 
    }

    public void addToStringOfRightChild(String symbol) {       
        String tmp = this.codeOfNode;  
        tmp += symbol;
        rightChild.codeOfNode = tmp;  
    }

}

