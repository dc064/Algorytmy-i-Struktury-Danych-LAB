package pl.edu.pw.ee;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import pl.edu.pw.ee.services.HashTable;

public class HashQuadraticProbingTest {

    private String[] words;

    @Before
    public void init() throws IOException {
        words = new String[100000];
        File fileToRead = new File("stotysiecyslow.txt");
        BufferedReader br = new BufferedReader(new FileReader(fileToRead));
        for (int i = 0; i < words.length; i++) {
            words[i] = br.readLine();
        }
        br.close();
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_WhenInitialSizeIsLowerThanOne() {
        // given
        int initialSize = 0;

        // when
        HashTable<Double> hash = new HashQuadraticProbing<>(initialSize);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_WhenObjectToPutIsNull() {
        // given
        int initialSize = 1;

        // when
        HashTable<Double> hash = new HashQuadraticProbing<>(initialSize);
        hash.put(null);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_WhenObjectToGetIsNull() {
        // given
        int initialSize = 1;
        HashTable<Double> hash = new HashQuadraticProbing<>(initialSize);

        // when
        hash.put(4.5);
        hash.put(null);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_WhenFactorAEqualsZero() {
        // given
        int initialSize = 10;
        double a = 2.4;
        double b = 0.0;

        // when
        HashTable<Double> hash = new HashQuadraticProbing<>(initialSize, a, b);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_WhenFactorBEqualsZero() {
        // given
        int initialSize = 10;
        double a = 2.4;
        double b = 0.0;

        // when
        HashTable<Double> hash = new HashQuadraticProbing<>(initialSize, a, b);

        // then
        assert false;
    }

    @Test
    public void should_CorrectlyAddNewElems_WhenNotExistInHashTable() {
        // given
        HashTable<String> emptyHash = new HashQuadraticProbing<>();
        String newEleme = "nothing special";

        // when
        int nOfElemsBeforePut = getFieldFromHash(emptyHash, "nElems");
        emptyHash.put(newEleme);
        int nOfElemsAfterPut = getFieldFromHash(emptyHash, "nElems");

        // then
        assertEquals(0, nOfElemsBeforePut);
        assertEquals(1, nOfElemsAfterPut);
    }

    @Test
    public void should_OverwriteDuplicate_WhenElementExistsInHashTable() {
        // given
        HashTable<String> emptyHash = new HashQuadraticProbing<>();
        String newEleme = "nothing special";

        // when
        emptyHash.put(newEleme);
        int nOfElemsAfterPut = getFieldFromHash(emptyHash, "nElems");
        emptyHash.put(newEleme);
        int nOfElemsAfterSecondPut = getFieldFromHash(emptyHash, "nElems");

        // then
        assertEquals(1, nOfElemsAfterPut);
        assertEquals(1, nOfElemsAfterSecondPut);
    }

    @Test
    public void should_CorrectlyAddAndGetNewElems() {
        // given
        HashTable<String> emptyHash = new HashQuadraticProbing<>();
        String newEleme = "nothing special";

        // when
        emptyHash.put(newEleme);
        String get = emptyHash.get(newEleme);

        // then
        assertEquals(newEleme, get);
    }

    @Test
    public void should_CorrectlyAddAndRemoveNewElems() {
        // given
        HashTable<String> emptyHash = new HashLinearProbing<>();
        String newEleme = "nothing special";

        // when
        int nOfElemsBeforePut = getFieldFromHash(emptyHash, "nElems");
        emptyHash.put(newEleme);
        int nOfElemsAfterPut = getFieldFromHash(emptyHash, "nElems");
        emptyHash.delete(newEleme);
        int nOfElemsAfterDelete = getFieldFromHash(emptyHash, "nElems");

        // then
        assertEquals(0, nOfElemsBeforePut);
        assertEquals(1, nOfElemsAfterPut);
        assertEquals(0, nOfElemsAfterDelete);
    }

    @Test
    public void should_CorrectlyAddElemsAndResizeHashWhenNeeded() {
        // given
        HashTable<String> emptyHash = new HashQuadraticProbing<>(1);
        String newEleme1 = "AISD 2021";
        String newEleme2 = "Warsaw University of Technologies";
        String newEleme3 = "Computer Science";

        // when
        int nOfElemsBeforePut = getFieldFromHash(emptyHash, "nElems");
        emptyHash.put(newEleme1);
        emptyHash.put(newEleme2);
        emptyHash.put(newEleme3);

        int nOfElemsAfterPut = getFieldFromHash(emptyHash, "nElems");
        int sizeOfHashAfterAddElems = getFieldFromHash(emptyHash, "size");

        // then
        assertEquals(0, nOfElemsBeforePut);
        assertEquals(3, nOfElemsAfterPut);
        assertEquals(4, sizeOfHashAfterAddElems);
    }

    private int getFieldFromHash(HashTable<?> hash, String numToGet) {
        try {
            System.out.println(hash.getClass().getSuperclass().getName());
            Field field = hash.getClass().getSuperclass().getDeclaredField(numToGet);
            field.setAccessible(true);

            int numOfElems = field.getInt(hash);

            return numOfElems;

        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void timeTest() throws IOException {

        HashTable<String> testedHash;
        long timeBeforeStartPutting;
        long timeAfterFinishPutting;
        long timeBeforeStartGetting;
        long timeAfterFinishGetting;
        long averagePutTime;
        long averageGetTime;
        int sizeOfHash;
        double actualFactorA;
        double actualFactorB;
        double[] factorsA = { 3.0, 666.2, 1.2, -4.4, -5.1, 193, 67, 0.1, 42, 12 };
        double[] factorsB = { 4.0, 37.9, 21, -5.3, 2, 192, -6.7, 32.1, 1.1, 5.6 };
        long[] putTime = new long[30];
        long[] getTime = new long[30];
        String[] wordsToFind = new String[100000];
        File fileToWrite = new File("HashQuadraticProbingTimeTest.txt");
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileToWrite));
        for (int c = 0; c < 10; c++) {

            sizeOfHash = (int) ((Math.pow(2, c)) * 512);
            bw.write("\nSize: " + sizeOfHash + " , ");
            for (int i = 0; i < 10; i++) {
                actualFactorA = factorsA[i];
                actualFactorB = factorsB[i];
                bw.write("a: " + actualFactorA + " b: " + actualFactorB+ ", ");
                for (int j = 0; j < 30; j++) {

                    testedHash = new HashQuadraticProbing<>(sizeOfHash, actualFactorA, actualFactorB);

                    timeBeforeStartPutting = System.nanoTime();
                    for (int z = 0; z < words.length; z++) {
                        testedHash.put(words[z]);
                    }
                    timeAfterFinishPutting = System.nanoTime();
                    putTime[j] = timeAfterFinishPutting - timeBeforeStartPutting;

                    timeBeforeStartGetting = System.nanoTime();
                    for (int k = 0; k < words.length; k++) {
                        wordsToFind[k] = testedHash.get(words[k]);
                    }
                    timeAfterFinishGetting = System.nanoTime();

                    getTime[j] = timeAfterFinishGetting - timeBeforeStartGetting;
                }

                Arrays.sort(putTime);
                Arrays.sort(getTime);

                averagePutTime = 0;
                averageGetTime = 0;

                for (int g = 10; g < 20; g++) {
                    averagePutTime += 0.1 * putTime[g];
                    averageGetTime += 0.1 * getTime[g];
                }

                bw.write((int) averagePutTime + ", " + averageGetTime + ", ");
            }
        }
        bw.close();
    }

}
