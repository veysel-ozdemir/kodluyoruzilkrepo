import java.util.HashMap;
import java.util.Scanner;

public class GreengrocerCalculator {
    public static void main(String[] args) {
        HashMap<String, Double> prices = new HashMap<>();
        prices.put("pear", 2.14);
        prices.put("apple", 3.67);
        prices.put("tomato", 1.11);
        prices.put("banana", 0.95);
        prices.put("aubergine", 5.0);
        double pearKG, appleKG, tomatoKG, bananaKG, aubergineKG, sum = 0.0;
        Scanner sc = new Scanner(System.in);

        System.out.print("How many kilograms of pears did you buy? ");
        pearKG = sc.nextDouble();
        System.out.print("How many kilograms of apples did you buy? ");
        appleKG = sc.nextDouble();
        System.out.print("How many kilograms of tomatoes did you buy? ");
        tomatoKG = sc.nextDouble();
        System.out.print("How many kilograms of bananas did you buy? ");
        bananaKG = sc.nextDouble();
        System.out.print("How many kilograms of aubergines did you buy? ");
        aubergineKG = sc.nextDouble();

        if (pearKG >= 0 && appleKG >= 0 && tomatoKG >= 0 && bananaKG >= 0 && aubergineKG >= 0) {
            sum += pearKG * prices.get("pear");
            sum += appleKG * prices.get("apple");
            sum += tomatoKG * prices.get("tomato");
            sum += bananaKG * prices.get("banana");
            sum += aubergineKG * prices.get("aubergine");
            System.out.printf("Total amount: %.2f\n", sum);
        } else {
            System.out.println("Invalid value entered");
        }
    }
}
