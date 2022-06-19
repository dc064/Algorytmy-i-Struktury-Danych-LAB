package pl.edu.pw.ee;

import java.util.Comparator;

public class PriorityQueue {

    private Elem head = null;
    private EdgeComparator comparator = new EdgeComparator();
    private int edgesInQueue = 0;

    private class EdgeComparator implements Comparator<Edge> {

        @Override
        public int compare(Edge first, Edge second) {
            return second.getValue() - first.getValue();
        }

    }

    public void put(Edge objectToPut) {
        if (objectToPut == null) {
            throw new IllegalArgumentException("Cannot put null!");
        }
        if (head == null || comparator.compare(objectToPut, head.value) > 0) {
            head = new Elem(objectToPut, head);
        } else {
            Elem newElem = new Elem(objectToPut);
            Elem iterator = head;
            while (iterator.next != null && comparator.compare(iterator.next.value, objectToPut) >= 0)
                iterator = iterator.next;
            newElem.next = iterator.next;
            iterator.next = newElem;
        }
        edgesInQueue++;
    }

    public Edge get() {
        if (head == null) {
            return null;
        }

        Edge tmp = head.value;
        head = head.next;
        edgesInQueue--;
        return tmp;
    }

    private class Elem {

        Edge value;
        Elem next;

        Elem(Edge value) {
            this.value = value;
            this.next = null;
        }

        Elem(Edge value, Elem next) {
            this.value = value;
            this.next = next;
        }
    }

    public int getNumberOfEdgesInQueue() {
        return this.edgesInQueue;
    }

}
