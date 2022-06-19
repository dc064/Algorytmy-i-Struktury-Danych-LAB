package pl.edu.pw.ee;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class KluskalAlgorithmTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhen_FileWithDataDoesNotExist() {
        // given
        KluskalAlgorithm kluskalAlgorithm = new KluskalAlgorithm();

        // when
        String actual = kluskalAlgorithm.findMST("src/data/does_not_exist.txt");

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhen_FileHasLineWithFourValues() {
        // given
        KluskalAlgorithm kluskalAlgorithm = new KluskalAlgorithm();

        // when
        String actual = kluskalAlgorithm.findMST("src/data/file_with_incorrect_line.txt");

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhen_FileHasLineWithTwoValues() {
        // given
        KluskalAlgorithm kluskalAlgorithm = new KluskalAlgorithm();

        // when
        String actual = kluskalAlgorithm.findMST("src/data/file_with_incorrect_line_two.txt");

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhen_FileHasEdgeBetweenTheSameNode() {
        // given
        KluskalAlgorithm kluskalAlgorithm = new KluskalAlgorithm();

        // when
        String actual = kluskalAlgorithm.findMST("src/data/file_with_loop.txt");

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhen_FileHasEdgeWithZeroValue() {
        // given
        KluskalAlgorithm kluskalAlgorithm = new KluskalAlgorithm();

        // when
        String actual = kluskalAlgorithm.findMST("src/data/file_with_egde_with_value_zero.txt");

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhen_FileIsEmpty() {
        // given
        KluskalAlgorithm kluskalAlgorithm = new KluskalAlgorithm();

        // when
        String actual = kluskalAlgorithm.findMST("src/data/empty_data.txt");

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhen_FileHasAnEdgeWithNegativeValue() {
        // given
        KluskalAlgorithm kluskalAlgorithm = new KluskalAlgorithm();

        // when
        String actual = kluskalAlgorithm.findMST("src/data/data_with_negative_edge_value.txt");

        // then
        assert false;
    }


    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhen_PathToFileIsNull() {
        // given
        KluskalAlgorithm kluskalAlgorithm = new KluskalAlgorithm();

        // when
        String actual = kluskalAlgorithm.findMST(null);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhen_CanootFindMSTBecause_NotAllNodesAreConnectedAtTheBeggining() {
        //given
        KluskalAlgorithm kluskalAlgorithm = new KluskalAlgorithm();

        //when
        String actual = kluskalAlgorithm.findMST("src/data/data_without_connected_all_nodes.txt");

        //then
        assert false;
    }

    @Test
    public void shouldReturnCorrectString_ExampleFromAiSDLab() {
        //given
        KluskalAlgorithm kluskalAlgorithm = new KluskalAlgorithm();
        String expected = "D_4_H|C_6_H|E_7_A|E_7_B|B_7_D|F_8_A|G_9_E";

        //when
        String actual = kluskalAlgorithm.findMST("src/data/data_file.txt");

        //then
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnCorrectStringWhen_DataFileHasFiveNodes() {
        //given
        KluskalAlgorithm kluskalAlgorithm = new KluskalAlgorithm();
        String expected = "B_1_C|C_1_D|A_3_B|D_7_E";

        //when
        String actual = kluskalAlgorithm.findMST("src/data/correct_small_data.txt");

        //then
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnCorrectStringWhen_DataFileHasOneEdge() {
        //given
        KluskalAlgorithm kluskalAlgorithm = new KluskalAlgorithm();
        String expected = "A_3_B";

        //when
        String actual = kluskalAlgorithm.findMST("src/data/one_edge_data.txt");

        //then
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnCorrectStringWhen_DataFileHasTwoEdgesConnectingTwoEdges() {
        //given
        KluskalAlgorithm kluskalAlgorithm = new KluskalAlgorithm();
        String expected = "A_9_B";

        //when
        String actual = kluskalAlgorithm.findMST("src/data/two_nodes_and_two_edges_data.txt");

        //then
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnCorrectStringWhen_DataFileHasTwoEdges() {
        //given
        KluskalAlgorithm kluskalAlgorithm = new KluskalAlgorithm();
        String expected = "A_3_B|B_4_C";

        //when
        String actual = kluskalAlgorithm.findMST("src/data/two_edges_data.txt");

        //then
        assertEquals(expected, actual);
    }

}
