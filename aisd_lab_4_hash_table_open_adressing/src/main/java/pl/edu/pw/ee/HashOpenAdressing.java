package pl.edu.pw.ee;

import pl.edu.pw.ee.services.HashTable;

public abstract class HashOpenAdressing<T extends Comparable<T>> implements HashTable<T> {

    private final T nil = null;
    private int size;
    private int nElems;
    private T[] hashElems;
    private boolean[] removedPlaces;
    private final double correctLoadFactor;

    HashOpenAdressing() {
        this(2039);
    }

    HashOpenAdressing(int size) {
        validateHashInitSize(size);

        this.size = size;
        this.hashElems = (T[]) new Comparable[this.size];
        this.removedPlaces = new boolean[this.size];
        this.correctLoadFactor = 0.75;
    }

    @Override
    public void put(T newElem) {
        validateInputElem(newElem);
        resizeIfNeeded();

        int key = newElem.hashCode();
        int i = 0;
        int hashId = hashFunc(key, i);

        while (hashElems[hashId] != nil) {
            if (i == getSize()) {
                doubleResize();
                i = 0;
            }
            if (hashElems[hashId] != nil) {
                if (hashElems[hashId].equals(newElem)) {
                    hashElems[hashId] = newElem;
                    return;
                }
            }
            i = i + 1;
            hashId = hashFunc(key, i);
        }

        hashElems[hashId] = newElem;
        nElems++;
    }

    @Override
    public T get(T elem) {
        validateInputElem(elem);
        int key = elem.hashCode();
        int i = 0;
        int hashId = hashFunc(key, i);
        while ((hashElems[hashId] != nil || removedPlaces[hashId] == true) && i < getSize()) {
            if (hashElems[hashId] != nil) {
                if (hashElems[hashId].equals(elem)) {
                    return hashElems[hashId];
                }
            }

            i = i + 1;
            hashId = hashFunc(key, i);

        }
        return null;
    }

    @Override
    public void delete(T elem) {
        validateInputElem(elem);
        int key = elem.hashCode();
        int i = 0;
        int hashId = hashFunc(key, i);

        while ((hashElems[hashId] != nil || removedPlaces[hashId] == true) && i < getSize()) {
            if (hashElems[hashId] != nil) {
                if (hashElems[hashId].equals(elem)) {
                    hashElems[hashId] = nil;
                    removedPlaces[hashId] = true;
                    nElems--;
                    return;
                }
            }
            i = i + 1;
            hashId = hashFunc(key, i);
        }
    }

    private void validateHashInitSize(int initialSize) {
        if (initialSize < 1) {
            throw new IllegalArgumentException("Initial size of hash table cannot be lower than 1!");
        }
    }

    private void validateInputElem(T newElem) {
        if (newElem == null) {
            throw new IllegalArgumentException("Input elem cannot be null!");
        }
    }

    abstract int hashFunc(int key, int i);

    public int getSize() {
        return size;
    }

    private void resizeIfNeeded() {
        double loadFactor = countLoadFactor();

        if (loadFactor >= correctLoadFactor) {
            doubleResize();
        }
    }

    private double countLoadFactor() {
        return (double) nElems / size;
    }

    private void doubleResize() {
        this.size *= 2;
        removedPlaces = new boolean[this.size];
        T[] hashElemsTMP = hashElems;
        this.hashElems = (T[]) new Comparable[this.size];
        this.nElems = 0;
        for (int i = 0; i < hashElemsTMP.length; i++) {
            if (hashElemsTMP[i] != nil) {
                put(hashElemsTMP[i]);
            }
        }
    }
}