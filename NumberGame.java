import java.util.Random;
import java.util.Scanner;

public class NumberGame {
    public static void main(String[] args) {
        System.out.println("Jay Bhagwan");

        Scanner sc = new Scanner(System.in);
        Random r = new Random();

        boolean keepPlaying = true;

        int playingRound = 0;
        int wins = 0;

        while (keepPlaying) {

            displayMenu();
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    playingRound++;
                    if (playGame(sc, r)) {
                        wins++;
                    }
                    break;
                case 2:
                    keepPlaying = false;
                    break;
                default:
                    System.out.println("Please enter a valid number!");
                    break;
            }
        }

        System.out.println("You played " + playingRound + " rounds");
        System.out.println("You won " + wins + " rounds");
    }

    private static void displayMenu() {
        System.out.println("1 to play again.");
        System.out.println("2 to exit.");
        System.out.print("Enter your choice: ");
    }

    private static boolean playGame(Scanner sc, Random r) {
        int randomNumber = r.nextInt(100);
        int guess = -1;
        int remainingChances = 10;

        while (remainingChances > 0 && guess != randomNumber) {
            System.out.print("Enter your guess: ");
            guess = sc.nextInt();
            remainingChances--;

            if (guess > randomNumber) {
                System.out.println("Your guess is greater than the random number.");
            } else if (guess < randomNumber) {
                System.out.println("Your guess is less than the random number.");
            }

            if (guess != randomNumber) {
                System.out.println("You have " + remainingChances + " chances remaining.");
            }
        }

        if (guess == randomNumber) {
            System.out.println("Congratulations, you guessed the correct number: " + guess);
            return true;
        } else {
            System.out.println("You've run out of chances! The correct number was: " + randomNumber);
            return false;
        }
    }
}