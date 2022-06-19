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

public class HashDoubleHashingTest {

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
        HashTable<Double> hash = new HashDoubleHashing<>(initialSize);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_WhenObjectToPutIsNull() {
        // given
        int initialSize = 1;

        // when
        HashTable<Double> hash = new HashDoubleHashing<>(initialSize);
        hash.put(null);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_WhenObjectToGetIsNull() {
        // given
        int initialSize = 1;
        HashTable<Double> hash = new HashDoubleHashing<>(initialSize);

        // when
        hash.put(7.7);
        hash.get(null);

        // then
        assert false;
    }

    @Test
    public void should_CorrectlyAddNewElems_WhenNotExistInHashTable() {
        // given
        HashTable<String> emptyHash = new HashDoubleHashing<>();
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
        HashTable<String> emptyHash = new HashDoubleHashing<>();
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
        HashTable<String> emptyHash = new HashDoubleHashing<>();
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
        HashTable<String> emptyHash = new HashDoubleHashing<>();
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
    public void should_CorrectlyAddFewElemsAndResizeHashWhenNeeded() {
        // given
        HashTable<String> emptyHash = new HashDoubleHashing<>(1);
        String newEleme1 = "Pochodna po elemencie z niepewnoscia";
        String newEleme2 = "Razy ta niepewnosc";
        String newEleme3 = "Iiiiiii...";
        String newEleme4 = "Suma geometryczna ich!";

        // when
        int nOfElemsBeforePut = getFieldFromHash(emptyHash, "nElems");
        emptyHash.put(newEleme1);
        emptyHash.put(newEleme2);
        emptyHash.put(newEleme3);
        emptyHash.put(newEleme4);

        int nOfElemsAfterPut = getFieldFromHash(emptyHash, "nElems");
        int sizeOfHashAfterPut = getFieldFromHash(emptyHash, "size");
        // then
        assertEquals(0, nOfElemsBeforePut);
        assertEquals(4, nOfElemsAfterPut);
        assertEquals(8, sizeOfHashAfterPut);
    }

    @Test
    public void shouldCorrectlyAddAndReturnMultipleElems() {
        // given
        HashTable<String> emptyHash = new HashDoubleHashing<>(20);
        String newEleme1 = "AISD 2021";
        String newEleme2 = "Warsaw University of Technologies";
        String newEleme3 = "Computer Science";

        String newEleme1ToGet;
        String newEleme2ToGet;
        String newEleme3ToGet;

        // when
        emptyHash.put(newEleme1);
        emptyHash.put(newEleme2);
        emptyHash.put(newEleme3);

        newEleme1ToGet = emptyHash.get("AISD 2021");
        newEleme2ToGet = emptyHash.get("Warsaw University of Technologies");
        newEleme3ToGet = emptyHash.get("Computer Science");

        // then
        assertEquals(newEleme1, newEleme1ToGet);
        assertEquals(newEleme2, newEleme2ToGet);
        assertEquals(newEleme3, newEleme3ToGet);
    }

    private int getFieldFromHash(HashTable<?> hash, String nameOfFieldToGet) {
        try {
            System.out.println(hash.getClass().getSuperclass().getName());
            Field field = hash.getClass().getSuperclass().getDeclaredField(nameOfFieldToGet);
            field.setAccessible(true);

            int searchedField = field.getInt(hash);

            return searchedField;

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
        long [] putTime = new long[30];
        long [] getTime = new long[30];
        String[] wordsToFind = new String[100000];
        File fileToWrite = new File("HashDoubleTimeProbingTest.txt");
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileToWrite));

        for (int i = 0; i < 10; i++) {

            sizeOfHash = (int) ((Math.pow(2, i)) * 512);
            bw.write("For hash size " + sizeOfHash + "\n");

            // Wstawianie
            for (int j = 0; j < 30; j++) {
            
                testedHash = new HashDoubleHashing<>(sizeOfHash);

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

            bw.write((int) averageGetTime + "\n" + averagePutTime + "\n");
        }
        bw.close();
    }

}
