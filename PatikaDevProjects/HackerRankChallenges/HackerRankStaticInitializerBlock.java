package HackerRankChallenges;

import java.util.Scanner;

public class HackerRankStaticInitializerBlock {
    public static int breadth, height;
    public static Scanner sc;

    static {
        sc = new Scanner(System.in);
        breadth = sc.nextInt();
        height = sc.nextInt();
    }

    public static void main(String[] args) {
        try {
            if (breadth <= 0 || height <= 0) {
                throw new Exception();
            }
            System.out.println(breadth * height);
        } catch (Exception e) {
            System.out.println("java.lang.Exception: Breadth and height must be positive");
        }
    }
}