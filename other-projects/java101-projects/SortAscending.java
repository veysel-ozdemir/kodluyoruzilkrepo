import java.util.ArrayList;
import java.util.Scanner;

public class SortAscending {
    public static void main(String[] args) {
        ArrayList<Integer> nums = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter numbers to be sorted.\nPress S for stopping.\n");
        while (true) {
            System.out.print("Enter number: ");
            String resp = sc.next();

            if (resp.equalsIgnoreCase("s"))
                break;

            try {
                nums.add(Integer.parseInt(resp));
            } catch (NumberFormatException e) {
                System.out.print("*** Invalid value\n");
            }
        }

        System.out.println("\nInitial order: " + nums);

        int min, index, temp;
        // inspired by the selection-sort algorithm
        for (int i = 0; i < nums.size(); i++) {
            min = nums.get(i);
            index = i;
            for (int j = i + 1; j < nums.size(); j++) {
                if (nums.get(j) < min) {
                    min = nums.get(j);
                    index = j;
                }
            }
            temp = nums.get(i);
            nums.set(i, min);
            nums.set(index, temp);
        }

        System.out.println("\nSorted order: " + nums);
    }
}
