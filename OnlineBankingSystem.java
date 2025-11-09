import java.util.*;
class Account {
    protected String accountNumber;
    protected String holderName;
    protected double balance;
    public Account(String accountNumber, String holderName, double balance) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = balance;
    }
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: $" + amount);
        } else {
            System.out.println("Invalid deposit amount!");
        }
    }
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: $" + amount);
        } else {
            System.out.println("Insufficient balance or invalid amount!");
        }
    }
    public void displayBalance() {
        System.out.println("Current Balance: $" + balance);
    }
    public String getAccountNumber() {
        return accountNumber;
    }
    public String getHolderName() {
        return holderName;
    }
}
class SavingsAccount extends Account {
    private double interestRate;
    public SavingsAccount(String accountNumber, String holderName, double balance, double interestRate) {
        super(accountNumber, holderName, balance);
        this.interestRate = interestRate;
    }
    public void addInterest() {
        double interest = balance * (interestRate / 100);
        balance += interest;
        System.out.println("Interest added: $" + interest);
    }
}
class CurrentAccount extends Account {
    private double overdraftLimit;
    public CurrentAccount(String accountNumber, String holderName, double balance, double overdraftLimit) {
        super(accountNumber, holderName, balance);
        this.overdraftLimit = overdraftLimit;
    }
    @Override
    public void withdraw(double amount) {
        if (amount > 0 && amount <= (balance + overdraftLimit)) {
            balance -= amount;
            System.out.println("Withdrawn: $" + amount);
        } else {
            System.out.println("Exceeds overdraft limit!");
        }
    }
}
public class OnlineBankingSystem {
    private static Map<String, Account> accounts = new HashMap<>();
    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n=====Online Banking System=====");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Check Balance");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    performDeposit();
                    break;
                case 3:
                    performWithdraw();
                    break;
                case 4:
                    checkBalance();
                    break;
                case 5:
                    System.out.println("Thank you for using Online Banking System!");
                    break;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        } while (choice != 5);
    }
    private static void createAccount() {
        System.out.print("Enter account number: ");
        String accNum = sc.nextLine();
        System.out.print("Enter holder name: ");
        String name = sc.nextLine();
        System.out.print("Enter initial balance: ");
        double balance = sc.nextDouble();
        System.out.println("Select account type:");
        System.out.println("1. Savings Account");
        System.out.println("2. Current Account");
        int type = sc.nextInt();
        Account account;
        if (type == 1) {
            System.out.print("Enter interest rate (%): ");
            double rate = sc.nextDouble();
            account = new SavingsAccount(accNum, name, balance, rate);
        } else {
            System.out.print("Enter overdraft limit: ");
            double limit = sc.nextDouble();
            account = new CurrentAccount(accNum, name, balance, limit);
        }
        accounts.put(accNum, account);
        System.out.println("Account created successfully!");
    }
    private static void performDeposit() {
        System.out.print("Enter account number: ");
        String accNum = sc.nextLine();
        Account acc = accounts.get(accNum);
        if (acc != null) {
            System.out.print("Enter amount to deposit: ");
            double amount = sc.nextDouble();
            acc.deposit(amount);
        } else {
            System.out.println("Account not found!");
        }
    }
    private static void performWithdraw() {
        System.out.print("Enter account number: ");
        String accNum = sc.nextLine();
        Account acc = accounts.get(accNum);
        if (acc != null) {
            System.out.print("Enter amount to withdraw: ");
            double amount = sc.nextDouble();
            acc.withdraw(amount);
        } else {
            System.out.println("Account not found!");
        }
    }
    private static void checkBalance() {
        System.out.print("Enter account number: ");
        String accNum = sc.nextLine();
        Account acc = accounts.get(accNum);
        if (acc != null) {
            acc.displayBalance();
        } else {
            System.out.println("Account not found!");
        }
    }
}