package pl.edu.pw.ee;

import pl.edu.pw.ee.services.Sorting;

public class InsertionSort implements Sorting {

    @Override
    public void sort(double[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Nums array cannot be null");
        }
        int j;
        for (int i = 1; i < nums.length; i++) {
            j = i - 1;
            if (nums[j] > nums[i]) {
                while (j >= 0 && nums[j] > nums[j + 1]) {
                    swap(nums, j, j + 1);
                    j--;
                }

            }
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
