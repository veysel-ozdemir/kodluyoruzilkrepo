package HackerRankChallenges;

import java.util.ArrayList;
import java.util.Scanner;

public class HackerRankArrayList {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<ArrayList<Integer>> lines = new ArrayList<>();
        int n = sc.nextInt();
        int d;
        for (int i = 0; i < n; i++) {
            d = sc.nextInt();
            ArrayList<Integer> locs = new ArrayList<>();
            for (int j = 0; j < d; j++) {
                locs.add(sc.nextInt());
            }
            lines.add(locs);
        }
        int q = sc.nextInt();
        int x, y;
        for (int i = 0; i < q; i++) {
            x = sc.nextInt();
            y = sc.nextInt();
            try {
                System.out.println(lines.get(x - 1).get(y - 1));
            } catch (IndexOutOfBoundsException e) {
                System.out.println("ERROR!");
            }
        }
    }
}
