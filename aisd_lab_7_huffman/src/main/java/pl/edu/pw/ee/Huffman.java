package pl.edu.pw.ee;

public class Huffman {

    private Node[] list;

    public Huffman() {
        this.list = new Node[256];
        for (int i = 0; i < list.length; i++) {
            list[i] = new Node(i);
        }
    }

    public int huffman(String pathToRootDir, boolean compress) {
        if (pathToRootDir == null) {
            throw new IllegalArgumentException("Path cannot be null");
        }
        if (compress) {
            Compresser compresser = new Compresser(pathToRootDir);
            return compresser.compressing();
        }
        else {
            Decompresser decompresser = new Decompresser(pathToRootDir);
            return decompresser.decompressing();
        }
    }

}
