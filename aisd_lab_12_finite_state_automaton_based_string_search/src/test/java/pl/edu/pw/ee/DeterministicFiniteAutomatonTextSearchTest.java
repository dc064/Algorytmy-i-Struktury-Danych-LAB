package pl.edu.pw.ee;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Assert;
import org.junit.Test;

public class DeterministicFiniteAutomatonTextSearchTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhen_PatternIsNull() {
        //given
        String pattern = null;
        DeterministicFiniteAutomatonTextSearch deterministicFiniteAutomatonTextSearch;

        //when
        deterministicFiniteAutomatonTextSearch = new DeterministicFiniteAutomatonTextSearch(null);

        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhen_PatternIsEmpty() {
        //given
        String pattern = "";
        DeterministicFiniteAutomatonTextSearch deterministicFiniteAutomatonTextSearch;

        //when
        deterministicFiniteAutomatonTextSearch = new DeterministicFiniteAutomatonTextSearch(pattern);

        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhen_GivenTextToFindFirstIsEmpty() {
        //given
        String pattern = "pattern";
        DeterministicFiniteAutomatonTextSearch deterministicFiniteAutomatonTextSearch = new DeterministicFiniteAutomatonTextSearch(pattern);

        //when
        String text = "";
        int index = deterministicFiniteAutomatonTextSearch.findFirst(text);

        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhen_GivenTextToFindAllIsEmpty() {
        //given
        String pattern = "pattern";
        DeterministicFiniteAutomatonTextSearch deterministicFiniteAutomatonTextSearch = new DeterministicFiniteAutomatonTextSearch(pattern);

        //when
        String text = "";
        int [] indexes = deterministicFiniteAutomatonTextSearch.findAll(text);

        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhen_GivenTextToFindFirstIndexIsNull() {
        //given
        String pattern = "pattern";
        DeterministicFiniteAutomatonTextSearch deterministicFiniteAutomatonTextSearch = new DeterministicFiniteAutomatonTextSearch(pattern);

        //when
        String text = null;
        int index = deterministicFiniteAutomatonTextSearch.findFirst(text);

        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhen_GivenTextToFindAllIndexesIsNull() {
        //given
        String pattern = "pattern";
        DeterministicFiniteAutomatonTextSearch deterministicFiniteAutomatonTextSearch = new DeterministicFiniteAutomatonTextSearch(pattern);

        //when
        String text = null;
        int [] index = deterministicFiniteAutomatonTextSearch.findAll(text);

        //then
        assert false;
    }

    @Test
    public void ShouldReturnCorrectIndexWhen_PatternExistsOneTimeAtTheBegginingOfTheText() {
        //given
        String pattern = "polski";
        DeterministicFiniteAutomatonTextSearch deterministicFiniteAutomatonTextSearch = new DeterministicFiniteAutomatonTextSearch(pattern);
        int expected = 0;

        //when
        String text = "polski_lad";
        int index = deterministicFiniteAutomatonTextSearch.findFirst(text);

        //then
        assertEquals(expected, index);
    }

    @Test
    public void ShouldReturnCorrectIndexWhen_TryToFindFirstWhen_ThereAreTwoPatternsInTheText() {
        //given
        String pattern = "bar";
        DeterministicFiniteAutomatonTextSearch deterministicFiniteAutomatonTextSearch = new DeterministicFiniteAutomatonTextSearch(pattern);
        int expected = 2;

        //when
        String text = "rabarbar";
        int index = deterministicFiniteAutomatonTextSearch.findFirst(text);

        //then
        assertEquals(expected, index);
    }

    @Test
    public void ShouldReturnCorrectIndexWhen_PatternExistsOneTimeAtTheMiddleOfTheText() {
        //given
        String pattern = "nas";
        DeterministicFiniteAutomatonTextSearch deterministicFiniteAutomatonTextSearch = new DeterministicFiniteAutomatonTextSearch(pattern);
        int expected = 5;

        //when
        String text = "czternasty";
        int index = deterministicFiniteAutomatonTextSearch.findFirst(text);

        //then
        assertEquals(expected, index);
    }

    @Test
    public void ShouldReturnCorrectIndexWhen_PatternExistsOneTimeAtTheEndOfTheText() {
        //given
        String pattern = "gwiazda";
        DeterministicFiniteAutomatonTextSearch deterministicFiniteAutomatonTextSearch = new DeterministicFiniteAutomatonTextSearch(pattern);
        int expected = 3;

        //when
        String text = "rozgwiazda";
        int index = deterministicFiniteAutomatonTextSearch.findFirst(text);

        //then
        assertEquals(expected, index);
    }

    @Test
    public void ShouldReturnNegativeIndexWhen_PatternDoesNotExistsInTheText() {
        //given
        String pattern = "Skoda";
        DeterministicFiniteAutomatonTextSearch deterministicFiniteAutomatonTextSearch = new DeterministicFiniteAutomatonTextSearch(pattern);
        int expected = -1;

        //when
        String text = "Mercedes";
        int index = deterministicFiniteAutomatonTextSearch.findFirst(text);

        //then
        assertEquals(expected, index);
    }

    @Test
    public void ShouldReturnNegativeIndexWhen_ThereIsASubstringOfPatternInTheText() {
        //given
        String pattern = "kompot";
        DeterministicFiniteAutomatonTextSearch deterministicFiniteAutomatonTextSearch = new DeterministicFiniteAutomatonTextSearch(pattern);
        int expected = -1;

        //when
        String text = "kompocik";
        int index = deterministicFiniteAutomatonTextSearch.findFirst(text);

        //then
        assertEquals(expected, index);
    }

    @Test
    public void ShouldReturnNegativeIndexWhen_PatternIsDividedInTheText() {
        //given
        String pattern = "gok";
        DeterministicFiniteAutomatonTextSearch deterministicFiniteAutomatonTextSearch = new DeterministicFiniteAutomatonTextSearch(pattern);
        int expected = -1;

        //when
        String text = "bigosik";
        int index = deterministicFiniteAutomatonTextSearch.findFirst(text);

        //then
        assertEquals(expected, index);
    }

    @Test
    public void ShouldReturnCorrectArrayWithOneNumberWhen_TryToFindAllIndexesAnd_PatternIsOnlyOnceInTheText() {
        //given
        String pattern = "ba";
        DeterministicFiniteAutomatonTextSearch deterministicFiniteAutomatonTextSearch = new DeterministicFiniteAutomatonTextSearch(pattern);
        int [] expected = {2};

        //when
        String text = "kabaret";
        int [] index = deterministicFiniteAutomatonTextSearch.findAll(text);

        //then
        Assert.assertArrayEquals(expected, index);
    }

    @Test
    public void ShouldReturnCorrectArrayWhen_PatternIsAtTheBegginingAnd_AtTheMiddleOfTheText() {
        //given
        String pattern = "ba";
        DeterministicFiniteAutomatonTextSearch deterministicFiniteAutomatonTextSearch = new DeterministicFiniteAutomatonTextSearch(pattern);
        int [] expected = {0, 3};

        //when
        String text = "barbados";
        int [] index = deterministicFiniteAutomatonTextSearch.findAll(text);

        //then
        Assert.assertArrayEquals(expected, index);
    }

    @Test
    public void ShouldReturnCorrectArrayWhen_PatternIsAtTheBegginingAnd_AtTheEndOfTheText() {
        //given
        String pattern = "maj";
        DeterministicFiniteAutomatonTextSearch deterministicFiniteAutomatonTextSearch = new DeterministicFiniteAutomatonTextSearch(pattern);
        int [] expected = {0, 7};

        //when
        String text = "majorkamaj";
        int [] index = deterministicFiniteAutomatonTextSearch.findAll(text);

        //then
        Assert.assertArrayEquals(expected, index);
    }

    @Test
    public void ShouldReturnCorrectArrayWhen_PatternIsAtTheMiddleAnd_AtTheEndOfTheText() {
        //given
        String pattern = "abcd";
        DeterministicFiniteAutomatonTextSearch deterministicFiniteAutomatonTextSearch = new DeterministicFiniteAutomatonTextSearch(pattern);
        int [] expected = {2, 7};

        //when
        String text = "cdabcdeabcd";
        int [] index = deterministicFiniteAutomatonTextSearch.findAll(text);

        //then
        Assert.assertArrayEquals(expected, index);
    }
    
    @Test
    public void ShouldReturnCorrectArrayWhen_AllTextIsMadeOfPatterns() {
        //given
        String pattern = "kot";
        DeterministicFiniteAutomatonTextSearch deterministicFiniteAutomatonTextSearch = new DeterministicFiniteAutomatonTextSearch(pattern);
        int [] expected = {0, 3, 6, 9, 12};

        //when
        String text = "kotkotkotkotkot";
        int [] index = deterministicFiniteAutomatonTextSearch.findAll(text);

        //then
        Assert.assertArrayEquals(expected, index);
    }

    @Test
    public void ShouldReturnCorrectArrayWhen_PatternsAreOverlappingInText() {
        //given
        String pattern = "AAA";
        DeterministicFiniteAutomatonTextSearch deterministicFiniteAutomatonTextSearch = new DeterministicFiniteAutomatonTextSearch(pattern);
        int [] expected = {0, 1, 2, 3};

        //when
        String text = "AAAAAA";
        int [] index = deterministicFiniteAutomatonTextSearch.findAll(text);

        //then
        Assert.assertArrayEquals(expected, index);
    }

    @Test
    public void ShouldReturnEmptyArrayWhen_PatternDoesNotExistInGivenTest() {
        //given
        String pattern = "prawda";
        DeterministicFiniteAutomatonTextSearch deterministicFiniteAutomatonTextSearch = new DeterministicFiniteAutomatonTextSearch(pattern);
        int [] expected = {};

        //when
        String text = "polski_rzad_jest_bardzo_dobry";
        int [] index = deterministicFiniteAutomatonTextSearch.findAll(text);

        //then
        Assert.assertArrayEquals(expected, index);
    }

    @Test
    public void ShouldReturnCorrectArrayWhen_ThereIsNewlineCharacterInPattern() {
        //given
        String pattern = "a\nb";
        DeterministicFiniteAutomatonTextSearch deterministicFiniteAutomatonTextSearch = new DeterministicFiniteAutomatonTextSearch(pattern);
        int [] expected = {1, 5};

        //when
        String text = "aa\nbca\nbd";
        int [] index = deterministicFiniteAutomatonTextSearch.findAll(text);

        //then
        Assert.assertArrayEquals(expected, index);
    }

}
