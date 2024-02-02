package HackerRankChallenges;

public class HackerRankGenerics {
    public static void main(String[] args) {
        Integer[] arrInt = { 1, 2, 3 };
        String[] arrStr = { "Hello", "World" };

        printArray(arrInt);
        printArray(arrStr);
    }

    public static <T> void printArray(T[] arr) {
        for (T t : arr) {
            System.out.println(t);
        }
    }
}
