import java.util.*;

class Main {
    public static String calc(String input) {
        String[] tokens = input.split("");
        if (tokens.length != 3) {
            throw new IllegalArgumentException("Invalid input format");
        }
        int a = parseNumber(tokens[0]);
        int b = parseNumber(tokens[2]);
        int result = switch (tokens[1]) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> a / b;
            default -> throw new IllegalArgumentException("Invalid operator");
        };
        return formatResult(result, isRoman(tokens[0]));
    }

    public static int parseNumber(String token) {
        try {
            return Integer.parseInt(token);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number format");
        }
    }

    public static boolean isRoman(String token) {
        return token.matches("[IVX]+");
    }

    public static String formatResult(int result, boolean isRoman) {
        if (isRoman) {
            if (result < 1) {
                throw new IllegalArgumentException("Roman result can not be less than I");
            }
            return toRoman(result);
        } else {
            return Integer.toString(result);
        }
    }

    public static String toRoman(int number) throws IllegalArgumentException {
        if (number < 1 || number > 3999) {
            throw new IllegalArgumentException("Roman number must be between I and MMMCMXCIX");
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ROMAN_VALUES.length; i++) {
            while (number >= ROMAN_VALUES[i]) {
                number -= ROMAN_VALUES[i];
                sb.append(ROMAN_LETTERS[i]);
            }
        }
        return sb.toString();
    }

    public static final int[] ROMAN_VALUES = {
            1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1
    };

    public static final String[] ROMAN_LETTERS = {
            "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"
    };
}

class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("exit")) {
                break;
            }
            try {
                System.out.println(Main.calc(input));
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        scanner.close();
    }
}

