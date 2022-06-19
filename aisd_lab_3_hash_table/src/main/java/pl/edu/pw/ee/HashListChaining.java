package pl.edu.pw.ee;

import pl.edu.pw.ee.services.HashTable;

public class HashListChaining implements HashTable {

    private final Elem nil = null;
    private Elem[] hashElems;
    private int nElem;

    private class Elem {
        private Object value;
        private Elem next;

        Elem(Object value, Elem nextElem) {
            this.value = value;
            this.next = nextElem;
        }
    }

    public HashListChaining(int size) {
        hashElems = new Elem[size];
        initializeHash();
    }

    @Override
    public void add(Object value) {
        int hashCode = value.hashCode();
        int hashId = countHashId(hashCode);

        Elem oldElem = hashElems[hashId];
        while (oldElem != nil && !oldElem.value.equals(value)) {
            oldElem = oldElem.next;
        }
        if (oldElem != nil) {
            oldElem.value = value;
        } else {
            hashElems[hashId] = new Elem(value, hashElems[hashId]);
            nElem++;
        }
    }

    @Override
    public Object get(Object value) {
        int hashCode = value.hashCode();
        int hashId = countHashId(hashCode);

        Elem elem = hashElems[hashId];

        while (elem != nil && !elem.value.equals(value)) {
            elem = elem.next;
        }

        return elem != nil ? elem.value : nil;
    }

    @Override
    public void delete(Object value) {
        int hashCode = value.hashCode();
        int hashId = countHashId(hashCode);

        Elem elem = hashElems[hashId];

        if (elem == null) {
            return;
        }

        if (elem.value.equals(value)) {
            hashElems[hashId] = elem.next;
            nElem--;
            return;
        }

        while (elem.next != null && !elem.next.value.equals(value)) {
            elem = elem.next;
        }

        if (elem.next.value.equals(value)) {
            elem.next = elem.next.next;
        } else {
            return;
        }

    }

    public double countLoadFactor() {
        double size = hashElems.length;
        return nElem / size;
    }

    private void initializeHash() {
        int n = hashElems.length;

        for (int i = 0; i < n; i++) {
            hashElems[i] = nil;
        }
    }

    public int countHashId(int hashCode) {
        int n = hashElems.length;
        if (hashCode < 0) {
            return (-1) * (hashCode % n);
        }
        return hashCode % n;
    }

    public int getHashSize() {
        return hashElems.length;
    }

}