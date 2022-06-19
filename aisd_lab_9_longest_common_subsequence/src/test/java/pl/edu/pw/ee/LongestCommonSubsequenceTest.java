package pl.edu.pw.ee;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class LongestCommonSubsequenceTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWnen_TopStringIsNull() {
        // given
        String topStr = null;
        String leftStr = "LOKOMOTYWA";

        // when
        LongestCommonSubsequence longestCommonSubsequence = new LongestCommonSubsequence(topStr, leftStr);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWnen_LeftStringIsNull() {
        // given
        String topStr = "KOMPOTY";
        String leftStr = null;

        // when
        LongestCommonSubsequence longestCommonSubsequence = new LongestCommonSubsequence(topStr, leftStr);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWnen_TopAndLeftStringsAreNull() {
        // given
        String topStr = null;
        String leftStr = null;

        // when
        LongestCommonSubsequence longestCommonSubsequence = new LongestCommonSubsequence(topStr, leftStr);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWnen_TopStringIsEmpty() {
        // given
        String topStr = "";
        String leftStr = "aisd lab";

        // when
        LongestCommonSubsequence longestCommonSubsequence = new LongestCommonSubsequence(topStr, leftStr);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWnen_LeftStringIsEmpty() {
        // given
        String topStr = "one two three";
        String leftStr = "";

        // when
        LongestCommonSubsequence longestCommonSubsequence = new LongestCommonSubsequence(topStr, leftStr);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWnen_TopAndLeftStringsAreEmpty() {
        // given
        String topStr = "";
        String leftStr = "";

        // when
        LongestCommonSubsequence longestCommonSubsequence = new LongestCommonSubsequence(topStr, leftStr);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWnen_TryToDisplayBeforeLCSWasFounded() {
        // given
        String topStr = "POLITECHNIKA";
        String leftStr = "TOALETA";
        LongestCommonSubsequence longestCommonSubsequence = new LongestCommonSubsequence(topStr, leftStr);

        // when
        longestCommonSubsequence.display();

        // then
        assert false;
    }

    @Test
    public void shouldReturnCorrectSubsequenceWhen_SubsequenceIsDividedInGivenStrings() {
        // given
        String topStr = "KOMPOTY";
        String leftStr = "LOKOMOTYWA";
        String expected = "KOMOTY";
        LongestCommonSubsequence longestCommonSubsequence = new LongestCommonSubsequence(topStr, leftStr);

        // when
        String actual = longestCommonSubsequence.findLCS();

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnCorrectSubsequenceWhen_SubsequenceIsNotDividedInGivenStrings() {
        // given
        String topStr = "koala";
        String leftStr = "fala";
        String expected = "ala";
        LongestCommonSubsequence longestCommonSubsequence = new LongestCommonSubsequence(topStr, leftStr);

        // when
        String actual = longestCommonSubsequence.findLCS();

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnCorrectSubsequenceWhen_SubsequenceLengthEqualsOne() {
        // given
        String topStr = "rycerz";
        String leftStr = "cis";
        String expected = "c";
        LongestCommonSubsequence longestCommonSubsequence = new LongestCommonSubsequence(topStr, leftStr);

        // when
        String actual = longestCommonSubsequence.findLCS();

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnEmptySubsequenceWhen_GivenStringsDoesNotHaveCommonSubsequence() {
        //given
        String topStr = "abcdefgh";
        String leftStr = "ijklmno";
        String expected = "";
        LongestCommonSubsequence longestCommonSubsequence = new LongestCommonSubsequence(topStr, leftStr);

        // when
        String actual = longestCommonSubsequence.findLCS();

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnCorrectSubsequenceWhen_BothArgumentsAreOneCharString() {
        //given
        String topStr = "a";
        String leftStr = "a";
        String expected = "a";
        LongestCommonSubsequence longestCommonSubsequence = new LongestCommonSubsequence(topStr, leftStr);

        // when
        String actual = longestCommonSubsequence.findLCS();

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnCorrectSubsequenceWhen_BothStringsAreTheSame() {
        //given
        String topStr = "doniczka";
        String leftStr = "doniczka";
        String expected = "doniczka";
        LongestCommonSubsequence longestCommonSubsequence = new LongestCommonSubsequence(topStr, leftStr);

        // when
        String actual = longestCommonSubsequence.findLCS();

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnCorrectSubsequenceWhen_ThereIsNewlineCharacterInStrings() {
        //given
        String topStr = "string\nnumber_one";
        String leftStr = "making\nnums";
        String expected = "ing\nnum";

        LongestCommonSubsequence longestCommonSubsequence = new LongestCommonSubsequence(topStr, leftStr);
        // when
        String actual = longestCommonSubsequence.findLCS();

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnLongerSubsequenceWhen_ThereAreTwoDifferentSubsequences() {
        //given
        String topStr = "mam auto";
        String leftStr = "automat mam";
        String expected = "auto";

        LongestCommonSubsequence longestCommonSubsequence = new LongestCommonSubsequence(topStr, leftStr);
        // when
        String actual = longestCommonSubsequence.findLCS();

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnCorrectSubsequenceWhen_ThereIsSpaceCharacterInStrings() {
        //given
        String topStr = "she believed";
        String leftStr = "but he lied";
        String expected = "he lied";

        LongestCommonSubsequence longestCommonSubsequence = new LongestCommonSubsequence(topStr, leftStr);
        // when
        String actual = longestCommonSubsequence.findLCS();

        // then
        assertEquals(expected, actual);
    }

}
