package pl.edu.pw.ee;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Compresser {

    private TreeMaker treeMaker;
    private int pointerForSetCodesRecurention = 0;
    private File dictionaryFile;
    private File fileToRead;
    private String pathToRootDir;
    private String pathForUncodedFile;
    private String pathForDictionary;
    private String pathForCodedFile;
    private Node[] list;
    private long bits = 0;

    public Compresser(String pathToRootDir) {

        this.list = new Node[256];
        for (int i = 0; i < list.length; i++) {
            this.list[i] = new Node(i);
        }

        if (pathToRootDir == null) {
            throw new IllegalArgumentException("Path cannot be null!");
        }

        this.pathToRootDir = pathToRootDir;

        this.pathForUncodedFile = this.pathToRootDir + "uncodedText.txt";
        this.pathForDictionary = this.pathToRootDir + "dictionary.txt";
        this.pathForCodedFile = this.pathToRootDir + "codedText.txt";

        this.fileToRead = new File(pathForUncodedFile);
        this.dictionaryFile = new File(pathForDictionary);

        if (!fileToRead.exists()) {
            throw new IllegalArgumentException("Not found file to code!");
        }

    }

    public int compressing() {
        return compressingText();
    }

    private int compressingText() {

        String currentLine;
        Scanner scanner;
        Node tree;
        int diffChars = 0;
        int numOfBits = 0;

        try {
            File codedFile = new File(this.pathForCodedFile);
            BufferedWriter bwForCodeFile = new BufferedWriter(new FileWriter(codedFile));
            scanner = new Scanner(fileToRead);

            while (scanner.hasNextLine()) {
                currentLine = scanner.nextLine();
                if (scanner.hasNextLine()) {
                    currentLine += "\n";
                }
                for (int i = 0; i < currentLine.length(); i++) {
                    if (currentLine.charAt(i) > 255) {
                        bwForCodeFile.close();
                        throw new IllegalArgumentException("Found char not in ASCII");
                    }
                    if (list[currentLine.charAt(i)].getFrequency() == 0) {
                        diffChars++;
                    }
                    list[currentLine.charAt(i)].increaseFrequency();
                }
            }
            scanner.close();

            Arrays.sort(list, new NodeSorterByFrequency());

            treeMaker = new TreeMaker(list, diffChars);
            tree = treeMaker.getTree();

            setCodesForLetters(tree, list);

            BufferedWriter writer = new BufferedWriter(new FileWriter(dictionaryFile));
            writer.write(diffChars + "\n");
            for (int i = 0; i < diffChars; i++) {
                if (list[i] != null) {
                    writer.write((int) list[i].getSign() + " " + list[i].getFrequency() + " "
                            + list[i].getCodeOfNode() + "\n");
                }
            }
            writer.close();

            scanner = new Scanner(fileToRead);
            while (scanner.hasNextLine()) {
                String currentCodedLine = "";
                currentLine = scanner.nextLine();
                if (scanner.hasNextLine()) {
                    currentLine += '\n';
                }
                for (int i = 0; i < currentLine.length(); i++) {

                    String currentCharCode = codeOfChar(currentLine.charAt(i), list, diffChars);
                    currentCodedLine += currentCharCode;
                    bits += currentCharCode.length();

                }

                bwForCodeFile.write(currentCodedLine + '\n');
            }
            scanner.close();
            bwForCodeFile.close();

        } catch (IOException | IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        if (bits > Integer.MAX_VALUE) {
            throw new IllegalArgumentException("Number of bits is too big for int!");
        }
        numOfBits = (int) bits;
        return numOfBits;
    }

    private void setCodesForLetters(Node root, Node[] lista) {
        if (root == null) {
            throw new IllegalArgumentException("Nie ma korzenia!");
        }
        setCodesRecurention(root, lista);
    }

    private void setCodesRecurention(Node node, Node[] lista) {

        if (node.getLeftChild() != null) {
            node.addToStringOfLeftChild("0");
            setCodesRecurention(node.getLeftChild(), lista);
        }

        if (node.getRightChild() != null) {
            node.addToStringOfRightChild("1");
            setCodesRecurention(node.getRightChild(), lista);
        }

        else if (node.getLeftChild() == null && node.getRightChild() == null) {
            lista[pointerForSetCodesRecurention] = node;
            pointerForSetCodesRecurention++;
        }
    }

    private class NodeSorterByFrequency implements Comparator<Node> {
        @Override
        public int compare(Node firstNode, Node secondNode) {
            return firstNode.getFrequency() - secondNode.getFrequency();
        }
    }

    private String codeOfChar(char charToCode, Node[] list, int end) {
        for (int i = end - 1; i >= 0; i--) {
            if (list[i].getSign() == charToCode) {
                return list[i].getCodeOfNode();
            }
        }
        throw new IndexOutOfBoundsException("Code not found!");
    }

}
