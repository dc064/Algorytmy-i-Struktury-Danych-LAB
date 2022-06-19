package pl.edu.pw.ee;

class LongestCommonSubsequence {

    private Grille[][] matrix;
    private String topStr;
    private String leftStr;
    private int firstDimension;
    private int secondDimension;
    private boolean ifLCSHasBeenFounded = false;
    private int maxCharLengthOfNumber;

    private class Grille {
        private int number = 0;
        private Side side;
        private boolean arrowVisibility = false;

        public Grille(int number) {
            this.number = number;
        }

        public void setSide(Side side) {
            this.side = side;
        }

        public void setVisibility(boolean arrowVisibility) {
            this.arrowVisibility = arrowVisibility;
        }
    }

    public LongestCommonSubsequence(String topStr, String leftStr) {
        if (topStr == null || leftStr == null) {
            throw new IllegalArgumentException("Found null in required arguments!");
        } else if (topStr.length() == 0 || leftStr.length() == 0) {
            throw new IllegalArgumentException(
                    "Cannot create new instance of the class: String with length 0 founded!");
        }
        this.topStr = " " + topStr;
        this.leftStr = " " + leftStr;
        this.firstDimension = this.topStr.length();
        this.secondDimension = this.leftStr.length();
        this.matrix = createMatrix();
    }

    public String findLCS() {
        String lcs = "";
        if (matrix == null || topStr == null || leftStr == null) {
            throw new NullPointerException("Found null in required arguments!");
        }

        fillMatrixWithNumbers();

        int firstDimensionForPointer = firstDimension - 1;
        int secondDimensionForPointer = secondDimension - 1;
        Grille pointer = matrix[firstDimensionForPointer][secondDimensionForPointer];
        maxCharLengthOfNumber = numberOfRequiredSpaces(pointer.number);
        while (pointer.number > 0 && firstDimensionForPointer >= 0 && secondDimensionForPointer >= 0) {
            matrix[firstDimensionForPointer][secondDimensionForPointer].setVisibility(true);
            if (pointer.side == Side.DIAGONAL) {
                lcs += topStr.charAt(firstDimensionForPointer);
                firstDimensionForPointer--;
                secondDimensionForPointer--;
            } else if (pointer.side == Side.UP) {
                secondDimensionForPointer--;
            } else {
                firstDimensionForPointer--;
            }

            pointer = matrix[firstDimensionForPointer][secondDimensionForPointer];
        }

        lcs = reverseString(lcs);
        ifLCSHasBeenFounded = true;
        return lcs;
    }

    private int numberOfRequiredSpaces(int number) {
        return String.valueOf(number).length();
    }

    private void fillMatrixWithNumbers() {
        for (int i = 1; i < secondDimension; i++) {
            for (int j = 1; j < firstDimension; j++) {
                if (topStr.charAt(j) == leftStr.charAt(i)) {
                    matrix[j][i].number = (matrix[j - 1][i - 1].number) + 1;
                    matrix[j][i].setSide(Side.DIAGONAL);
                } else if (matrix[j - 1][i].number > matrix[j][i - 1].number) {
                    matrix[j][i].number = matrix[j - 1][i].number;
                    matrix[j][i].setSide(Side.LEFT);
                } else {
                    matrix[j][i].number = matrix[j][i - 1].number;
                    matrix[j][i].setSide(Side.UP);
                }
            }
        }
    }

    public void display() {
        if (!ifLCSHasBeenFounded) {
            throw new IllegalArgumentException("Cannot display matrix because LCD has not been found yet!");
        }
        String space = " ";
        String[] topStringDividedIntoChars = new String[firstDimension];
        String[] leftStringDividedIntoChars = new String[secondDimension];
        char[] specialChars = { '\n', '\t', '\b', '\t', '\n', '\f', '\r', '\"', '\'', '\\' };
        String[] specialCharsToStrings = { "\\n", "\\t", "\\b", "\\t", "\\n", "\\f", "\\r", "\"", "\'", "\\" };
        String currentStringForDisplaying;
        char currentArrowForDisplaying;
        int numberOfSpacesForCorner = 2;

        for (int i = 0; i < firstDimension; i++) {
            topStringDividedIntoChars[i] = topStr.charAt(i) + " ";
            for (int j = 0; j < specialChars.length; j++) {
                if (specialChars[j] == topStr.charAt(i)) {
                    topStringDividedIntoChars[i] = specialCharsToStrings[j];
                }
            }
        }
        for (int i = 0; i < secondDimension; i++) {
            leftStringDividedIntoChars[i] = leftStr.charAt(i) + " ";
            for (int j = 0; j < specialChars.length; j++) {
                if (specialChars[j] == leftStr.charAt(i)) {
                    leftStringDividedIntoChars[i] = specialCharsToStrings[j];
                }
            }
        }

        System.out.print(space.repeat(numberOfSpacesForCorner));
        for (int i = 0; i < firstDimension; i++) {
            System.out.print(topStringDividedIntoChars[i] + space.repeat(maxCharLengthOfNumber - 1) + "|");
        }
        System.out.print("\n\n");
        for (int j = 0; j < secondDimension; j++) {
            System.out.print(leftStringDividedIntoChars[j]);
            for (int i = 0; i < firstDimension; i++) {

                currentStringForDisplaying = "";

                if (matrix[i][j].arrowVisibility) {
                    if (matrix[i][j].side == Side.DIAGONAL) {
                        currentArrowForDisplaying = '\\';
                    }
                    else if (matrix[i][j].side == Side.LEFT) {
                        currentArrowForDisplaying = '<';
                    }
                    else {
                        currentArrowForDisplaying = '^';
                    }
                    currentStringForDisplaying += currentArrowForDisplaying;
                }

                else {
                    currentStringForDisplaying += space;
                }

                currentStringForDisplaying += space.repeat(maxCharLengthOfNumber);

                System.out.print(currentStringForDisplaying + "|");
            }

            System.out.print("\n  ");

            for (int i = 0; i < firstDimension; i++) {
                int numberOfSpaces = maxCharLengthOfNumber - numberOfRequiredSpaces(matrix[i][j].number) + 1;
                currentStringForDisplaying = space.repeat(numberOfSpaces) + matrix[i][j].number;
                System.out.print(currentStringForDisplaying + "|");
            }
            System.out.print("\n\n");
        }

    }

    private Grille[][] createMatrix() {
        if (topStr == null || leftStr == null) {
            throw new IllegalArgumentException("Given argument cannot be null!");
        }
        Grille[][] matrix = new Grille[firstDimension][secondDimension];
        for (int i = 0; i < firstDimension; i++) {
            for (int j = 0; j < secondDimension; j++) {
                matrix[i][j] = new Grille(0);
            }
        }
        return matrix;
    }

    private String reverseString(String stringToReverse) {
        if (stringToReverse == null) {
            throw new IllegalArgumentException("String to reverse cannot be null!");
        }
        String reversedString = "";
        for (int i = stringToReverse.length() - 1; i >= 0; i--) {
            reversedString += stringToReverse.charAt(i);
        }
        return reversedString;
    }
}
