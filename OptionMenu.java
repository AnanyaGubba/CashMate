package Package1;

import java.sql.*;
import java.text.DecimalFormat;

public class OptionMenu extends Account {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/ATM_DB";
    private static final String USER = "root";
    private static final String PASS = "@Gubb4788"; // Ensure this matches your database password

    DecimalFormat moneyFormat = new DecimalFormat("'$'###,##0.00");

    private Connection connectToDatabase() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    // Validate login by checking customer number and PIN in the database
    public boolean validateLogin(int customerNumber, int pinNumber) {
        try (Connection conn = connectToDatabase()) {
            String query = "SELECT * FROM Customer WHERE customerNumber = ? AND pinNumber = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, customerNumber);
                stmt.setInt(2, pinNumber);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    setCustomerNumber(customerNumber);
                    setPinNumber(pinNumber);
                    loadAccountBalances(conn);
                    return true;
                }
            }
        } catch (SQLException e) {
            System.out.println("Database connection error: " + e.getMessage());
        }
        return false;
    }

    // Load account balances from the database
    private void loadAccountBalances(Connection conn) throws SQLException {
        String query = "SELECT checkingBalance, savingBalance FROM Account WHERE customerNumber = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, getCustomerNumber());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                checkingBalance = rs.getDouble("checkingBalance");
                savingBalance = rs.getDouble("savingBalance");
            }
        }
    }

    // Update balance in the database based on account type
    private void updateBalance(String accountType, double newBalance) throws SQLException {
        String query = "UPDATE Account SET " + accountType + " = ? WHERE customerNumber = ?";
        try (Connection conn = connectToDatabase(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDouble(1, newBalance);
            stmt.setInt(2, getCustomerNumber());
            stmt.executeUpdate();
        }
    }

    // Methods for checking account operations

    public double viewCheckingBalance() {
        return getCheckingBalance();
    }

    public double withdrawFromChecking(double amount) {
        checkingBalance = calcCheckingWithdraw(amount);
        try {
            updateBalance("checkingBalance", checkingBalance);
        } catch (SQLException e) {
            System.out.println("Error updating checking balance: " + e.getMessage());
        }
        return checkingBalance;
    }

    public double depositToChecking(double amount) {
        checkingBalance = calcCheckingDeposit(amount);
        try {
            updateBalance("checkingBalance", checkingBalance);
        } catch (SQLException e) {
            System.out.println("Error updating checking balance: " + e.getMessage());
        }
        return checkingBalance;
    }

    // Methods for saving account operations

    public double viewSavingBalance() {
        return getSavingBalance();
    }

    public double withdrawFromSaving(double amount) {
        savingBalance = calcSavingWithdraw(amount);
        try {
            updateBalance("savingBalance", savingBalance);
        } catch (SQLException e) {
            System.out.println("Error updating saving balance: " + e.getMessage());
        }
        return savingBalance;
    }

    public double depositToSaving(double amount) {
        savingBalance = calcSavingDeposit(amount);
        try {
            updateBalance("savingBalance", savingBalance);
        } catch (SQLException e) {
            System.out.println("Error updating saving balance: " + e.getMessage());
        }
        return savingBalance;
    }
}
