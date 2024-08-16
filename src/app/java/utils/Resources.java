package utils;

import java.util.Scanner;

public class Resources {
    private static final Scanner scanner = new Scanner(System.in);

    public static String readString(String prompt) {
        scanner.nextLine();
        System.out.print(prompt + ": ");
        return scanner.nextLine();
    }

    public static int readInt(String prompt) {
        System.out.print(prompt + ": ");
        return scanner.nextInt();
    }

    public static double readDouble(String prompt) {
        System.out.print(prompt + ": ");
        return scanner.nextDouble();
    }

    public static boolean readBoolean(String prompt) {
        return readBoolean(prompt, true);
    }

    public static boolean readBoolean(String prompt, boolean printPrompt) {
        if (printPrompt) {
            System.out.print(prompt + ": ");
        }
        boolean choice;
        while (true) {
            String input = scanner.nextLine().trim();
            if (input.equals("0")) {
                choice = true;
                break;
            } else if (input.equals("1")) {
                choice = false;
                break;
            } else {
                System.out.print("Por favor, digite apenas 0 para Sim ou 1 para Não: ");
            }
        }
        return choice;
    }
}
