package Package1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATM_GUI extends JFrame {
    private OptionMenu optionMenu = new OptionMenu();
    private JTextField customerNumberField;
    private JPasswordField pinField;
    private JLabel messageLabel;

    public ATM_GUI() {
        // Set up the login frame
        setTitle("ATM Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Login Panel
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(3, 2));

        loginPanel.add(new JLabel("Customer Number:"));
        customerNumberField = new JTextField();
        loginPanel.add(customerNumberField);

        loginPanel.add(new JLabel("PIN:"));
        pinField = new JPasswordField();
        loginPanel.add(pinField);

        JButton loginButton = new JButton("Login");
        loginPanel.add(loginButton);

        messageLabel = new JLabel("", SwingConstants.CENTER);
        add(messageLabel, BorderLayout.NORTH);
        add(loginPanel, BorderLayout.CENTER);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int customerNumber = Integer.parseInt(customerNumberField.getText());
                int pinNumber = Integer.parseInt(new String(pinField.getPassword()));

                if (optionMenu.validateLogin(customerNumber, pinNumber)) {
                    showAccountOptions();
                } else {
                    messageLabel.setText("Invalid customer number or PIN.");
                }
            }
        });
    }

    // Show account type options after successful login
    private void showAccountOptions() {
        JFrame accountFrame = new JFrame("Select Account Type");
        accountFrame.setSize(400, 200);
        accountFrame.setLayout(new GridLayout(3, 1));

        JButton checkingButton = new JButton("Checking Account");
        JButton savingButton = new JButton("Saving Account");

        accountFrame.add(checkingButton);
        accountFrame.add(savingButton);

        checkingButton.addActionListener(e -> showCheckingOptions(accountFrame));
        savingButton.addActionListener(e -> showSavingOptions(accountFrame));

        accountFrame.setVisible(true);
    }

    // Show checking account options
    private void showCheckingOptions(JFrame accountFrame) {
        accountFrame.dispose();

        JFrame checkingFrame = new JFrame("Checking Account");
        checkingFrame.setSize(400, 300);
        checkingFrame.setLayout(new GridLayout(4, 1));

        JButton viewBalanceButton = new JButton("View Balance");
        JButton withdrawButton = new JButton("Withdraw");
        JButton depositButton = new JButton("Deposit");

        checkingFrame.add(viewBalanceButton);
        checkingFrame.add(withdrawButton);
        checkingFrame.add(depositButton);

        viewBalanceButton.addActionListener(e -> JOptionPane.showMessageDialog(null,
                "Checking Account Balance: $" + optionMenu.viewCheckingBalance()));

        withdrawButton.addActionListener(e -> {
            String amountStr = JOptionPane.showInputDialog("Enter amount to withdraw:");
            double amount = Double.parseDouble(amountStr);
            double newBalance = optionMenu.withdrawFromChecking(amount);
            JOptionPane.showMessageDialog(null, "New Checking Balance: $" + newBalance);
        });

        depositButton.addActionListener(e -> {
            String amountStr = JOptionPane.showInputDialog("Enter amount to deposit:");
            double amount = Double.parseDouble(amountStr);
            double newBalance = optionMenu.depositToChecking(amount);
            JOptionPane.showMessageDialog(null, "New Checking Balance: $" + newBalance);
        });

        checkingFrame.setVisible(true);
    }

    // Show saving account options
    private void showSavingOptions(JFrame accountFrame) {
        accountFrame.dispose();

        JFrame savingFrame = new JFrame("Saving Account");
        savingFrame.setSize(400, 300);
        savingFrame.setLayout(new GridLayout(4, 1));

        JButton viewBalanceButton = new JButton("View Balance");
        JButton withdrawButton = new JButton("Withdraw");
        JButton depositButton = new JButton("Deposit");

        savingFrame.add(viewBalanceButton);
        savingFrame.add(withdrawButton);
        savingFrame.add(depositButton);

        viewBalanceButton.addActionListener(e -> JOptionPane.showMessageDialog(null,
                "Saving Account Balance: $" + optionMenu.viewSavingBalance()));

        withdrawButton.addActionListener(e -> {
            String amountStr = JOptionPane.showInputDialog("Enter amount to withdraw:");
            double amount = Double.parseDouble(amountStr);
            double newBalance = optionMenu.withdrawFromSaving(amount);
            JOptionPane.showMessageDialog(null, "New Saving Balance: $" + newBalance);
        });

        depositButton.addActionListener(e -> {
            String amountStr = JOptionPane.showInputDialog("Enter amount to deposit:");
            double amount = Double.parseDouble(amountStr);
            double newBalance = optionMenu.depositToSaving(amount);
            JOptionPane.showMessageDialog(null, "New Saving Balance: $" + newBalance);
        });

        savingFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ATM_GUI atmGui = new ATM_GUI();
            atmGui.setVisible(true);
        });
    }
}
