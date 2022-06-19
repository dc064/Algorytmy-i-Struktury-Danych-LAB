package pl.edu.pw.ee;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class RedBlackTreeTest {
    
    private RedBlackTree<String, String> rbt;

    private String [] words;
    
    @Before
    public void setUp() throws IOException {
        rbt = new RedBlackTree<String, String>();
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
        rbt.put("1", "abcd");

        // when
        String value;
        value = (String) rbt.get("1");
        
        // then
        assertTrue(value == "abcd");
    }

    @Test
    public void shouldCorrectlyAddNewMultipleElemsToTheTree() {
        // given
        String [] values = {"1", "2", "3", "4"};
        String [] valuesArrayToFill = new String[4];

        // when
        rbt.put("1", "1");
        rbt.put("2", "2");
        rbt.put("3", "3");
        rbt.put("4", "4");

        for (int i=0; i < values.length; i++) {
            valuesArrayToFill[i] = rbt.get("" + (i+1));
        }
        // then
        assertArrayEquals(values, valuesArrayToFill);
    }

    @Test
    public void shouldOverwriteValue_WhenGivenKeyExistsInTheTree() {
        // given
        String firstValue = "First value.";
        String secondValue = "Second value.";

        // when
        rbt.put("1", firstValue);
        String valueBeforeChanging = rbt.get("1");
        rbt.put("1", secondValue);
        String valueAfterChanging = rbt.get("1");

        // then
        assertEquals(valueBeforeChanging, "First value.");
        assertEquals(valueAfterChanging, "Second value.");

    }

    @Test
    public void shouldReturnNullWhen_SearchedElementWasDeleted() {
        // given
        String value;

        // when
        rbt.put("1", "1");
        rbt.put("2", "1");
        rbt.put("3", "1");

        rbt.deleteMax();
        value = rbt.get("3");
        
        // then
        assertEquals(value, null);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionWhen_TryToDeleteFromEmptyRoot() {
        // given
        rbt = new RedBlackTree<>();

        // when
        rbt.deleteMax();
        
        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhen_KeyIsNull() {
        // given
        rbt = new RedBlackTree<String, String>();
        // when
        rbt.put(null, "To brzmi absurdalnie.");
        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhen_ValueIsNull() {
        // given
        rbt = new RedBlackTree<String, String>();

        // when
        rbt.put("1", null);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhen_KeyAndValueAreNull() {
        // given
        rbt = new RedBlackTree<String, String>();

        // when
        rbt.put(null, null);

        // then
        assert false;
    }

    @Test
    public void shouldReturnCorrectString_PreOrder() {
        //given
        String letters = "abcdefghijklm";
        String expected = "h:h d:d b:b a:a c:c f:f e:e g:g l:l j:j i:i k:k m:m ";

        //when
        for (int i = 0; i < letters.length(); i++) {
            rbt.put( "" + letters.charAt(i), "" + letters.charAt(i));
        }
        String result = rbt.getPreOrder();

        //then
        assertEquals(expected, result);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionByTryingGetPreOrderWhen_RootIsNull() {
        //given
        rbt = new RedBlackTree<>();

        //when
        String stringPreOrder = rbt.getPreOrder();

        //then
        assert false;
    }
    
    @Test
    public void shouldReturnCorrectString_InOrder() {
        //given
        String letters = "abcdefghijklm";
        String expected = "a:a b:b c:c d:d e:e f:f g:g h:h i:i j:j k:k l:l m:m ";
        String result;

        //when
        for (int i = 0; i < letters.length(); i++) {
            rbt.put( "" + letters.charAt(i), "" + letters.charAt(i));
        }

        result = rbt.getInOrder();

        //then
        assertEquals(expected, result);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionByTryingGetInOrderWhen_RootIsNull() {
        //given
        rbt = new RedBlackTree<>();
        String stringInOrder;

        //when
        stringInOrder = rbt.getInOrder();

        //then
        assert false;
    }
    @Test
    public void shouldReturnCorrectString_PostOrder() {
        //given
        String letters = "abcdefghijklm";
        String result;
        String expected = "a:a c:c b:b e:e g:g f:f d:d i:i k:k j:j m:m l:l h:h ";

        //when
        for (int i = 0; i < letters.length(); i++) {
            rbt.put( "" + letters.charAt(i) , "" + letters.charAt(i));
        }

        result = rbt.getPostOrder();

        //then
        assertEquals(expected, result);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionByTryingPostInOrderWhen_RootIsNull() {
        //given
        rbt = new RedBlackTree<>();

        //when
        String stringPreOrder = rbt.getPostOrder();

        //then
        assert false;
    }

    @Test
    public void shouldCorrectly_AddOneMilionElems_AndFillArray(){
        //given
        String [] arrayToFill = new String[1000000];

        //when
        for(int i = 0; i < words.length; i++) {
            rbt.put(words[i], words[i]);
        }

        for(int i = 0; i < words.length; i++) {
            arrayToFill[i] = rbt.get(words[i]);
        }

        //then
        assertArrayEquals(words, arrayToFill);
    }
}