package pl.edu.pw.ee;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class HuffmanTest {

    private final boolean compress = true;
    private final boolean decompress = false;

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhen_FileHasCharThatDoesNotExistInASCII() {
        // given
        Huffman huffman = new Huffman();

        // when
        int actualBits = huffman.huffman("src/dirfortests/testsForHuffman/testForCharsThatDoesNotExistInASCII/",
                compress);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenCompressing_WhenFileToCodeDoesNotExist() {
        // given
        Huffman huffman = new Huffman();

        // when
        int actualBits = huffman.huffman("src/dirfortests/testsForHuffman/testForCompressingWithoutInput/", compress);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenDecompressing_DictionaryDoesNotExist() {
        // given
        Huffman huffman = new Huffman();

        // when
        int actualBits = huffman.huffman("src/dirfortests/testsForHuffman/testForDecompressingWithoutDictionary/",
                decompress);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenDecompressing_FileToUncodeDoesNotExist() {
        // given
        Huffman huffman = new Huffman();

        // when
        int actualBits = huffman.huffman("src/dirfortests/testsForHuffman/testForDeompressingWithoutInput/",
                decompress);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenCompressing_WhenThereIsOnlyOneTypeOfCharInFile() {
        // given
        Huffman huffman = new Huffman();

        // when
        int actualBits = huffman.huffman("src/dirfortests/testsForCompressing/testForCompressingWithTextWithOneChar/",
                compress);

        // then
        assert false;
    }

    @Test
    public void shouldReturnCorrectNumberOfBitsWhenCompressing_WhenFileExists() {
        // given
        Huffman huffman = new Huffman();
        int expectedBits = 18;

        // when
        int actualBits = huffman.huffman("src/dirfortests/testsForHuffman/testKnownFromAiSDLab/", compress);

        // then
        assertEquals(expectedBits, actualBits);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenDecompressing_WhenDictionaryIsEmpty() {
        //given
        Huffman huffman = new Huffman();

        //when
        int numOfBits = huffman.huffman("src/dirfortests/testsForHuffman/testForDecompressingWithEmptyDictionary/", decompress);

        //then
        assert false;
    }

    @Test
    public void shouldReturnCorrectNumberOfBitsWhenDecompressing_WhenFilesExist() {
        // given
        Huffman huffman = new Huffman();
        int expectedBits = 7;

        // when
        int actualBits = huffman.huffman("src/dirfortests/testsForHuffman/testKnownFromAiSDLabDecompressing/",
                decompress);

        // then
        assertEquals(expectedBits, actualBits);
    }

}
