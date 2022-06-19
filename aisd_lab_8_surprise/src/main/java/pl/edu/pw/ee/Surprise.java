package pl.edu.pw.ee;

public class Surprise {
    public int countCombinationsOfScreenings (int [] screenings) {
        if (screenings == null) {
            throw new IllegalArgumentException();
        }
        if (screenings.length < 4) {
            throw new IllegalArgumentException();
        }
        int differentScreenings = 0;
        int [] moviesAppeared = new int[screenings.length];
        boolean duplicated;
        for (int i = 0; i < screenings.length; i++) {
            duplicated = false;
            for (int j=0; j < differentScreenings; j++) {
                if (screenings[i] == moviesAppeared[j])) {
                    duplicated = true;
                }
            }
            if (!duplicated) {
                moviesAppeared[differentScreenings++] = screenings[i];
            }
        }
        int a=1;
        for (int i=differentScreenings; i>0; i--) {
            a *= i;
        }
        int b=1;
        for (int i=differentScreenings-3; i>0; i--) {
            b *= i;
        }
        int numberOfCombinations = a / b;
        return numberOfCombinations;
    }
}
