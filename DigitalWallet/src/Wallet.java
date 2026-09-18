import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Wallet {

    private double balance;
    private ArrayList<Transaction> history;

    private final String FILE_NAME = "wallet_data.txt";

    public Wallet() {
        balance = 0.0;
        history = new ArrayList<Transaction>();
        loadFromFile();
    }

    public double getBalance() {
        return balance;
    }

    public ArrayList<Transaction> getHistory() {
        return history;
    }

    public void deposit(double amount) {
        balance = balance + amount;
        String today = getTodayDate();
        history.add(new Transaction("Deposit", amount, today));
        saveToFile();
    }

    public boolean withdraw(double amount) {
        if (amount > balance) {
            return false;
        }
        balance = balance - amount;
        String today = getTodayDate();
        history.add(new Transaction("Withdraw", amount, today));
        saveToFile();
        return true;
    }

    private String getTodayDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return formatter.format(new Date());
    }

    private void saveToFile() {
        try {
            FileWriter writer = new FileWriter(FILE_NAME);
            writer.write(balance + "\n");
            for (int i = 0; i < history.size(); i++) {
                writer.write(history.get(i).toFileFormat() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Could not save wallet data: " + e.getMessage());
        }
    }

    private void loadFromFile() {
        try {
            FileReader fr = new FileReader(FILE_NAME);
            BufferedReader reader = new BufferedReader(fr);

            String firstLine = reader.readLine();
            if (firstLine != null) {
                balance = Double.parseDouble(firstLine);
            }

            String line = reader.readLine();
            while (line != null) {
                if (!line.trim().isEmpty()) {
                    history.add(Transaction.fromFileFormat(line));
                }
                line = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {
           
            System.out.println("No previous wallet data found. Starting fresh.");
        }
    }
}
