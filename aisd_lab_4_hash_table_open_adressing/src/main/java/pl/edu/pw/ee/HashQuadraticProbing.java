package pl.edu.pw.ee;

public class HashQuadraticProbing<T extends Comparable<T>> extends HashOpenAdressing<T> {
    private final double a;
    private final double b;
    public HashQuadraticProbing() {
        super();
        this.a = 3.0;
        this.b = 2.0;
    }

    public HashQuadraticProbing(int size) {
        super(size);
        this.a = 3.0;
        this.b = 2.0;
    }

    public HashQuadraticProbing(int size, double a, double b) {
        super(size);
        if (a == 0 || b == 0) {
            throw new IllegalArgumentException("Factor cannot be zero!");
        }
        this.a = a;
        this.b = b;
    }

    @Override
    int hashFunc(int key, int i) {
        int m = getSize();

        int hash = (int)((key % m + a * i + b * i * i) % m);

        hash = hash < 0 ? -hash : hash;

        return hash;
    }
}
