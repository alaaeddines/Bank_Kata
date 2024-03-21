
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Account implements AccountService {

    private List<Transaction> transactions;
    private int balance;

    public Account() {
        this.transactions = new ArrayList<>();
        this.balance = 0;
    }

    public int getBalance() {
        return balance;
    }

    @Override
    public void deposit(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        balance += amount;
        transactions.add(new Transaction(amount, balance, new Date()));
    }

    @Override
    public void withdraw(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient funds for withdrawal");
        }
        balance -= amount;
        transactions.add(new Transaction(-amount, balance, new Date()));
    }

    @Override
    public void printStatement() {
        System.out.println("Date       || Amount || Balance");
        for (Transaction transaction : transactions) {
            System.out.printf("%s || %d || %d\n", transaction.getDateString(), transaction.getAmount(), transaction.getBalance());
        }
    }

    // Transaction class
    private static class Transaction {
        private final int amount;
        private final int balance;
        private final Date date;

        public Transaction(int amount, int balance, Date date) {
            this.amount = amount;
            this.balance = balance;
            this.date = date;
        }

        public String getDateString() {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            return formatter.format(date);
        }

        public int getAmount() {
            return amount;
        }

        public int getBalance() {
            return balance;
        }
    }
}
