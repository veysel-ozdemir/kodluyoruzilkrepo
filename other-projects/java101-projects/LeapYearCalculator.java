import java.util.InputMismatchException;
import java.util.Scanner;

public class LeapYearCalculator {
    public static void main(String[] args) {
        int year;
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Enter year: ");
            year = sc.nextInt();

            calculateLeapYear(year);
        } catch (InputMismatchException e) {
            System.out.println("Invalid value entered");
        }
    }

    private static void calculateLeapYear(int year) {
        boolean isLeap = false;

        if (year % 100 == 0) {
            if (year % 400 == 0) {
                isLeap = true;
            }
        } else if (year % 4 == 0) {
            isLeap = true;
        }

        System.out.println(isLeap ? year + " is leap year." : year + " isn't leap year.");
    }
}
