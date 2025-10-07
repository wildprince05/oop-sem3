interface Bank {
    void deposit(double amount);
    void withdraw(double amount);
}
class Account implements Bank {
    double balance;
    public Account() {
        balance = 0.0;
    }
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited ₹" + amount + " | Balance: ₹" + balance);
        } else {
            System.out.println("Invalid deposit amount!");
        }
    }
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid withdrawal amount!");
        } else if (amount > balance) {
            System.out.println("Insufficient funds!");
        } else {
            balance -= amount;
            System.out.println("Withdrawn ₹" + amount + " | Balance: ₹" + balance);
        }
    }
}
class Transaction {
    void record(String type, double amount) {
        System.out.println("Transaction recorded: " + type + " of ₹" + amount);
    }
}
public class Program6 {
    public static void main(String[] args) {
        Account acc = new Account();   // ✅ Now Java finds the constructor
        Transaction t = new Transaction();
        System.out.println("\nTC1: Deposit ₹1000");
        acc.deposit(1000);
        t.record("Deposit", 1000);
        System.out.println("\nTC2: Withdraw ₹500");
        acc.withdraw(500);
        t.record("Withdraw", 500);
        System.out.println("\nTC3: Withdraw ₹1500");
        acc.withdraw(1500);
        t.record("Withdraw", 1500);
        System.out.println("\nTC4: Deposit -₹100");
        acc.deposit(-100);
        t.record("Deposit", -100);
        System.out.println("\nTC5: Balance Check");
        t.record("Balance", acc.balance);
    }
}
