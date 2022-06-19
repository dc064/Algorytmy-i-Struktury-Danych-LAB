package pl.edu.pw.ee;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Decompresser {

    private TreeMaker treeMaker;
    private int pointerForFindingDecodedChar = 0;
    private File dictionaryFile;
    private File fileToRead;
    private String pathToRootDir;
    private String pathForUncodedFile;

    public Decompresser(String pathToRootDir) {

        if (pathToRootDir == null) {
            throw new IllegalArgumentException("Path cannot be null!");
        }

        this.pathForUncodedFile = pathToRootDir + "uncodedText.txt";
        String pathForDictionary = pathToRootDir + "dictionary.txt";
        String pathForCodedFile = pathToRootDir + "codedText.txt";
        
        this.dictionaryFile = new File(pathForDictionary);
        this.fileToRead = new File(pathForCodedFile);

        if (!dictionaryFile.exists() || !fileToRead.exists()) {
            throw new IllegalArgumentException("Cannot find all required files for decompressing!");
        }
        if (this.dictionaryFile.length() == 0) {
            throw new IllegalArgumentException("Dictionary cannot be empty!");
        }
    }

    public int decompressing() {
        return decompressingText();
    }

    private int decompressingText() {

        Node [] list = decodeList(dictionaryFile);
        long chars = 0;
        int numOfChars = 0;
        String currentLine;
        String currentDecodedLine;
        Scanner codedReader;
        BufferedWriter uncoder;
        Node tree;

        treeMaker = new TreeMaker(list, list.length);
        tree = treeMaker.getTree();

        try {
            File fileToSave = new File(pathForUncodedFile);
            codedReader = new Scanner(fileToRead);
            uncoder = new BufferedWriter(new FileWriter(fileToSave));
            while (codedReader.hasNextLine()) {
                currentDecodedLine = "";
                pointerForFindingDecodedChar = 0;
                currentLine = codedReader.nextLine();
                while (pointerForFindingDecodedChar < currentLine.length()) {
                    currentDecodedLine += findDecodedChar(tree, list, currentLine);
                    chars++;
                }
                uncoder.write(currentDecodedLine);
            }
            codedReader.close();
            uncoder.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        if (chars > Integer.MAX_VALUE) {
            throw new IllegalArgumentException("Number of bits is too big for int!");
        }
        numOfChars = (int) chars;
        return numOfChars;
    }

    private Node[] decodeList(File dictFile) {
        String currentLine;
        int pointerForNewNodes = 0;
        try (Scanner dictScanner = new Scanner(dictFile)) {
            int sizeOfList;
            sizeOfList = Integer.parseInt(dictScanner.nextLine()); 
            if (sizeOfList < 1) {
                throw new IllegalArgumentException("Incorrect number of different chars in dictionary!");
            }
            Node[] newListOfNodes = new Node[sizeOfList--];
            while (dictScanner.hasNextLine() && sizeOfList >= 0) {

                currentLine = dictScanner.nextLine();

                String[] ingredientsOfNewNode = currentLine.split(" ");

                int actualFrequency = Integer.parseInt(ingredientsOfNewNode[1]);
                int actualChar = Integer.parseInt(ingredientsOfNewNode[0]);
                newListOfNodes[pointerForNewNodes] = new Node(actualFrequency, (char) actualChar, ingredientsOfNewNode[2]);
                pointerForNewNodes++;
            }
            if (pointerForNewNodes != newListOfNodes.length) {
                throw new IllegalArgumentException("Incorect number of different chars in file!");
            }
            return newListOfNodes;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private char findDecodedChar(Node tree, Node[] lista, String line) {
        Node it = tree;
        while (it.getLeftChild() != null && it.getRightChild() != null) {
            if (line.charAt(pointerForFindingDecodedChar) == '0') {
                it = it.leftChild;
                pointerForFindingDecodedChar++;
            } else if (line.charAt(pointerForFindingDecodedChar) == '1') {
                it = it.rightChild;
                pointerForFindingDecodedChar++;
            } else {
                throw new IndexOutOfBoundsException();
            }
        }
        return it.getSign();
    }
    
}
