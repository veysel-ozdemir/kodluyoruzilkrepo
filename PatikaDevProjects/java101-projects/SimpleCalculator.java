import java.util.Scanner;

public class SimpleCalculator {
    public static void main(String[] args) {
        double num1, num2;
        byte op;
        String div;
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter first number: ");
        num1 = sc.nextDouble();
        System.out.print("Enter second number: ");
        num2 = sc.nextDouble();
        System.out.print("""
                \n1-Addition
                2-Subtraction
                3-Multiplication
                4-Division
                Select operation:\s""");
        op = sc.nextByte();

        switch (op) {
            case 1:
                System.out.println(num1 + " + " + num2 + " = " + (num1 + num2));
                break;
            case 2:
                System.out.println(num1 + " - " + num2 + " = " + (num1 - num2));
                break;
            case 3:
                System.out.println(num1 + " x " + num2 + " = " + (num1 * num2));
                break;
            case 4:
                System.out.print("\n" +
                        "A-" + num1 + "\n" +
                        "B-" + num2 + "\nSelect the divider: ");
                div = sc.next();

                if (div.equalsIgnoreCase("A") && num1 == 0) {
                    System.out.println("Divider cannot be zero.");
                } else if (div.equalsIgnoreCase("A")) {
                    System.out.println(num2 + " / " + num1 + " = " + (num2 / num1));
                } else if (div.equalsIgnoreCase("B") && num2 == 0) {
                    System.out.println("Divider cannot be zero.");
                } else if (div.equalsIgnoreCase("B")) {
                    System.out.println(num1 + " / " + num2 + " = " + (num1 / num2));
                } else {
                    System.out.println("Invalid selection");
                }
                break;
            default:
                System.out.println("Invalid operation selected");
        }
    }
}
