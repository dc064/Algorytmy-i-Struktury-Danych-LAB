package pl.edu.pw.ee;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class PrimAlgorithmTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhen_FileWithDataDoesNotExist() {
        // given
        PrimAlgorithm primAlgorithm = new PrimAlgorithm();

        // when
        String actual = primAlgorithm.findMST("src/data/does_not_exist.txt");

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhen_FileHasLineWithFourValues() {
        // given
        PrimAlgorithm primAlgorithm = new PrimAlgorithm();

        // when
        String actual = primAlgorithm.findMST("src/data/file_with_incorrect_line.txt");

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhen_FileHasLineWithTwoValues() {
        // given
        PrimAlgorithm primAlgorithm = new PrimAlgorithm();

        // when
        String actual = primAlgorithm.findMST("src/data/file_with_incorrect_line_two.txt");

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhen_FileHasEdgeBetweenTheSameNode() {
        // given
        PrimAlgorithm primAlgorithm = new PrimAlgorithm();

        // when
        String actual = primAlgorithm.findMST("src/data/file_with_loop.txt");

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhen_FileHasEdgeWithZeroValue() {
        // given
        PrimAlgorithm primAlgorithm = new PrimAlgorithm();

        // when
        String actual = primAlgorithm.findMST("src/data/file_with_egde_with_value_zero.txt");

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhen_FileIsEmpty() {
        // given
        PrimAlgorithm primAlgorithm = new PrimAlgorithm();

        // when
        String actual = primAlgorithm.findMST("src/data/empty_data.txt");

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhen_FileHasAnEdgeWithNegativeValue() {
        // given
        PrimAlgorithm primAlgorithm = new PrimAlgorithm();

        // when
        String actual = primAlgorithm.findMST("src/data/data_with_negative_edge_value.txt");

        // then
        assert false;
    }


    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhen_PathToFileIsNull() {
        // given
        PrimAlgorithm primAlgorithm = new PrimAlgorithm();

        // when
        String actual = primAlgorithm.findMST(null);

        // then
        assert false;
    }


    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhen_CanootFindMSTBecause_NotAllNodesAreConnectedAtTheBeggining() {
        //given
        PrimAlgorithm primAlgorithm = new PrimAlgorithm();

        //when
        String actual = primAlgorithm.findMST("src/data/data_without_connected_all_nodes.txt");

        //then
        assert false;
    }

    @Test
    public void shouldReturnCorrectString_ExampleFromAiSDLab() {
        //given
        PrimAlgorithm primAlgorithm = new PrimAlgorithm();
        String expected = "G_9_E|E_7_A|E_7_B|B_7_D|D_4_H|H_6_C|A_8_F";

        //when
        String actual = primAlgorithm.findMST("src/data/data_file.txt");

        //then
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnCorrectStringWhen_DataFileHasFiveNodes() {
        //given
        PrimAlgorithm primAlgorithm = new PrimAlgorithm();
        String expected = "A_3_B|B_1_C|C_1_D|D_7_E";

        //when
        String actual = primAlgorithm.findMST("src/data/correct_small_data.txt");

        //then
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnCorrectStringWhen_DataFileHasOneEdge() {
        //given
        PrimAlgorithm primAlgorithm = new PrimAlgorithm();
        String expected = "A_3_B";

        //when
        String actual = primAlgorithm.findMST("src/data/one_edge_data.txt");

        //then
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnCorrectStringWhen_DataFileHasTwoEdgesConnectingTwoEdges() {
        //given
        PrimAlgorithm primAlgorithm = new PrimAlgorithm();
        String expected = "A_9_B";

        //when
        String actual = primAlgorithm.findMST("src/data/two_nodes_and_two_edges_data.txt");

        //then
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnCorrectStringWhen_DataFileHasTwoEdges() {
        //given
        PrimAlgorithm primAlgorithm = new PrimAlgorithm();
        String expected = "A_3_B|B_4_C";

        //when
        String actual = primAlgorithm.findMST("src/data/two_edges_data.txt");

        //then
        assertEquals(expected, actual);
    }

}
