package pl.edu.pw.ee;

public class TreeMaker {
    
    private Node [] list;
    private int diffChars;

    public TreeMaker (Node [] list, int diffChars) {
        this.list = list;
        this.diffChars = diffChars;
        
        validateParams();
    }

    public Node getTree() {
        validateParams();

        Node tree = makeTree();

        return tree;
    }

    private void validateParams() {
        if (this.list == null) {
            throw new IllegalArgumentException("List cannot be null");
        }

        for (int i = 0; i < list.length; i++) {
            if (list[i] == null) {
                throw new IllegalArgumentException("Found node in list that is null");
            }
        }

        if (this.diffChars <= 1) {
            throw new IllegalArgumentException("Invalid number of different chars");
        }

    }


    private Node makeTree() {
        int pointerForMakingNodes = list.length - diffChars;
        int endForPointer = list.length;
        int currentLvl;
        int freq;

        do {
            currentLvl = 0;
            while (pointerForMakingNodes < endForPointer) {
                if (pointerForMakingNodes + 1 < endForPointer) { // srodek- tworzymy node z dwoma "dziecmi"
                    freq = list[pointerForMakingNodes].getFrequency() + list[pointerForMakingNodes + 1].getFrequency();
                    list[currentLvl++] = new Node(freq, (char) 257, list[pointerForMakingNodes], // do zmiany
                            list[pointerForMakingNodes + 1]);
                } else { // mamy tylko jeden wierzcholek
                    list[currentLvl++] = list[pointerForMakingNodes];
                }
                pointerForMakingNodes += 2;
            }

            pointerForMakingNodes = 0;
            endForPointer = currentLvl;

        } while (currentLvl > 1);

        return list[0];

    }

}
