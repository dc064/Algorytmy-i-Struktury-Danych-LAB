package pl.edu.pw.ee;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CompresserTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhen_GivenDirIsNull() {
        //given
        String path = null;

        //when
        Compresser compresser = new Compresser(path);

        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhen_FileHasCharThatDoesNotExistInASCII() {
        //given
        Compresser compresser = new Compresser("src/dirfortests/testsForCompressing/testForCharsThatDoesNotExistInASCII/");

        //when
        int actualBits = compresser.compressing();

        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenCompressing_WhenFileToCodeDoesNotExist() {
        //given
        Compresser compresser = new Compresser("src/dirfortests/testsForCompressing/testForCompressingWithoutInput/");

        //when
        int actualBits = compresser.compressing();

        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenCompressing_WhenThereIsOnlyOneTypeOfCharInFile() {
        //given
        Compresser compresser = new Compresser("src/dirfortests/testsForCompressing/testForCompressingWithTextWithOneChar/");

        //when
        int actualBits = compresser.compressing();

        //then
        assert false;
    }

    @Test
    public void shouldReturnCorrectNumberOfBitsWhenCompressing_WhenFileExists() {
        //given
        Compresser compresser = new Compresser("src/dirfortests/testsForCompressing/testKnownFromAiSDLab/");
        int expectedBits = 18;

        //when
        int actualBits = compresser.compressing();

        //then
        assertEquals(expectedBits, actualBits);
    }
    
}
