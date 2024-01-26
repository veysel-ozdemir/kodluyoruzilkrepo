import java.util.Scanner;

public class ZodiacSignFinder {
    public static void main(String[] args) {
        byte month, day;
        Scanner sc = new Scanner(System.in);

        try {
            System.out.println("Enter in numeric format.");
            System.out.print("Enter day of your birthday: ");
            day = Byte.parseByte(sc.nextLine());
            System.out.print("Enter month of your birthday: ");
            month = Byte.parseByte(sc.nextLine());

            if (month < 1 || month > 12)
                throw new NumberFormatException();
            else if (day < 1 || day > 31) {
                throw new NumberFormatException();
            } else if (month == 2 && day > 29) {
                throw new NumberFormatException();
            } else if ((month == 4 || month == 6 || month == 9 || month == 11) && day > 30) {
                throw new NumberFormatException();
            }

            System.out.println(getZodiac(month, day));
        } catch (NumberFormatException e) {
            System.out.println("Invalid value entered");
        }
    }

    private static String getZodiac(byte month, byte day) {
        String zodiac;

        if (month == 1) {
            if (day > 0 && day < 22) {
                zodiac = "Capricorn";
            } else {
                zodiac = "Aquarius";
            }
        } else if (month == 2) {
            if (day > 0 && day < 20) {
                zodiac = "Aquarius";
            } else {
                zodiac = "Pisces";
            }
        } else if (month == 3) {
            if (day > 0 && day < 21) {
                zodiac = "Pisces";
            } else {
                zodiac = "Aries";
            }
        } else if (month == 4) {
            if (day > 0 && day < 21) {
                zodiac = "Aries";
            } else {
                zodiac = "Taurus";
            }
        } else if (month == 5) {
            if (day > 0 && day < 22) {
                zodiac = "Taurus";
            } else {
                zodiac = "Gemini";
            }
        } else if (month == 6) {
            if (day > 0 && day < 23) {
                zodiac = "Gemini";
            } else {
                zodiac = "Cancer";
            }
        } else if (month == 7) {
            if (day > 0 && day < 23) {
                zodiac = "Cancer";
            } else {
                zodiac = "Leo";
            }
        } else if (month == 8) {
            if (day > 0 && day < 23) {
                zodiac = "Leo";
            } else {
                zodiac = "Virgo";
            }
        } else if (month == 9) {
            if (day > 0 && day < 23) {
                zodiac = "Virgo";
            } else {
                zodiac = "Libra";
            }
        } else if (month == 10) {
            if (day > 0 && day < 23) {
                zodiac = "Libra";
            } else {
                zodiac = "Scorpio";
            }
        } else if (month == 11) {
            if (day > 0 && day < 22) {
                zodiac = "Scorpio";
            } else {
                zodiac = "Sagittarius";
            }
        } else {
            if (day > 0 && day < 22) {
                zodiac = "Sagittarius";
            } else {
                zodiac = "Capricorn";
            }
        }

        return zodiac;
    }
}
