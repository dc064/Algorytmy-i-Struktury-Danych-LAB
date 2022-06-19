package pl.edu.pw.ee;

public class KluskalNode {
    private String name;
    private int treeName;

    public KluskalNode(String name, int treeName) {
        this.name = name;
        this.treeName = treeName;
    }

    public void setTreeName(int treeName) {
        this.treeName = treeName;
    }

    public String getName() {
        return this.name;
    }

    public int getTreeName() {
        return this.treeName;
    }
}
