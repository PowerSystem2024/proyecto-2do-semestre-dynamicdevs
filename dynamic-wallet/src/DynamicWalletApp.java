import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import domain.Account;
import domain.Customer;
import domain.Movement;
import domain.PesosAccount;
import domain.USDAccount;

public class DynamicWalletApp {
    static List<Customer> customersDB;
    static List<Account> accountsDB;

    static Customer loggedUser;
    static Movement movement;
    static Account account;

    public static void main(String[] args) {
        initDummyData();
        welcomeMessage();
    }

    public static void initDummyData() {
        customersDB = List.of(
                new Customer("John", "Doe"),
                new Customer("Alexa", "Smith"));

        for (Customer customerSaved : customersDB) {
            customerSaved.addAccount(new PesosAccount());
            customerSaved.addAccount(new USDAccount());
        }

        accountsDB = new ArrayList<>();
        for (Customer customerSaved : customersDB) {
            accountsDB.addAll(customerSaved.getAccounts());
        }
    }

    public static Account getAccountByAccountNumberFromDB(int accountNumber) {
        for (Account accountSaved : accountsDB) {
            if (accountSaved.getAccountNumber() == accountNumber) {
                return accountSaved;
            }
        }
        return null;
    }

    /**
     * Muestra un mensaje de bienvenida al inicio de la ejecución
     */
    public static void welcomeMessage() {
        String welcomeMessage = "Bienvenido a Dynamic Wallet\nSu billetera virtual dinámica" +
                "\n\nFavor de registrarse para operar";
        JOptionPane.showMessageDialog(null, welcomeMessage);
    }
}
