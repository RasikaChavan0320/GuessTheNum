package Task_1;
import java.util.Scanner;
import java.util.Random;

public class GuessTheNum
{

    public static void main(String[] args) 
    {
        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("I have selected a random number between 1 and 100.");
        System.out.println("Your goal is to guess the number. Good luck!");

        Random random = new Random();
        int targetNumber = random.nextInt(100) + 1; 
        Scanner scanner = new Scanner(System.in);
        
        int userGuess = 0; 
        int attempts = 0;  
        while (userGuess != targetNumber) 
        {
            System.out.print("Enter your guess (1 to 100): ");
            
            if (scanner.hasNextInt())
            {
                userGuess = scanner.nextInt();
                attempts++;
                
                if (userGuess < 1 || userGuess > 100) 
                {
                    System.out.println("Invalid guess! Please enter a number between 1 and 100.");
                } 
                else 
                {
                    if (userGuess > targetNumber) 
                    {
                        System.out.println("Too high! Try again.");
                    } 
                    else if (userGuess < targetNumber) 
                    {
                        System.out.println("Too low! Try again.");
                    } 
                    else
                    {
                        
                        System.out.println("Congratulations! You guessed the number correctly.");
                        System.out.println("It took you " + attempts + " attempts to guess the number.");
                    }
                }
            } 
            else 
            {
                System.out.println("Invalid input! Please enter a valid number.");
                scanner.next(); // Clear invalid input
            }
        }

        // Close the scanner to avoid resource leak
        scanner.close();
    }

}