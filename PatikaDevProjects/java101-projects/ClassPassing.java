import java.util.ArrayList;
import java.util.Scanner;

public class ClassPassing {
    public static void main(String[] args) {
        ArrayList<Double> grades = new ArrayList<>();
        double avg, sum = 0.0;
        byte count = 0, passingNote = 55;
        String msg;
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter math grade: ");
        grades.add(sc.nextDouble());
        System.out.print("Enter chemistry grade: ");
        grades.add(sc.nextDouble());
        System.out.print("Enter physics grade: ");
        grades.add(sc.nextDouble());
        System.out.print("Enter turkish grade: ");
        grades.add(sc.nextDouble());
        System.out.print("Enter history grade: ");
        grades.add(sc.nextDouble());
        System.out.print("Enter music grade: ");
        grades.add(sc.nextDouble());

        for (double grade : grades) {
            if (grade >= 0 && grade <= 100) {
                count++;
                sum += grade;
            }
        }
        avg = sum / count;
        System.out.println("\nAverage: " + avg);
        msg = (avg >= passingNote) ? "Class passed!" : "Class couldn't pass!";
        System.out.println(msg);
    }
}
