package HackerRankChallenges;

import java.util.Scanner;

public class HackerRankExceptionHandling {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MyCalculator myCalculator = new MyCalculator();
        int n = sc.nextInt();
        int p = sc.nextInt();
        try {
            myCalculator.power(n, p);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

}

class MyCalculator {
    long power(int n, int p) throws Exception {
        long result = 1;
        if (n < 0 || p < 0) {
            throw new Exception("n or p should not be negative.");
        } else if (n == 0 && p == 0) {
            throw new Exception("n and p should not be zero.");
        }
        for (int i = 0; i < p; i++) {
            result *= n;
        }
        return result;
    }
}