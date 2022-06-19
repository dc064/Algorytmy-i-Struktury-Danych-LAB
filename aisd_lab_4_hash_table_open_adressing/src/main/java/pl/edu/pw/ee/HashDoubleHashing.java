package pl.edu.pw.ee;

public class HashDoubleHashing<T extends Comparable<T>> extends HashOpenAdressing<T> {

    public HashDoubleHashing() {
        super();
    }

    public HashDoubleHashing(int size) {
        super(size);
    }

    @Override
    int hashFunc(int key, int i) {
        int m = getSize();

        int preHash = key % m;
        int preHash2 = 1 + (key % (m - 3));
        int hash = (preHash * i + preHash2) % m;

        hash = hash < 0 ? -hash : hash;

        return hash;
    }
}

