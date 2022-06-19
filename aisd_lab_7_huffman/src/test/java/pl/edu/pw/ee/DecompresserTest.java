package pl.edu.pw.ee;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DecompresserTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhen_GivenPathIsNull() {
        //given
        String path = null;

        //when
        Decompresser decompresser = new Decompresser(path);

        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenDecompressing_DictionaryDoesNotExist() {
        //given
        Decompresser decompresser = new Decompresser("src/dirfortests/testsForDecompressing/testForDecompressingWithoutDictionary/");

        //when
        int actualBits = decompresser.decompressing();

        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenDeompressing_FileToUncodeDoesNotExist() {
        //given
        Decompresser decompresser = new Decompresser("src/dirfortests/testsForDecompressing/testForDecompressingWithoutInput/");

        //when
        int actualBits = decompresser.decompressing();

        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenDeompressing_WhenNumberOfDiffrentCharsInDictionaryIsTooBig() {
        //given
        Decompresser decompresser = new Decompresser("src/dirfortests/testsForDecompressing/testForDecompressingWithBadDictionary/");


        //when
        int numOfBits = decompresser.decompressing();

        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenDecompressing_WhenDictionaryIsEmpty() {
        //given
        Decompresser decompresser;

        //when
        decompresser = new Decompresser("src/dirfortests/testsForDecompressing/testForDecompressingWithEmptyDictionary/");

        //then
        assert false;
    }

    @Test
    public void shouldReturnCorrectNumberOfBitsWhenDecompressing_WhenFilesExist() {
        //given
        Decompresser decompresser = new Decompresser("src/dirfortests/testsForDecompressing/testKnownFromAiSDLabDecompressing/");
        int expectedBits = 7;

        //when
        int actualBits = decompresser.decompressing();

        //then
        assertEquals(expectedBits, actualBits);
    }

}
