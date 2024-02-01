import java.util.Scanner;

public class UserLogin {
    public static void main(String[] args) {
        final String USERNAME = "java";
        String PASSWORD = "java-crew.95";
        String inputUsername, inputPassword, resetSelection, newPassword;
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter username: ");
        inputUsername = sc.nextLine();
        System.out.print("Enter password: ");
        inputPassword = sc.nextLine();

        if (!inputUsername.equals(USERNAME)) {
            System.out.println("Wrong username!");
        } else if (!inputPassword.equals(PASSWORD)) {
            System.out.print("Wrong password!\nReset the password? (Y\\N)\n");
            resetSelection = sc.next();
            if (resetSelection.equalsIgnoreCase("y")) {
                System.out.print("\nThe new password has to be different from the previous.\nNew password: ");
                newPassword = sc.next();

                while (newPassword.equals(PASSWORD)) {
                    System.out.print(
                            "\nPassword could not be generated, please enter another password, or press X for exit.\nNew password: ");
                    newPassword = sc.next();
                    if (newPassword.equalsIgnoreCase("x"))
                        break;
                }
            } else if (resetSelection.equalsIgnoreCase("n")) {
                System.out.println();
            } else {
                System.out.println("Invalid key pressed");
            }
        } else {
            System.out.println("Login Success!");
        }
    }
}
