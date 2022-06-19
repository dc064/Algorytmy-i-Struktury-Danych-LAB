package pl.edu.pw.ee;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class TreeMakerTest {

    private Node [] listForTest;
    private Node [] listWithNull;

    @Before
    public void init() {
        listForTest = new Node[4];
        listWithNull = new Node[4];
        for (int i =0; i< listForTest.length; i++) {
            listForTest[i] = new Node(i + 1, (char) i, null, null);
            listWithNull[i] = listForTest[i];
        }
        listWithNull[2] = null;
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldReturnNullWnen_listIsNull() {
        //given
        int diffChars = 10;
        Node [] list = null;

        //when
        TreeMaker treeMaker = new TreeMaker(null, diffChars);

        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldReturnNullWnen_NumberOfCharsIsLowerThanOne() {
        //given
        int diffChars = 0;

        //when
        TreeMaker treeMaker = new TreeMaker(listForTest, diffChars);

        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldReturnRootWithCorrectFrequencyWnen_ThereIsOnlyOneNodeInList() {
        //given
        int diffChars = 1;
        Node [] oneElemList = new Node[1];
        oneElemList[0] = new Node(2, 'c', null, null);

        //when
        TreeMaker treeMaker = new TreeMaker(oneElemList, diffChars);

        //then
        assert false;
    }
    

    @Test(expected = IllegalArgumentException.class)
    public void shouldReturnNullWnen_ThereIsNullInList() {
        //given
        int diffChars = 3;

        //when
        TreeMaker treeMaker = new TreeMaker(listWithNull, diffChars);

        //then
        assert false;
    }

    @Test
    public void shouldReturnRootWithCorrectFrequencyWnen_AllAgrumentsAreCorrect() {
        //given
        int diffChars = 4;
        int expectedFrequencyOfRoot = 10;

        //when
        TreeMaker treeMaker = new TreeMaker(listForTest, diffChars);
        Node root = treeMaker.getTree();
        int frequencyOfRoot = root.getFrequency();

        //then
        assertEquals(expectedFrequencyOfRoot, frequencyOfRoot);
    }

    
}
