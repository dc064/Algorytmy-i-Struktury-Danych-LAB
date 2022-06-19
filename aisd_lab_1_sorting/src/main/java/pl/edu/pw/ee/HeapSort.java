package pl.edu.pw.ee;

import pl.edu.pw.ee.services.Sorting;

public class HeapSort implements Sorting {

    @Override
    public void sort(double[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Nums array cannot be null");
        }
        Heap<Double> c = new Heap<>();
        int i;
        for(i = 0; i < nums.length; i++) {
            c.put(nums[i]);
        }
        i--;
        for(; i >= 0; i--) {
            nums[i] = c.pop();
        }
    }

}
