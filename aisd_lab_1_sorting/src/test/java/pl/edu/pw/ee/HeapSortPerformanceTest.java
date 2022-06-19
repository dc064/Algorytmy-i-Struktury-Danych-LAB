package pl.edu.pw.ee;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class HeapSortPerformanceTest {
    HeapSort heapSort;
    Random r;
    File file;
    public static long timeBeforeSorting;
    public static long timeAfterSorting;
    public static long sortingTime;
    BufferedWriter writer;
    double[] nums = new double[5];

    private void doubleSize() {
        nums = new double[2 * nums.length];
    }

    private void fillArrayWithRandomValues() {
        int seed = 10000;
        for (int i = 0; i < nums.length; i++) {
            nums[i] = r.nextDouble() * seed;
        }
    }

    private void fillArrayWithEqualValues() {
        for (int i = 0; i < nums.length; i++) {
            nums[i] = 2;
        }
    }
    

    @Before
    public void init() {
        heapSort = new HeapSort();
        r = new Random();
    }

    @Test
    public void sortingForRandomValues() {

        file = new File("HeapSortForRandomValues.txt");
        try {
            writer = new BufferedWriter(new FileWriter(file));
        } catch (FileNotFoundException a) {} catch (IOException e) {}

        for (int i = 0; i < 16; i++) {
            fillArrayWithRandomValues();
            timeBeforeSorting = System.nanoTime();
            heapSort.sort(nums);
            timeAfterSorting = System.nanoTime();
            sortingTime = timeAfterSorting - timeBeforeSorting;
            try {
                writer.write(nums.length + " " + sortingTime + "\n");
            } catch (FileNotFoundException a) {} catch (IOException a) {};
            doubleSize();
        }
        try {
            writer.close();
        } catch (IOException e) {
        }
    }

    @Test
    public void sortingForEqualValues() {

        file = new File("HeapSortForEqualValues.txt");
        try {
            writer = new BufferedWriter(new FileWriter(file));
        } catch (FileNotFoundException a) {
            System.out.println("File doesnt exist");
        } catch (IOException e) {
        }

        for (int i = 0; i < 16; i++) {
            fillArrayWithEqualValues();
            timeBeforeSorting = System.nanoTime();
            heapSort.sort(nums);
            timeAfterSorting = System.nanoTime();
            sortingTime = timeAfterSorting - timeBeforeSorting;
            try {
                writer.write(nums.length + " " + sortingTime + "\n");
            } catch (FileNotFoundException a) {
                System.out.println("File doesnt exist");
            } catch (IOException a) {
            }
            ;
            doubleSize();
        }
        try {
            writer.close();
        } catch (IOException e) {
        }
    }
}
