import java.util.Scanner;

public class ActivitySuggester {
    public static void main(String[] args) {
        double temp;
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter temperature: ");
        temp = sc.nextDouble();

        if (temp < 5) {
            System.out.println("* skiing");
        } else if (temp >= 5 && temp < 15) {
            System.out.println("* cinema");
            if (temp >= 10)
                System.out.println("* picnic");
        } else if (temp >= 10 && temp <= 25) {
            System.out.println("* picnic");
        } else {
            System.out.println("* swimming");
        }
    }
}
