import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.*;
public class OnlineBankingSystem extends Application {
    private static Map<String, Account> accounts = new HashMap<>();
    private Stage primaryStage;
    private String loggedUser = null;
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Online Banking System");
        showLoginScene();
    }
    // ---------------- LOGIN SCENE ----------------
    private void showLoginScene() {
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(20));
        Label userLabel = new Label("Username:");
        TextField userField = new TextField();
        Label passLabel = new Label("Password:");
        PasswordField passField = new PasswordField();
        Button loginButton = new Button("Login");
        Button createButton = new Button("Create Account");
        grid.add(userLabel, 0, 0);
        grid.add(userField, 1, 0);
        grid.add(passLabel, 0, 1);
        grid.add(passField, 1, 1);
        grid.add(loginButton, 1, 2);
        grid.add(createButton, 1, 3);
        loginButton.setOnAction(e -> {
            String username = userField.getText();
            String password = passField.getText();
            if (accounts.containsKey(username) && password.equals("pass")) {
                loggedUser = username;
                showBankingScene();
            } else {
                showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid username or password!");
            }
        });
        createButton.setOnAction(e -> showCreateAccountScene());
        Scene scene = new Scene(grid, 350, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    // ---------------- ACCOUNT CREATION SCENE ----------------
    private void showCreateAccountScene() {
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(20));
        Label accNumLabel = new Label("Account Number:");
        TextField accNumField = new TextField();
        Label nameLabel = new Label("Holder Name:");
        TextField nameField = new TextField();
        Label balLabel = new Label("Initial Balance:");
        TextField balField = new TextField();
        Label typeLabel = new Label("Account Type:");
        ComboBox<String> typeBox = new ComboBox<>();
        typeBox.getItems().addAll("Savings", "Current");
        Button createBtn = new Button("Create Account");
        Button backBtn = new Button("Back");
        grid.add(accNumLabel, 0, 0);
        grid.add(accNumField, 1, 0);
        grid.add(nameLabel, 0, 1);
        grid.add(nameField, 1, 1);
        grid.add(balLabel, 0, 2);
        grid.add(balField, 1, 2);
        grid.add(typeLabel, 0, 3);
        grid.add(typeBox, 1, 3);
        grid.add(createBtn, 1, 4);
        grid.add(backBtn, 1, 5);
        createBtn.setOnAction(e -> {
            try {
                String accNum = accNumField.getText();
                String name = nameField.getText();
                double balance = Double.parseDouble(balField.getText());
                String type = typeBox.getValue();
                if (type == null) {
                    showAlert(Alert.AlertType.WARNING, "Warning", "Please select account type!");
                    return;
                }
                Account acc;
                if (type.equals("Savings")) {
                    acc = new SavingsAccount(accNum, name, balance, 5.0);
                } else {
                    acc = new CurrentAccount(accNum, name, balance, 1000);
                }
                accounts.put(accNum, acc);
                showAlert(Alert.AlertType.INFORMATION, "Success", "Account created successfully!");
                showLoginScene();
            } catch (NumberFormatException ex) {
                showAlert(Alert.AlertType.ERROR, "Error", "Invalid balance input!");
            }
        });
        backBtn.setOnAction(e -> showLoginScene());
        Scene scene = new Scene(grid, 400, 300);
        primaryStage.setScene(scene);
    }
    // ---------------- BANKING MENU SCENE ----------------
    private void showBankingScene() {
        GridPane grid = new GridPane();
        grid.setVgap(15);
        grid.setHgap(15);
        grid.setPadding(new Insets(20));
        Label title = new Label("Welcome, " + loggedUser + "!");
        Button depositBtn = new Button("Deposit");
        Button withdrawBtn = new Button("Withdraw");
        Button balanceBtn = new Button("Check Balance");
        Button logoutBtn = new Button("Logout");
        grid.add(title, 0, 0);
        grid.add(depositBtn, 0, 1);
        grid.add(withdrawBtn, 0, 2);
        grid.add(balanceBtn, 0, 3);
        grid.add(logoutBtn, 0, 4);
        depositBtn.setOnAction(e -> handleTransaction("deposit"));
        withdrawBtn.setOnAction(e -> handleTransaction("withdraw"));
        balanceBtn.setOnAction(e -> handleBalance());
        logoutBtn.setOnAction(e -> {
            loggedUser = null;
            showLoginScene();
        });
        Scene scene = new Scene(grid, 350, 250);
        primaryStage.setScene(scene);
    }
    private void handleTransaction(String type) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle(type.equals("deposit") ? "Deposit" : "Withdraw");
        dialog.setHeaderText("Enter amount to " + type + ":");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            try {
                double amount = Double.parseDouble(result.get());
                Account acc = accounts.get(loggedUser);
                if (acc == null) {
                    showAlert(Alert.AlertType.ERROR, "Error", "Account not found!");
                    return;
                }
                if (type.equals("deposit")) acc.deposit(amount);
                else acc.withdraw(amount);
                showAlert(Alert.AlertType.INFORMATION, "Transaction Successful", "Operation completed!");
            } catch (NumberFormatException ex) {
                showAlert(Alert.AlertType.ERROR, "Error", "Invalid amount entered!");
            }
        }
    }
    private void handleBalance() {
        Account acc = accounts.get(loggedUser);
        if (acc != null)
            showAlert(Alert.AlertType.INFORMATION, "Balance", "Current Balance: $" + acc.balance);
        else
            showAlert(Alert.AlertType.ERROR, "Error", "Account not found!");
    }
    private void showAlert(Alert.AlertType type, String title, String msg) {
        Alert alert = new Alert(type, msg);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
// ---------------- ACCOUNT CLASSES ----------------
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
        if (amount > 0) balance += amount;
    }
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) balance -= amount;
    }
}
class SavingsAccount extends Account {
    private double interestRate;
    public SavingsAccount(String accNum, String holderName, double balance, double interestRate) {
        super(accNum, holderName, balance);
        this.interestRate = interestRate;
    }
    public void addInterest() {
        balance += balance * (interestRate / 100);
    }
}
class CurrentAccount extends Account {
    private double overdraftLimit;
    public CurrentAccount(String accNum, String holderName, double balance, double overdraftLimit) {
        super(accNum, holderName, balance);
        this.overdraftLimit = overdraftLimit;
    }
    @Override
    public void withdraw(double amount) {
        if (amount > 0 && amount <= (balance + overdraftLimit)) balance -= amount;
    }
}