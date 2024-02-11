package RaceOfThreads;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> odds = new ArrayList<>();
        List<Integer> evens = new ArrayList<>();
        List<Integer> nums = new ArrayList<>();

        // store the numbers 1-10000
        for (int i = 1; i <= 10000; i++) {
            nums.add(i);
        }

        // divide to 4 parts
        List<List<Integer>> partitions = new ArrayList<>();
        int partitionSize = nums.size() / 4;
        for (int i = 0; i < nums.size(); i += partitionSize) {
            partitions.add(nums.subList(i, Math.min(i + partitionSize, nums.size())));
        }

        // create 4 threads to be run
        List<NumberProcessor> processors = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            processors.add(new NumberProcessor(partitions.get(i), odds, evens));
            processors.get(i).start();
        }

        // wait the threads to be finished
        try {
            for (NumberProcessor processor : processors) {
                processor.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Odds: " + odds);
        System.out.println("Evens: " + evens);
    }
}
