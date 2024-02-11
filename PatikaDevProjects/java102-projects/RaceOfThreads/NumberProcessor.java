package RaceOfThreads;

import java.util.ArrayList;
import java.util.List;

public class NumberProcessor extends Thread {
    private final List<Integer> nums, odds, evens;

    public NumberProcessor(List<Integer> nums, List<Integer> odds, List<Integer> evens) {
        this.nums = nums;
        this.odds = odds;
        this.evens = evens;
    }

    @Override
    public void run() {
        for (int num : nums) {
            if (num % 2 == 0) {
                synchronized (evens) {
                    evens.add(num);
                }
            } else {
                synchronized (odds) {
                    odds.add(num);
                }
            }
        }
    }

    public List<Integer> getNums() {
        return nums;
    }
}
