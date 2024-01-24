import java.util.Scanner;

public class TaximeterCalculator {
    public static void main(String[] args) {
        double distance, openingFee = 10.0, feePerKM = 2.2, minPrice = 20.0, totalAmount;
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the distance in km: ");
        distance = sc.nextDouble();
        totalAmount = distance * feePerKM;

        if (totalAmount > 0 && totalAmount <= minPrice) {
            System.out.printf("The total amount is %.2f\n", minPrice);
        } else if (totalAmount > minPrice) {
            System.out.printf("The total amount is %.2f\n", (openingFee + totalAmount));
        } else {
            System.out.println("Invalid value entered");
        }
    }
}