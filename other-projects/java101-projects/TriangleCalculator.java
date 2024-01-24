/*
    𝑢 = (a+b+c) / 2
    Perimeter = 2𝑢
    Area * Area = 𝑢 * (𝑢 − 𝑎)* (𝑢 − 𝑏) * (𝑢 − 𝑐)
*/

import java.util.Scanner;

public class TriangleCalculator {
    public static void main(String[] args) {
        double a, b, c, u, h, area;
        byte resp;
        Scanner sc = new Scanner(System.in);

        System.out.println("Press 1 for calculating the hypothesis of the right-angled triangle, or\n" +
                "Press 2 for calculating the perimeter and area of the triangle:");
        resp = sc.nextByte();

        if (resp == 1) {
            System.out.println("Enter the edges of the right-angled triangle:");
            a = sc.nextDouble();
            b = sc.nextDouble();
            h = Math.hypot(a, b);
            System.out.printf("The hypothesis: %.2f", h);
        } else if (resp == 2) {
            System.out.println("Enter the edges of the triangle:");
            a = sc.nextDouble();
            b = sc.nextDouble();
            c = sc.nextDouble();
            u = (a + b + c) / 2;
            area = Math.sqrt(u * (u - a) * (u - b) * (u - c));
            System.out.printf("The Perimeter is %.2f\n", 2 * u);
            System.out.printf("The Area is %.2f\n", area);
        } else {
            System.out.println("Invalid value pressed");
        }
    }
}
