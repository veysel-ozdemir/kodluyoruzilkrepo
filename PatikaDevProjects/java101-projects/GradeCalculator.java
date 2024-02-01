import java.util.Scanner;

public class GradeCalculator {
    public static void main(String[] args) {
        int math, chemistry, physics, turkish, history, music;
        double avg;
        String msg;
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter math grade: ");
        math = sc.nextInt();
        System.out.print("Enter chemistry grade: ");
        chemistry = sc.nextInt();
        System.out.print("Enter physics grade: ");
        physics = sc.nextInt();
        System.out.print("Enter turkish grade: ");
        turkish = sc.nextInt();
        System.out.print("Enter history grade: ");
        history = sc.nextInt();
        System.out.print("Enter music grade: ");
        music = sc.nextInt();

        avg = (math + chemistry + physics + turkish + history + music) / 6.0;
        System.out.println("The average is " + avg);

        msg = (avg > 60) ? "Class passed" : "Class failed";
        System.out.println(msg);
    }
}
