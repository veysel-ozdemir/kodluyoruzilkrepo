/*
Radius: r
π: 3.14
Perimeter: 2 * π * r
Area: π * r * r
Center Angle of a Circle Slice: 𝛼
Area of a Circle Slice: (𝜋 * (r*r) * 𝛼) / 360
*/

import java.util.Scanner;

public class CircleCalculator {
    public static void main(String[] args) {
        final double PI = 3.14;
        double radius, area, perimeter, sliceArea, centerAngle;
        byte resp;
        Scanner sc = new Scanner(System.in);

        System.out.print("""
                Press 1 for calculating the area and perimeter of the circle, or
                Press 2 for calculating the area of the circle slice:
                """);
        resp = sc.nextByte();

        if (resp == 1) {
            System.out.print("Enter the radius: ");
            radius = sc.nextDouble();
            area = PI * radius * radius;
            perimeter = 2 * PI * radius;
            System.out.printf("Area: %.2f\nPerimeter: %.2f\n", area, perimeter);
        } else if (resp == 2) {
            System.out.print("Enter the radius: ");
            radius = sc.nextDouble();
            System.out.print("Enter the center-angle: ");
            centerAngle = sc.nextDouble();
            sliceArea = (PI * radius * radius * centerAngle) / 360;
            System.out.printf("Slice Area: %.2f\n", sliceArea);
        } else {
            System.out.println("Invalid value pressed");
        }
    }
}
