```java
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

// This class manages the wallet

public class Wallet {

    private double balance;
    private ArrayList<Transaction> history;

    private final String FILE_NAME = "wallet_data.txt";

    // Create wallet
    public Wallet() {

        balance = 0;
        history = new ArrayList<Transaction>();

        loadFromFile();
    }

    // Get balance
    public double getBalance() {

        return balance;
    }

    // Get transaction history
    public ArrayList<Transaction> getHistory() {

        return history;
    }

    // Add money
    public void deposit(double amount) {

        balance = balance + amount;

        String date = getDate();

        Transaction transaction =
                new Transaction("Deposit", amount, date);

        history.add(transaction);

        saveToFile();
    }

    // Remove money
    public boolean withdraw(double amount) {

        if (amount > balance) {

            return false;
        }

        balance = balance - amount;

        String date = getDate();

        Transaction transaction =
                new Transaction("Withdraw", amount, date);

        history.add(transaction);

        saveToFile();

        return true;
    }

    // Get current date and time
    private String getDate() {

        SimpleDateFormat format =
                new SimpleDateFormat("yyyy-MM-dd HH:mm");

        return format.format(new Date());
    }

    // Save wallet data
    private void saveToFile() {

        try {

            FileWriter writer =
                    new FileWriter(FILE_NAME);

            writer.write(balance + "\n");

            for (Transaction t : history) {

                writer.write(
                        t.toFileFormat() + "\n"
                );
            }

            writer.close();

        } catch (IOException e) {

            System.out.println(
                    "Could not save wallet data."
            );
        }
    }

    // Load wallet data
    private void loadFromFile() {

        try {

            FileReader file =
                    new FileReader(FILE_NAME);

            BufferedReader reader =
                    new BufferedReader(file);

            // First line contains balance
            String line = reader.readLine();

            if (line != null) {

                balance = Double.parseDouble(line);
            }

            // Remaining lines contain transactions
            line = reader.readLine();

            while (line != null) {

                if (!line.trim().isEmpty()) {

                    Transaction t =
                            Transaction.fromFileFormat(line);
                    history.add(t);
                }

                line = reader.readLine();
            }

            reader.close();

        } catch (IOException e) {

            // First time running the program
            System.out.println(
                    "Starting new wallet."
            );
        }
    }
}
```

