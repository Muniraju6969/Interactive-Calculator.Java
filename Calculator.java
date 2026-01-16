import java.util.Scanner; // Import Scanner for console input

public class Calculator {
    // Instance variable (belongs to the object, initialized per instance)
    private double result; // Stores the calculation result; double chosen for precision in divisions (8 bytes memory)

    // Static variable (belongs to the class, shared across instances)
    private static int operationCount = 0; // Tracks how many operations performed; int (4 bytes) sufficient for counting

    public static void main(String[] args) {
        // Local variables (scope limited to this method)
        byte byteVar = 127; // Byte: 1 byte memory, range -128 to 127; chosen for small integers
        short shortVar = 32767; // Short: 2 bytes, range -32,768 to 32,767; for medium-small ints
        int intVar = 2147483647; // Int: 4 bytes, most common for integers
        long longVar = 9223372036854775807L; // Long: 8 bytes, for very large integers; note the 'L' suffix
        float floatVar = 3.4028235E38f; // Float: 4 bytes, single-precision decimal; 'f' suffix
        double doubleVar = 1.7976931348623157E308; // Double: 8 bytes, double-precision; default for decimals
        char charVar = 'A'; // Char: 2 bytes, single character or Unicode
        boolean boolVar = true; // Boolean: 1 byte (typically), true/false

        // Explain memory usage in comments: All primitives have fixed sizes, unlike non-primitives (objects) which vary.

        Scanner scanner = new Scanner(System.in); // Scanner preferred over BufferedReader for simpler input parsing

        System.out.println("Welcome to Interactive Calculator!");
        System.out.println("Primitive Data Types Demo:");
        System.out.printf("Byte: %d (1 byte)%n", byteVar);
        System.out.printf("Short: %d (2 bytes)%n", shortVar);
        System.out.printf("Int: %d (4 bytes)%n", intVar);
        System.out.printf("Long: %d (8 bytes)%n", longVar);
        System.out.printf("Float: %f (4 bytes)%n", floatVar);
        System.out.printf("Double: %f (8 bytes)%n", doubleVar);
        System.out.printf("Char: %c (2 bytes)%n", charVar);
        System.out.printf("Boolean: %b (1 byte)%n", boolVar);

        // Demonstrate type casting
        System.out.println("\nType Casting Demo:");
        int intValue = 100;
        double doubleFromInt = intValue; // Implicit (widening): int to double (compatible)
        System.out.printf("Implicit cast: int %d to double %.2f%n", intValue, doubleFromInt);

        double doubleValue = 99.99;
        int intFromDouble = (int) doubleValue; // Explicit (narrowing): double to int (may lose data)
        System.out.printf("Explicit cast: double %.2f to int %d%n", doubleValue, intFromDouble);

        // Incompatible types example (requires manual handling)
        // String to int: Use parsing instead of cast
        System.out.println("Enter a number as string (e.g., '123'):");
        String strInput = scanner.nextLine();
        try {
            int parsedInt = Integer.parseInt(strInput); // Parsing, not casting
            System.out.printf("Parsed string to int: %d%n", parsedInt);
        } catch (NumberFormatException e) {
            System.out.println("Invalid string for int parsing!");
        }

        // Interactive Calculator Loop
        Calculator calc = new Calculator(); // Create instance to use instance variable
        boolean continueCalc = true;
        while (continueCalc) {
            System.out.println("\nEnter first number (double):");
            if (!scanner.hasNextDouble()) { // Handle invalid input
                System.out.println("Invalid input! Please enter a number.");
                scanner.next(); // Clear invalid input
                continue;
            }
            double num1 = scanner.nextDouble();

            System.out.println("Enter second number (double):");
            if (!scanner.hasNextDouble()) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.next();
                continue;
            }
            double num2 = scanner.nextDouble();

            scanner.nextLine(); // Clear newline
            System.out.println("Enter operation (+, -, *, /) or 'q' to quit:");
            String operation = scanner.nextLine();

            if (operation.equals("q")) {
                continueCalc = false;
                break;
            }

            // Perform arithmetic operations
            switch (operation) {
                case "+":
                    calc.result = num1 + num2;
                    break;
                case "-":
                    calc.result = num1 - num2;
                    break;
                case "*":
                    calc.result = num1 * num2;
                    break;
                case "/":
                    if (num2 == 0) {
                        System.out.println("Error: Division by zero!");
                        continue;
                    }
                    calc.result = num1 / num2;
                    break;
                default:
                    System.out.println("Invalid operation!");
                    continue;
            }

            // Formatted output
            System.out.printf("Result: %.2f %s %.2f = %.2f%n", num1, operation, num2, calc.result);
            operationCount++; // Increment static counter
            System.out.printf("Operations performed: %d%n", operationCount);

            // Show variable scopes
            // Local: num1, num2 (only in this method)
            // Instance: result (accessible via calc object)
            // Static: operationCount (accessible directly via class)
        }

        scanner.close();
        System.out.println("Calculator closed. Default values: Primitives have defaults (e.g., int=0, boolean=false), non-primitives=null.");

        // Interview Notes:
        // - Primitive vs Non-primitive: Primitives are basic (int, double), fixed size, no methods. Non-primitives are objects (String, arrays), can be null, have methods.
        // - Type Casting: Converting types, implicit (safe widening) or explicit (risky narrowing).
        // - Scanner vs BufferedReader: Scanner easier for parsing types; BufferedReader faster for large input but more verbose.
        // - Default Values: Locals uninitialized (error if used), instance/static have defaults (0, false, null).
        // - Scope: Local (method), Instance (class object), Static (class-wide).
    }
}