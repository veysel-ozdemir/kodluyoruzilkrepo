import java.util.Scanner;

public class VATCalculator {
    public static void main(String[] args) {
        int amount;
        double vat, taxedAmount;
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter amount: ");
        amount = sc.nextInt();

        if (amount > 0 && amount < 1000) {
            vat = 0.18; // %18
            System.out.println("Price without VAT: " + amount);
            taxedAmount = amount + (amount * vat);
            System.out.println("Price with VAT: " + taxedAmount);
            System.out.printf("VAT amount: %.2f", (taxedAmount - amount));
        } else if (amount >= 1000) {
            vat = 0.08; // %8
            System.out.println("Price without VAT: " + amount);
            taxedAmount = amount + (amount * vat);
            System.out.println("Price with VAT: " + taxedAmount);
            System.out.printf("VAT amount: %.2f", (taxedAmount - amount));
        } else {
            System.out.println("Invalid value entered");
        }
    }
}