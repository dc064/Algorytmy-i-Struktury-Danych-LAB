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

public class SurpriseTest {


    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhen_ScreeningsIsNull() {
        // given
        Surprise f = new Surprise();

        // when
        int g = f.countCombinationsOfScreenings(null);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhen_ArrayLengthIsLowerThanFour() {
        // given
        Surprise f = new Surprise();
        int [] a = {1, 2}

        // when
        int g = f.countCombinationsOfScreenings(a);

        // then
        assert false;
    }

}
