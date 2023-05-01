import java.util.Scanner;

public class BullsAndCows {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int bulls = 0;
        int cows = 0;
        String secretCode = "9305";
        String str = sc.nextLine();

        for (int i = 0; i < str.length(); i++) {
            char strDigit = str.charAt(i);
            char secretCodeDigit = secretCode.charAt(i);
            if (strDigit == secretCodeDigit) {
                bulls++;
            } else if (secretCode.indexOf(strDigit) != -1) {
                cows++;
            }
        }

        if (bulls > 0 || cows > 0) {
            String result = String.format("Grade: %d bull(s) and %d cow(s). The secret code is %s.", bulls, cows,
                    secretCode);
            System.out.println(result);
        } else if (cows == 0) {
            String result = String.format("Grade: %d bull(s). The secret code is %s.", bulls, secretCode);
            System.out.println(result);
        } else if (bulls == 0) {
            String result = String.format("Grade: %d cows(s). The secret code is %s.", cows, secretCode);
            System.out.println(result);
        } else {
            System.out.println("Grade: None. The secret code is 9305.");
        }
    }
}
