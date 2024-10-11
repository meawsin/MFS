package MFS;

import java.io.*;
import java.util.Scanner;

public class User {
    private String phoneNumber;
    private String pin;
    private String name;
    private double balance;

    public User(String phoneNumber, String pin, String name, double balance) {
        this.phoneNumber = phoneNumber;
        this.pin = pin;
        this.name = name;
        this.balance = balance;
    }

    // Find user by phone number
    public static User findUserByPhoneNumber(String phoneNumber) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("users.txt"));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] userData = line.split(" ");
            if (userData[0].equals(phoneNumber)) {
                return new User(userData[0], userData[1], userData[2], Double.parseDouble(userData[3]));
            }
        }
        reader.close();
        return null;
    }

    // Add new user
    public static void addNewUser(String phoneNumber) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Phone number not found! Add new user.");
        System.out.print("Enter your name: ");
        String name = sc.nextLine();
        System.out.print("Set your PIN: ");
        String pin = sc.nextLine();

        // Add new user to file with default balance 500
        FileWriter writer = new FileWriter("users.txt", true);
        writer.write(phoneNumber + " " + pin + " " + name + " 500.00\n");
        writer.close();
        System.out.println("New user added successfully with a balance of 500.");
    }

    // Update user balance and PIN in the file
    public void updateUserFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("users.txt"));
        StringBuilder sb = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            String[] userData = line.split(" ");
            if (userData[0].equals(phoneNumber)) {
                // Update user's balance and PIN
                sb.append(phoneNumber).append(" ").append(pin).append(" ").append(name).append(" ").append(balance).append("\n");
            } else {
                sb.append(line).append("\n");
            }
        }
        reader.close();

        // Write the updated content back to the file
        FileWriter writer = new FileWriter("users.txt");
        writer.write(sb.toString());
        writer.close();
    }

    // Getter and setter methods
    public String getPhoneNumber() { return phoneNumber; }
    public String getPin() { return pin; }
    public String getName() { return name; }
    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }
    public void setPin(String newPin) { this.pin = newPin; }
}
