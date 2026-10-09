import java.util.*;

public class Calculator {
    static List<String> history = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    static double calculate(double a, String op, double b) throws Exception {
        switch (op) {
            case "+": return a + b;
            case "-": return a - b;
            case "*": return a * b;
            case "/":
                if (b == 0) throw new Exception("Cannot divide by zero");
                return a / b;
            case "**": return Math.pow(a, b);
            default: throw new Exception("Unknown operator: " + op);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Java Calculator ===");
        System.out.println("Enter: <num> <op> <num> | sqrt <num> | history | quit\n");
        while (true) {
            System.out.print("> ");
            String line = sc.nextLine().trim();
            if (line.equalsIgnoreCase("quit")) { System.out.println("Goodbye!"); break; }
            if (line.equalsIgnoreCase("history")) {
                if (history.isEmpty()) System.out.println("  No history yet.");
                else history.forEach(h -> System.out.println("  " + h));
                continue;
            }
            String[] parts = line.split("\\s+");
            try {
                double result;
                String expr;
                if (parts.length == 2 && parts[0].equalsIgnoreCase("sqrt")) {
                    double a = Double.parseDouble(parts[1]);
                    result = Math.sqrt(a);
                    expr = String.format("sqrt(%.2f) = %.4f", a, result);
                } else if (parts.length == 3) {
                    double a = Double.parseDouble(parts[0]);
                    double b = Double.parseDouble(parts[2]);
                    result = calculate(a, parts[1], b);
                    expr = String.format("%.2f %s %.2f = %.4f", a, parts[1], b, result);
                } else { System.out.println("  Invalid input. Try: 5 + 3"); continue; }
                System.out.printf("  Result: %.4f%n", result);
                history.add(expr);
            } catch (Exception e) { System.out.println("  Error: " + e.getMessage()); }
        }
    }
}
