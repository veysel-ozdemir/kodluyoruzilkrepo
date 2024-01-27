/*
Make a program in Java that calculates airfare price based on distance and conditions.
Get Distance (KM), age and trip type (One Way, Round Trip) information from the user.
Take the fare per distance as 0.10 TL / km.
First calculate the total price of the flight and then apply
the following discounts to the customer according to the conditions;

   -The values received from the user must be valid (distance and age must be
    positive numbers and trip type must be 1 or 2). Otherwise, the user should
    receive a warning saying "You entered incorrect data !".
   -If the person is under 12 years old, 50% discount is applied on the ticket price.
   -If the person is between the ages of 12-24, a 10% discount is applied on the ticket price.
   -If the person is over 65 years old, a 30% discount is applied on the ticket price.
   -If the person has selected "Trip Type" as round trip, a 20% discount is applied on the ticket price.
*/

import java.util.InputMismatchException;
import java.util.Scanner;

public class AirTicketPriceCalculator {
    public static void main(String[] args) {
        double distance;
        byte age, tripType;
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Enter your distance in km: ");
            distance = sc.nextDouble();
            System.out.print("\nEnter your age: ");
            age = sc.nextByte();
            System.out.print("\n1- One-way\n2- Round-trip\nEnter your trip type: ");
            tripType = sc.nextByte();

            if (distance <= 0) {
                throw new InputMismatchException();
            } else if (age <= 0) {
                throw new InputMismatchException();
            } else if (tripType != 1 && tripType != 2) { // !(tripType == 1 || tripType == 2)
                throw new InputMismatchException();
            } else {
                getPrice(distance, age, tripType);
            }
        } catch (InputMismatchException e) {
            System.out.println("\nInvalid value entered");
        }
    }

    private static void getPrice(double distance, byte age, byte tripType) {
        double totalAmount;
        float discount, feePerKM = 0.1f;

        // initialize total amount
        totalAmount = distance * feePerKM;

        // calculate age discount
        if (age < 12) {
            discount = 0.5f;
            totalAmount -= (totalAmount * discount);
        } else if (age <= 24) {
            discount = 0.1f;
            totalAmount -= (totalAmount * discount);
        } else if (age > 65) {
            discount = 0.3f;
            totalAmount -= (totalAmount * discount);
        }

        // calculate trip-type discount
        if (tripType == 2) {
            discount = 0.2f;
            totalAmount -= (totalAmount * discount);
            totalAmount *= 2;
        }

        System.out.printf("\nTotal amount: %.2f\n", totalAmount);
    }
}
