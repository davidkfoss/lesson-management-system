package com.example.lessonmanagement.service;

import java.util.Scanner;

public class InputService {
    private final Scanner scanner;

    public InputService(Scanner scanner) {
        this.scanner = scanner;
    }

    public String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    public double readPositiveDouble(String prompt) {
        double value = -1;
        while (value <= 0) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim().replace(",", ".");
            try {
                value = Double.parseDouble(input);
                if (value <= 0) {
                    System.out.println("Value must be positive. Please try again.");
                    value = -1;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }
        return value;
    }

    public double readDoubleInRange(String prompt, double min, double max) {
        double value = min - 1;
        while (value < min || value > max) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim().replace(",", ".");
            try {
                value = Double.parseDouble(input);
                if (value < min || value > max) {
                    System.out.printf("Please enter a value between %.2f and %.2f.%n", min, max);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }
        return value;
    }

    public int readInt(String prompt) {
        Integer value = null;
        while (value == null) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                value = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter an integer.");
            }
        }
        return value;
    }

    public int readPositiveInt(String prompt) {
        Integer value = null;
        while (value == null || value < 0) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                int candidate = Integer.parseInt(input);
                if (candidate < 0) {
                    System.out.println("Please enter an integer >= 0.");
                    value = null;
                } else {
                    value = candidate;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter an integer.");
            }
        }
        return value;
    }
}
