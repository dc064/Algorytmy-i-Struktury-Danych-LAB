package pl.edu.pw.ee;

import static java.lang.Math.min;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import pl.edu.pw.ee.services.PatternSearch;

public class DeterministicFiniteAutomatonTextSearch implements PatternSearch {

    private class Key {
        private int state;
        private char sign;

        public Key(int state, char sign) {
            this.state = state;
            this.sign = sign;
        }

        @Override
        public boolean equals(Object o) {
            if (o == this)
                return true;
            if (!(o instanceof Key)) {
                return false;
            }
            Key key = (Key) o;
            return state == key.state && sign == key.sign;
        }

        @Override
        public int hashCode() {
            return Objects.hash(state, sign);
        }
    }

    private String pattern;
    private Map<Key, Integer> transMap;

    public DeterministicFiniteAutomatonTextSearch(String pattern) {
        validateInput(pattern);

        this.pattern = pattern;
        buildTransitionMatrix();
    }

    @Override
    public int findFirst(String text) {
        validateInput(text);
        int n = text.length();
        int acceptedState = pattern.length();
        int result = -1;

        Integer state = 0;

        for (int i = 0; i < n; i++) {
            state = transMap.get(new Key(state, text.charAt(i)));
            if (state == null) {
                state = 0;
            }

            if (state == acceptedState) {
                result = i - acceptedState + 1;
                break;
            }
        }

        return result;
    }

    @Override
    public int[] findAll(String text) {
        validateInput(text);
        int n = text.length();
        int acceptedState = pattern.length();
        int result = -1;
        ArrayList<Integer> listOfStates = new ArrayList<>();
        int numberOfFoundStates = 0;

        Integer state = 0;

        for (int i = 0; i < n; i++) {
            state = transMap.get(new Key(state, text.charAt(i)));
            if (state == null) {
                state = 0;
            }

            if (state == acceptedState) {
                numberOfFoundStates++;

                result = i - acceptedState + 1;
                listOfStates.add(result);
            }
        }

        if (numberOfFoundStates == 0) {
            return new int[0];
        }
        int [] list = new int[listOfStates.size()];
        for (int i=0; i<listOfStates.size(); i++) {
            list[i] = listOfStates.get(i);
        }
        return list;
    }

    private void validateInput(String txt) {
        if (txt == null) {
            throw new IllegalArgumentException("Input text cannot be null!");
        }

        else if (txt.length() == 0) {
            throw new IllegalArgumentException("Input text cannot be empty!");
        }
    }

    private void buildTransitionMatrix() {
        transMap = new HashMap<>();

        int m = pattern.length();
        List<Character> alphabet = getAlphabetOfPattern();

        for (int q = 0; q <= m; q++) {
            for (char sign : alphabet) {

                int k = min(m + 1, q + 2);
                k--;

                while (k > 0 && !isSuffixOfPattern(k, q, sign)) {
                    k--;
                }
                transMap.put(new Key(q, sign), k);
            }
        }
    }

    private List<Character> getAlphabetOfPattern() {
        List<Character> signs = pattern.chars()
                .distinct()
                .mapToObj(c -> (char) c)
                .collect(toList());

        return signs;
    }

    private boolean isSuffixOfPattern(int kIndex, int qIndex, char sign) {
        boolean isSuffix = false;

        if (pattern.charAt(--kIndex) == sign) {
            isSuffix = true;

            while (kIndex > 0) {
                kIndex--;
                qIndex--;

                if (pattern.charAt(kIndex) != pattern.charAt(qIndex)) {
                    isSuffix = false;
                    break;
                }
            }
        }

        return isSuffix;
    }

}
