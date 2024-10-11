package MFS;

import java.io.IOException;
import java.util.Scanner;

public class MainMenu {
    static Scanner sc = new Scanner(System.in);
    static User currentUser;

    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to MFS File System");
        System.out.print("Enter your phone number: ");
        String phoneNumber = sc.nextLine();

        // Fetch user details from file or add new user
        currentUser = User.findUserByPhoneNumber(phoneNumber);

        if (currentUser == null) {
            // If no user found, add a new user
            User.addNewUser(phoneNumber);
            currentUser = User.findUserByPhoneNumber(phoneNumber);
        }

        System.out.println("Welcome " + currentUser.getName() + "!");
        System.out.print("Enter your PIN: ");
        String enteredPin = sc.nextLine();

        if (!currentUser.getPin().equals(enteredPin)) {
            System.out.println("Incorrect PIN. Exiting...");
            return;
        }

        getAccounts();
    }

    // Main menu options
    public static void getAccounts() throws IOException {
        System.out.println("Main Menu:");
        System.out.println("1. View Balance");
        System.out.println("2. Deposit Money");
        System.out.println("3. Transfer Money");
        System.out.println("4. My Account");
        System.out.println("5. Mini Statement");
        System.out.println("6. Exit");

        System.out.print("Enter Your Option: ");
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                viewBalance();
                break;
            case 2:
                depositMoney();
                break;
            case 3:
                transferMoney();
                break;
            case 4:
                myAccount();
                break;
            case 5:
                MiniStatement.viewMiniStatement(currentUser.getName());
                break;
            case 6:
                System.out.println("Thank you for using MFS.");
                return;
            default:
                System.out.println("Invalid Option. Try again.");
                getAccounts();
        }
    }

    // View balance
    public static void viewBalance() throws IOException {
        System.out.println("Your Balance is: " + currentUser.getBalance());
        getAccounts();
    }

    // Deposit money
    public static void depositMoney() throws IOException {
        System.out.print("Enter the amount to deposit: ");
        double amount = sc.nextDouble();

        if (amount <= 0 || amount > 30000) {
            System.out.println("Invalid deposit amount. Max deposit limit is 30,000.");
        } else {
            currentUser.setBalance(currentUser.getBalance() + amount);
            currentUser.updateUserFile();
            MiniStatement.addMiniStatement(currentUser.getName(), "Deposited " + amount);
            System.out.println(amount + " has been deposited. New balance: " + currentUser.getBalance());
        }

        getAccounts();
    }

    // Transfer money
    public static void transferMoney() throws IOException {
        System.out.print("Enter recipient's phone number: ");
        String recipientPhone = sc.next();
        User recipient = User.findUserByPhoneNumber(recipientPhone);

        if (recipient == null) {
            System.out.println("Recipient not found.");
            getAccounts();
            return;
        }

        System.out.print("Enter the amount to transfer: ");
        double amount = sc.nextDouble();

        if (amount <= 0 || currentUser.getBalance() < amount) {
            System.out.println("Insufficient balance or invalid amount.");
        } else {
            currentUser.setBalance(currentUser.getBalance() - amount);
            recipient.setBalance(recipient.getBalance() + amount);

            currentUser.updateUserFile();
            recipient.updateUserFile();

            MiniStatement.addMiniStatement(currentUser.getName(), "Transferred " + amount + " to " + recipient.getPhoneNumber());
            MiniStatement.addMiniStatement(recipient.getName(), "Received " + amount + " from " + currentUser.getPhoneNumber());

            System.out.println(amount + " has been transferred to " + recipient.getPhoneNumber());
        }

        getAccounts();
    }

    // My Account section
    public static void myAccount() throws IOException {
        System.out.println("My Account Details:");
        System.out.println("Name: " + currentUser.getName());
        System.out.println("Phone: " + currentUser.getPhoneNumber());
        System.out.println("Balance: " + currentUser.getBalance());

        System.out.println("1. Reset PIN");
        System.out.println("2. View Mini Statement");
        System.out.println("3. Back to Main Menu");
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                resetPin();
                break;
            case 2:
                MiniStatement.viewMiniStatement(currentUser.getName());
                break;
            case 3:
                getAccounts();
                break;
            default:
                System.out.println("Invalid Option.");
                myAccount();
        }
    }

    // Reset PIN option
    public static void resetPin() throws IOException {
        System.out.print("Enter your new PIN: ");
        String newPin = sc.next();
        currentUser.setPin(newPin);
        currentUser.updateUserFile();
        System.out.println("PIN reset successfully.");
        myAccount();
    }
}
