import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInputProcessor {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            int firstInt = readInteger(scanner, "Enter the first integer: ");
            int secondInt = readInteger(scanner, "Enter the second integer: ");
            double floatValue = readDouble(scanner, "Enter a floating-point number: ");
            char character = readCharacter(scanner, "Enter a single character: ");
            boolean booleanValue = readBoolean(scanner, "Enter a boolean value (true/false): ");
            String name = readString(scanner, "Enter your name: ");

            displayResults(firstInt, secondInt, floatValue, character, booleanValue, name);
        } catch (InputMismatchException ime) {
            System.out.println("Invalid input type. Please enter values in the correct format.");
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    /**
     * Prompts the user to enter an integer and validates the input.
     */
    private static int readInteger(Scanner scanner, String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. " + prompt);
            scanner.next();
        }
        return scanner.nextInt();
    }

    /**
     * Prompts the user to enter a floating-point number and validates the input.
     */
    private static double readDouble(Scanner scanner, String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            System.out.print("Invalid input. " + prompt);
            scanner.next();
        }
        return scanner.nextDouble();
    }

    /**
     * Prompts the user to enter a single character and validates the input length.
     */
    private static char readCharacter(Scanner scanner, String prompt) {
        System.out.print(prompt);
        String input = scanner.next();
        while (input.length() != 1) {
            System.out.print("Please enter a single character: ");
            input = scanner.next();
        }
        return input.charAt(0);
    }

    /**
     * Prompts the user to enter a boolean value and validates the input.
     */
    private static boolean readBoolean(Scanner scanner, String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextBoolean()) {
            System.out.print("Invalid input. " + prompt);
            scanner.next();
        }
        return scanner.nextBoolean();
    }

    /**
     * Prompts the user to enter a string (name).
     */
    private static String readString(Scanner scanner, String prompt) {
        System.out.print(prompt);
        scanner.nextLine();
        return scanner.nextLine();
    }

    /**
     * Displays the computed results based on input.
     */
    private static void displayResults(int num1, int num2, double floatNum, char ch, boolean boolVal, String name) {
        System.out.println();
        System.out.println("Sum of " + num1 + " and " + num2 + " is: " + calculateSum(num1, num2));
        System.out.println("Difference between " + num1 + " and " + num2 + " is: " + calculateDifference(num1, num2));
        System.out.println("Product of " + num1 + " and " + num2 + " is: " + calculateProduct(num1, num2));
        System.out.println(floatNum + " multiplied by 2 is: " + multiplyByTwo(floatNum));
        System.out.println("The next character after '" + ch + "' is: " + getNextCharacter(ch));
        System.out.println("The opposite of " + boolVal + " is: " + getOppositeBoolean(boolVal));
        System.out.println("Hello, " + name + "!");
    }

    /**
     * Returns the sum of two integers.
     */
    private static int calculateSum(int a, int b) {
        return a + b;
    }

    /**
     * Returns the difference between two integers.
     */
    private static int calculateDifference(int a, int b) {
        return a - b;
    }

    /**
     * Returns the product of two integers.
     */
    private static int calculateProduct(int a, int b) {
        return a * b;
    }

    /**
     * Multiplies a floating-point number by two and returns the result.
     */
    private static double multiplyByTwo(double number) {
        return number * 2;
    }

    /**
     * Returns the next ASCII character following the given character.
     */
    private static char getNextCharacter(char ch) {
        return (char) (ch + 1);
    }

    /**
     * Returns the opposite boolean value.
     */
    private static boolean getOppositeBoolean(boolean value) {
        return !value;
    }
}
