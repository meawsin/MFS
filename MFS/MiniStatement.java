package MFS;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MiniStatement {
    private static final String FILE_PATH = "ministatement.txt";

    // Add transaction to mini statement
    public static void addMiniStatement(String name, String transactionDetails) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true));
        writer.write(name + " " + new SimpleDateFormat("dd/MM/yyyy").format(new Date()) + " " + transactionDetails + "\n");
        writer.close();
    }

    // View mini statement for the current user
    public static void viewMiniStatement(String name) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
        String line;
        List<String> transactions = new ArrayList<>();

        System.out.println("Mini Statement for " + name + ":");

        while ((line = reader.readLine()) != null) {
            if (line.startsWith(name)) {
                transactions.add(line);
            }
        }

        reader.close();

        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            for (String transaction : transactions) {
                System.out.println(transaction);
            }
        }
    }
}
