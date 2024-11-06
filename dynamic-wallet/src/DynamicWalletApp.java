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
        registration();
        openAccount();
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

    /**
     * Registro del cliente: esto permitirá que el usuario pueda usar la app.
     */
    public static void registration() {
        // todo: validar todo antes de registrar al cliente
        JOptionPane.showMessageDialog(null,
                "Para registrarse, ingrese los siguientes datos (presione OK para continuar)");

        String firstName = JOptionPane.showInputDialog("Nombre");
        String lastName = JOptionPane.showInputDialog("Apellido");

        validateRegistrationData(firstName, lastName);

        loggedUser = new Customer(firstName, lastName);

        JOptionPane.showMessageDialog(null, "Registro exitoso");
    }

    public static void validateRegistrationData(String firstName, String lastName) {
        while (firstName == null || firstName.trim().isEmpty() || lastName == null || lastName.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, ingrese un nombre y apellido válidos");
            firstName = JOptionPane.showInputDialog("Nombre");
            lastName = JOptionPane.showInputDialog("Apellido");
        }
    }

    /**
     * Permite crear una cuenta bancaria dependiendo de la
     * opción que se elija.
     * Si se elije una de las opciones pero ya posée una cuenta de ese tipo,
     * se muestra un
     * mensaje detallando el error.
     */
    public static void openAccount() {
        String menu = "Seleccióne el tipo de cuenta para operar:\n" +
                "1. Cuenta en Pesos\n" +
                "2. Cuenta en Dólares\n";

        int option = Integer.parseInt(JOptionPane.showInputDialog(menu));

        while (option < 1 || option > 2) {
            JOptionPane.showMessageDialog(null, "Opción incorrecta");
            option = Integer.parseInt(JOptionPane.showInputDialog(menu));
        }

        String accountType = (option == 1) ? "PESOS" : "USD"; // tipo de cuenta según elección
        account = loggedUser.getAccountByType(accountType);

        if (account != null) {
            JOptionPane.showMessageDialog(null, "Ya posee una cuenta en " + accountType, null, JOptionPane.ERROR_MESSAGE);
            return;
        }

        account = (option == 1) ? new PesosAccount() : new USDAccount();
        loggedUser.addAccount(account);

        JOptionPane.showMessageDialog(null, "Cuenta en " + accountType + " creada exitosamente", null,
                JOptionPane.INFORMATION_MESSAGE);
    }
}
