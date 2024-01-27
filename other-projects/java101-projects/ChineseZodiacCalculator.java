/*
    Used for 4000 thousand years, Chinese astrology is a type of astrology that
    describes people with 12 different signs and symbols. The Chinese Zodiac is
    an animal ring in which these 12 signs are equally spaced (10 degrees wide)
    and has nothing to do with the stars.

    The Chinese zodiac is calculated according to the remainder of
    the person's birth year divided by 12.
*/

import java.util.InputMismatchException;
import java.util.Scanner;

public class ChineseZodiacCalculator {
    public static void main(String[] args) {
        int year;
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Enter birth-year: ");
            year = sc.nextInt();

            getChineseZodiac(year);
        } catch (InputMismatchException e) {
            System.out.println("Invalid value entered");
        }
    }

    private static void getChineseZodiac(int year) {
        int remainder = year % 12;
        String zodiac = switch (remainder) {
            case 0 -> "Monkey";
            case 1 -> "Rooster";
            case 2 -> "Dog";
            case 3 -> "Pork";
            case 4 -> "Mouse";
            case 5 -> "Ox";
            case 6 -> "Tiger";
            case 7 -> "Rabbit";
            case 8 -> "Dragon";
            case 9 -> "Snake";
            case 10 -> "Horse";
            case 11 -> "Sheep";
            default -> "";
        };

        System.out.println("The Chinese Zodiac: " + zodiac);
    }
}
