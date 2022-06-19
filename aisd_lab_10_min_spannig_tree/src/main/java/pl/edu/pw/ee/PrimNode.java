package pl.edu.pw.ee;

import java.util.ArrayList;

public class PrimNode {
    private String name;
    private ArrayList<Edge> edges;
    private boolean isIncluded;

    public PrimNode(String name) {
        this.name = name;
        this.edges = new ArrayList<Edge>();
        this.isIncluded = false;
    }

    public void setIfIncluded(boolean ifIncluded) {
        this.isIncluded = ifIncluded;
    }

    public void addEgde(Edge edge) {
        if (edge == null) {
            throw new IllegalArgumentException("Cannot add null edge!");
        }
        this.edges.add(edge);
    }

    public boolean getIsIncluded() {
        return this.isIncluded;
    }

    public boolean ifNameExists (String comparedNode) {
        if (comparedNode == null) {
            throw new NullPointerException("Cannot compare name to a null string!");
        }
        return this.name.equals(comparedNode);
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<Edge> getEdges() {
        return this.edges;
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }
}
