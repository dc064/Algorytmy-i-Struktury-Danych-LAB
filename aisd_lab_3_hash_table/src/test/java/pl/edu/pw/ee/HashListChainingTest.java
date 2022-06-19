package pl.edu.pw.ee;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class HashListChainingTest {
    private HashListChaining hashListChaining;
    private Object[] words;
    private File fileToRead;
    private BufferedReader br;

    @Before
    public void setUp() throws IOException {
        hashListChaining = new HashListChaining(100);
        words = new Object[100000];
        fileToRead = new File("stotysiecyslow.txt");
        br = new BufferedReader(new FileReader(fileToRead));
        for (int i = 0; i < words.length; i++) {
            words[i] = br.readLine();
        }
        br.close();
    }

    @Test
    public void shouldReturnObjectAddedToHash() {
        // given
        hashListChaining.add("aaa");
        Object expected = "aaa";

        // when
        Object fromHash = hashListChaining.get("aaa");

        // then
        assertEquals(expected, fromHash);
    }

    @Test
    public void shouldReturnNullWhen_SerachedElementIsNotInTheHashList() {
        // given
        hashListChaining.add("bbb");
        hashListChaining.add("ccc");
        Object expected = null;

        // when
        Object fromHash = hashListChaining.get("ddd");

        // then
        assertEquals(expected, fromHash);
    }

    @Test
    public void shouldReturnNullWhen_ObjectWasDeleted() {
        // given
        hashListChaining.add("eee");
        hashListChaining.add("fff");
        hashListChaining.add("ggg");
        Object expected = null;

        // when
        hashListChaining.delete("fff");
        Object fromHash = hashListChaining.get("fff");

        // then
        assertEquals(expected, fromHash);
    }

    @Test
    public void shouldNotAdd_Duplicat() {
        // given
        hashListChaining.add("hhh");
        hashListChaining.add("hhh");
        hashListChaining.add("iii");
        Object expected = null;

        // when
        hashListChaining.delete("hhh");
        Object fromHash = hashListChaining.get("hhh");

        // then
        assertEquals(expected, fromHash);
    }

    @Test
    public void ShouldFillArrayWithWordsWeGet() {
        // given
        Object[] wordsArrayToFill = new Object[100000];
        for (int i = 0; i < words.length; i++) {
            hashListChaining.add(words[i]);
        }

        // when
        for (int i = 0; i < words.length; i++) {
            wordsArrayToFill[i] = hashListChaining.get(words[i]);
        }

        // then
        assertArrayEquals(words, wordsArrayToFill);
    }

    @Test
    public void ShouldFillArrayWithDifferentTypesOfObject() {
        // given
        Object[] objectsToGet = { true, 2.3, "Anna", 36, 'd', false };
        Object[] arrayToFill = new Object[6];

        for (int i = 0; i < objectsToGet.length; i++) {
            hashListChaining.add(objectsToGet[i]);
        }

        // when
        for (int i = 0; i < objectsToGet.length; i++) {
            arrayToFill[i] = hashListChaining.get(objectsToGet[i]);
        }

        // then
        assertArrayEquals(objectsToGet, arrayToFill);
    }

    @Test
    public void countHashIdShouldBePositiveWhenInteger_MIN_VALUE_IsGiven() {
        // given
        hashListChaining = new HashListChaining(100);

        // when
        int x = hashListChaining.countHashId(Integer.MIN_VALUE);

        // then
        assertTrue(x >= 0);
    }

    @Test
    public void timeTest() throws IOException {
        long timeBeforeStart;
        long timeAfterFinish;
        Object[] wordsToFind = new Object[100000];
        File fileToWrite = new File("wyniki.txt");
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileToWrite));

        br.close();
        for (int i = 0; i < 7; i++) {

            hashListChaining = new HashListChaining((int) Math.pow(2, i) * 4096);
            bw.write("Dla listy o długości " + hashListChaining.getHashSize() + "\n");
            for (int z = 0; z < words.length; z++) {
                hashListChaining.add(words[z]);
            }

            for (int j = 0; j < 30; j++) {
                timeBeforeStart = System.nanoTime();
                for (int k = 0; k < words.length; k++) {
                    wordsToFind[k] = hashListChaining.get(words[k]);
                }
                timeAfterFinish = System.nanoTime();
                bw.write(timeAfterFinish - timeBeforeStart + "\n");
            }
        }
        bw.close();
    }

}