// Body-Mass Index: Mass (kg) / (Height (m) * Height (m))

import java.util.Scanner;

public class BMICalculator {
    public static void main(String[] args) {
        double mass, height, bmi;
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the mass in kg: ");
        mass = sc.nextDouble();
        System.out.print("Enter the height in m: ");
        height = sc.nextDouble();

        if (mass > 0 && height > 0) {
            bmi = mass / (height * height);
            System.out.printf("BMI: %.2f", bmi);
        } else {
            System.out.println("Invalid value entered");
        }
    }
}
