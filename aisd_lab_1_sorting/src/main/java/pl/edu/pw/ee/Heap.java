package pl.edu.pw.ee;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pl.edu.pw.ee.services.HeapInterface;

public class Heap<T extends Comparable<T>> implements HeapInterface<T> {

    private List<T> nmbrs = new ArrayList<>();

    @Override
    public void put(T item) {
        nmbrs.add(item);
        heapUp();
    }

    @Override
    public T pop() {

        if (nmbrs.size() == 0) {
            throw new IndexOutOfBoundsException("No elements in heap");
        }
        T tmp = nmbrs.get(0);
        Collections.swap(nmbrs, 0, nmbrs.size() - 1);
        nmbrs.remove(nmbrs.size() - 1);
        heapDown();
        return tmp;

    }

    private void heapUp() {
        int i = nmbrs.size() - 1;
        while (i > 0) {
            int parent = (i - 1) / 2;
            if (nmbrs.get(parent).compareTo(nmbrs.get(i)) >= 0) {
                return;
            }
            Collections.swap(nmbrs, parent, i);
            i = parent;
        }
    }

    private void heapDown() {
        int i = 0;
        int kid = 2 * i + 1;
        while (kid < nmbrs.size()) {
            if (kid + 1 < nmbrs.size() && nmbrs.get(kid + 1).compareTo(nmbrs.get(kid)) > 0)
            {
                kid++;
            }
            if (nmbrs.get(i).compareTo(nmbrs.get(kid)) >= 0) {
                return;
            }
            Collections.swap(nmbrs, kid, i);
            i = kid;
            kid = 2 * i + 1;
        }
    }

}
