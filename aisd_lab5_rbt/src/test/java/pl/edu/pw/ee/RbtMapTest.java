package pl.edu.pw.ee;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class RbtMapTest {
    private RbtMap<String, String> rbtMap;
    private String [] words;

    @Before
    public void setUp() throws IOException {
        rbtMap = new RbtMap<String, String>();
        words = new String[1000000];
        File fileToRead = new File("milionslow.txt");
        BufferedReader br = new BufferedReader(new FileReader(fileToRead));
        for (int i = 0; i < words.length; i++) {
            words[i] = br.readLine();
        }
        br.close();
    }

    @Test
    public void shouldCorrectlyAddNewElemToTheTree() {
        // given
        rbtMap.setValue("1", "nothing special.");

        // when
        String value;
        value = rbtMap.getValue("1");

        // then
        assertEquals(value, "nothing special.");
    }

    @Test
    public void shouldFillArrayWith_MultipleElemsAddedToTheTree() {
        // given
        rbtMap.setValue("0", "Red");
        rbtMap.setValue("1", "Black");
        rbtMap.setValue("2", "Tree");
        String[] filledArray = { "Red", "Black", "Tree" };

        // when
        String[] arrayToFill = new String[3];
        for (int i = 0; i < arrayToFill.length; i++) {
            arrayToFill[i] = rbtMap.getValue("" + i);
        }

        // then
        assertArrayEquals(filledArray, arrayToFill);
    }

    @Test
    public void shouldReturnNullWhen_SearchElementDoesNotExist() {
        // given
        rbtMap.setValue("1", "a");
        rbtMap.setValue("2", "b");

        // when
        String value;
        value = rbtMap.getValue("3");

        // then
        assertEquals(value, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhen_KeyIsNull() {
        // given
        rbtMap = new RbtMap<String, String>();

        // when
        rbtMap.setValue(null, "To brzmi absurdalnie.");

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhen_ValueIsNull() {
        // given
        rbtMap = new RbtMap<String, String>();

        // when
        rbtMap.setValue("1", null);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhen_KeyAndValueAreNull() {
        // given
        rbtMap = new RbtMap<String, String>();

        // when
        rbtMap.setValue(null, null);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhen_KeyOfSearchedElementIsNull() {
        // given
        rbtMap = new RbtMap<String, String>();
        rbtMap.setValue("Ala", "Ala");
        rbtMap.setValue("Ma", "Ma");
        rbtMap.setValue("Kota", "Kota");

        // when
        rbtMap.getValue(null);

        // then
        assert false;
    }

    @Test
    public void shouldOverwriteValueWhen_TryToPutElemWithKeyThatExistsInTree() {
        // given
        rbtMap = new RbtMap<String, String>();

        // when
        rbtMap.setValue("1", "First value of the element.");
        rbtMap.setValue("1", "Second value of the element.");
        String value = rbtMap.getValue("1");

        // then
        assertEquals(value, "Second value of the element.");
    }

    @Test
    public void shouldCorrectly_AddOneMilionElems_AndFillArray(){
        //given
        String [] arrayToFill = new String[1000000];

        //when
        for(int i = 0; i < words.length; i++) {
            rbtMap.setValue(words[i], words[i]);
        }

        for(int i = 0; i < words.length; i++) {
            arrayToFill[i] = rbtMap.getValue(words[i]);
        }

        //then
        assertArrayEquals(words, arrayToFill);
    }

}
