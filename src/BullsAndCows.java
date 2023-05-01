import java.util.Scanner;

public class BullsAndCows {
    public static void main(String[] args) {
        playGame();
    }

    public static void playGame() {
        System.out.println("Please, enter the secret code's length:");
        Scanner sc = new Scanner(System.in);
        int length = sc.nextInt();
        String password = generatePassword(length);
        System.out.println("Okay, let's start a game!");
        String inputCode = sc.nextLine();
        int counter = 1;

        while (!inputCode.equals(password)) {
            System.out.printf("Turn %d:\n", counter);
            inputCode = sc.nextLine();
            checkBullsAndCows(password, inputCode, length);
            counter++;
        }
    }

    public static StringBuilder generate(int length) {
        StringBuilder password = new StringBuilder();
        long randomNum = System.nanoTime();
        while (length != 0) {
            password.append(randomNum % 10);
            randomNum /= 10;
            length--;
        }
        return password;
    }

    public static boolean isUnique(StringBuilder password) {
        String str = password.toString();
        return str.chars().distinct().count() == str.length();
    }

    public static String generatePassword(int len) {
        StringBuilder password = new StringBuilder(generate(len));

        if (len > 10) {
            System.out.println("Error: can't generate a secret number with a length of 11 because there aren't enough unique digits.");
        } else {
            while (!isUnique(password)) {
                password = generate(len);
            }
            System.out.printf("The random secret number is %d.", Long.parseLong(password.toString()));
        }
        return password.toString();
    }

    public static void checkBullsAndCows(String secretCode, String userInputCode, int len) {
        int bulls = 0;
        int cows = 0;

        for (int i = 0; i < userInputCode.length(); i++) {
            char strDigit = userInputCode.charAt(i);
            char secretCodeDigit = secretCode.charAt(i);
            if (strDigit == secretCodeDigit) {
                bulls++;
            } else if (secretCode.indexOf(strDigit) != -1) {
                cows++;
            }
        }

        if (bulls > 0 && bulls < len || cows > 0) {
            String result = String.format("Grade: %d bull(s) and %d cow(s). The secret code is %s.", bulls, cows,
                    secretCode);
            System.out.println(result);
        } else if (cows == 0 && bulls < len) {
            String result = String.format("Grade: %d bull(s). The secret code is %s.", bulls, secretCode);
            System.out.println(result);
        } else if (bulls == 0) {
            String result = String.format("Grade: %d cows(s). The secret code is %s.", cows, secretCode);
            System.out.println(result);
        } else if (bulls == len) {
            String result = String.format("Grade: %s bulls", bulls);
            System.out.println(result);
            System.out.println("Congratulations! You guessed the secret code.");
        } else {
            String result = String.format("Grade: None. The secret code is %s.", secretCode);
            System.out.println(result);
        }
    }
}
