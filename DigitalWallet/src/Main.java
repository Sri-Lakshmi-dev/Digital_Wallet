```java
import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    Wallet wallet = new Wallet();

    JLabel balanceLabel;
    JTextField amountField;
    JTextArea historyArea;

    public Main() {

        setTitle("My Simple Digital Wallet");
        setSize(420, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Balance
        balanceLabel = new JLabel();
        balanceLabel.setFont(new Font("Arial", Font.BOLD, 22));
        balanceLabel.setHorizontalAlignment(JLabel.CENTER);

        updateBalance();

        add(balanceLabel, BorderLayout.NORTH);

        // Transaction history
        historyArea = new JTextArea();
        historyArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(historyArea);
        scrollPane.setBorder(
                BorderFactory.createTitledBorder("Transaction History")
        );

        add(scrollPane, BorderLayout.CENTER);

        // Amount
        JPanel bottomPanel = new JPanel();

        bottomPanel.add(new JLabel("Amount: $"));

        amountField = new JTextField(10);
        bottomPanel.add(amountField);

        // Buttons
        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");

        bottomPanel.add(depositButton);
        bottomPanel.add(withdrawButton);

        add(bottomPanel, BorderLayout.SOUTH);

        // Button actions
        depositButton.addActionListener(e -> deposit());
        withdrawButton.addActionListener(e -> withdraw());

        updateHistory();
    }

    // Deposit money
    void deposit() {

        double amount = getAmount();

        if (amount > 0) {

            wallet.deposit(amount);

            updateBalance();
            updateHistory();

            amountField.setText("");
        }
    }

    // Withdraw money
    void withdraw() {

        double amount = getAmount();

        if (amount > 0) {

            if (wallet.withdraw(amount)) {

                updateBalance();
                updateHistory();

                amountField.setText("");

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "You don't have enough balance.",
                        "Withdraw Failed",
                        JOptionPane.WARNING_MESSAGE
                );
            }
        }
    }

    // Get amount from the text box
    double getAmount() {

        try {

            double amount =
                    Double.parseDouble(amountField.getText());

            if (amount <= 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Enter an amount greater than 0."
                );

                return -1;
            }

            return amount;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid number."
            );

            return -1;
        }
    }

    // Show balance
    void updateBalance() {

        balanceLabel.setText(
                String.format(
                        "Balance: $%.2f",
                        wallet.getBalance()
                )
        );
    }

    // Show transaction history
    void updateHistory() {

        historyArea.setText("");

        for (Transaction t : wallet.getHistory()) {

            historyArea.append(t.toString() + "\n");
        }
    }

    public static void main(String[] args) {

        Main window = new Main();

        window.setVisible(true);
    }
}
```
