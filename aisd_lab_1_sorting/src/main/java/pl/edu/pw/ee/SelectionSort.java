package pl.edu.pw.ee;

import pl.edu.pw.ee.services.Sorting;

public class SelectionSort implements Sorting {

    @Override
    public void sort(double[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Nums array cannot be null");
        }
        int i = 0;
        int j;
        double min_Value;
        int min_ID;
        for (i = 0; i < nums.length - 1; i++) {
            min_ID = i;
            min_Value = nums[min_ID];
            for (j = i + 1; j < nums.length; j++) {
                if (nums[j] < min_Value) {
                    min_Value = nums[j];
                    min_ID = j;
                }
            }
            swap(nums, min_ID, i);
        }
    }

    private void swap(double[] data, int firstId, int secondId) {
        if (firstId != secondId) {
            double firstValue = data[firstId];
            data[firstId] = data[secondId];
            data[secondId] = firstValue;
        }
    }

}
