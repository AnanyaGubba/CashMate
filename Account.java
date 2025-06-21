package Package1;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Account {
    private int customerNumber;
    private int pinNumber;
    protected double checkingBalance = 0;
    protected double savingBalance = 0;
    Scanner input = new Scanner(System.in);

    DecimalFormat moneyFormat = new DecimalFormat("'$'###,##0.00");

    public int setCustomerNumber(int customerNumber) {
        this.customerNumber = customerNumber;
        return customerNumber;
    }

    public int getCustomerNumber() {
        return customerNumber;
    }

    public int setPinNumber(int pinNumber) {
        this.pinNumber = pinNumber;
        return pinNumber;
    }

    public int getPinNumber() {
        return pinNumber;
    }

    public double getCheckingBalance() {
        return checkingBalance;
    }

    public double getSavingBalance() {
        return savingBalance;
    }

    public double calcCheckingWithdraw(double amount) {
        if (amount > 0 && checkingBalance >= amount) {
            checkingBalance -= amount;
        } else {
            System.out.println("Invalid withdrawal amount or insufficient funds.");
        }
        return checkingBalance;
    }

    public double calcSavingWithdraw(double amount) {
        if (amount > 0 && savingBalance >= amount) {
            savingBalance -= amount;
        } else {
            System.out.println("Invalid withdrawal amount or insufficient funds.");
        }
        return savingBalance;
    }

    public double calcCheckingDeposit(double amount) {
        if (amount > 0) {
            checkingBalance += amount;
        } else {
            System.out.println("Invalid deposit amount.");
        }
        return checkingBalance;
    }

    public double calcSavingDeposit(double amount) {
        if (amount > 0) {
            savingBalance += amount;
        } else {
            System.out.println("Invalid deposit amount.");
        }
        return savingBalance;
    }

    public void getCheckingWithdrawInput() {
        System.out.println("Checking Account Balance: " + moneyFormat.format(checkingBalance));
        System.out.print("Enter the amount to withdraw from Checking Account: ");
        double amount = input.nextDouble();
        calcCheckingWithdraw(amount);
        System.out.println("New Checking Account Balance: " + moneyFormat.format(checkingBalance));
    }

    public void getSavingWithdrawInput() {
        System.out.println("Saving Account Balance: " + moneyFormat.format(savingBalance));
        System.out.print("Enter the amount to withdraw from Saving Account: ");
        double amount = input.nextDouble();
        calcSavingWithdraw(amount);
        System.out.println("New Saving Account Balance: " + moneyFormat.format(savingBalance));
    }

    public void getCheckingDepositInput() {
        System.out.println("Checking Account Balance: " + moneyFormat.format(checkingBalance));
        System.out.print("Enter the amount to deposit in Checking Account: ");
        double amount = input.nextDouble();
        calcCheckingDeposit(amount);
        System.out.println("New Checking Account Balance: " + moneyFormat.format(checkingBalance));
    }

    public void getSavingDepositInput() {
        System.out.println("Saving Account Balance: " + moneyFormat.format(savingBalance));
        System.out.print("Enter the amount to deposit in Saving Account: ");
        double amount = input.nextDouble();
        calcSavingDeposit(amount);
        System.out.println("New Saving Account Balance: " + moneyFormat.format(savingBalance));
    }
}
