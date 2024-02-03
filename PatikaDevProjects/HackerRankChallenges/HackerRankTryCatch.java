package HackerRankChallenges;

import java.util.InputMismatchException;
import java.util.Scanner;

public class HackerRankTryCatch {
    public static void main(String[] args) {
        int x, y;
        Scanner sc = new Scanner(System.in);
        try {
            x = sc.nextInt();
            y = sc.nextInt();
            System.out.println(x / y);
        } catch (InputMismatchException e) {
            System.out.println(e.getClass().getName());
        } catch (ArithmeticException e) {
            System.out.println(e.toString());
        }
    }
}
