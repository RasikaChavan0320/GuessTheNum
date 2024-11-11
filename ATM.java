import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Account {
    private String accountNumber;
    private String pin;
    private double balance;

    public Account(String accountNumber, String pin, double initialBalance) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = initialBalance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public boolean authenticate(String pin) {
        return this.pin.equals(pin);
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        } else {
            return false;
        }
    }
}

public class ATM {
    private static Map<String, Account> accounts = new HashMap<>();
    private static Account currentAccount = null;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Create sample accounts
        accounts.put("123456", new Account("123456", "1234", 5000.0));
        accounts.put("654321", new Account("654321", "4321", 3000.0));

        System.out.println("Welcome to the ATM!");

        if (authenticateUser()) {
            showMainMenu();
        } else {
            System.out.println("Authentication failed. Exiting.");
        }

        scanner.close();
    }

    private static boolean authenticateUser() {
        System.out.print("Enter your account number: ");
        String accountNumber = scanner.nextLine();

        System.out.print("Enter your PIN: ");
        String pin = scanner.nextLine();

        Account account = accounts.get(accountNumber);
        if (account != null && account.authenticate(pin)) {
            currentAccount = account;
            return true;
        } else {
            System.out.println("Invalid account number or PIN.");
            return false;
        }
    }

    private static void showMainMenu() {
        int option;
        do {
            System.out.println("\nMain Menu:");
            System.out.println("1. Balance Inquiry");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Clear invalid input
            }
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    balanceInquiry();
                    break;
                case 2:
                    depositMoney();
                    break;
                case 3:
                    withdrawMoney();
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (option != 4);
    }

    private static void balanceInquiry() {
        System.out.printf("Your current balance is: $%.2f%n", currentAccount.getBalance());
    }

    private static void depositMoney() {
        System.out.print("Enter the amount to deposit: ");
        while (!scanner.hasNextDouble()) {
            System.out.println("Invalid amount. Please enter a positive number.");
            scanner.next();
        }
        double amount = scanner.nextDouble();

        if (amount > 0) {
            currentAccount.deposit(amount);
            System.out.printf("Successfully deposited $%.2f. New balance: $%.2f%n", amount, currentAccount.getBalance());
        } else {
            System.out.println("Invalid amount. Please enter a positive number.");
        }
    }

    private static void withdrawMoney() {
        System.out.print("Enter the amount to withdraw: ");
        while (!scanner.hasNextDouble()) {
            System.out.println("Invalid amount. Please enter a positive number.");
            scanner.next();
        }
        double amount = scanner.nextDouble();

        if (amount > 0) {
            if (currentAccount.withdraw(amount)) {
                System.out.printf("Successfully withdrew $%.2f. Remaining balance: $%.2f%n", amount, currentAccount.getBalance());
            } else {
                System.out.println("Insufficient balance.");
            }
        } else {
            System.out.println("Invalid amount. Please enter a positive number.");
        }
    }
}