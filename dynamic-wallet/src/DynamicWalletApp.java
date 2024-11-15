import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import java.awt.*;

import domain.Account;
import domain.Customer;
import domain.Movement;
import domain.PesosAccount;
import domain.USDAccount;

public class DynamicWalletApp {
    static List<Customer> customersDB;
    static List<Account> accountsDB;

    static Customer loggedUser;
    static Movement transactionReceipt;
    static Account account;

    public static void main(String[] args) {
        applyCustomOptionPaneStyles();

        initDummyData();
        welcomeMessage();
        registration();
        openAccount();
        app();
    }

    /**
     * Aplica estilos personalizados a los cuadros de diálogo de JOptionPane
     */
    public static void applyCustomOptionPaneStyles() {
        // Cambiar fuentes y bordes en el UIManager
        UIManager.put("OptionPane.messageFont", new Font("Arial", Font.PLAIN, 18));
        UIManager.put("OptionPane.buttonFont", new Font("Arial", Font.PLAIN, 18));
        UIManager.put("OptionPane.minimumSize", new Dimension(400, 200)); // Mínimo tamaño del cuadro de diálogo
    }

    /**
     * Inicializa los datos de prueba de la aplicación
     */
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

        accountsDB.forEach(System.out::println);
    }

    /**
     * Obtiene una cuenta bancaria por número de cuenta
     *
     * @param accountNumber Número de cuenta
     * @return Cuenta bancaria
     */
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
        String accountType = selectAccountType();

        account = loggedUser.getAccountByType(accountType);

        if (account != null) {
            JOptionPane.showMessageDialog(null, "Ya posee una cuenta en " + accountType, null,
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        account = (accountType.equalsIgnoreCase("PESOS")) ? new PesosAccount() : new USDAccount();
        loggedUser.addAccount(account);

        JOptionPane.showMessageDialog(null,
                "Cuenta en " + accountType + " creada exitosamente\n\nDatos de la cuenta:\n" + account.toString(), null,
                JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Menú principal de la aplicación
     */
    public static void app() {
        String mainMenu = "Bienvenido " + loggedUser.getFullName() + "\n" +
                "¿Qué le gustaría realizar?\n" +
                "1. Depositar\n" +
                "2. Transferir\n" +
                "3. Ver movimientos\n" +
                "4. Ver estado de cuenta\n" +
                "5. Crear una nueva cuenta bancaria\n" +
                "6. Ver mis datos\n" +
                "7. Finalizar";

        int selectedOption;

        do {
            selectedOption = Integer.parseInt(JOptionPane.showInputDialog(mainMenu));

            validateInputAcordingRange(selectedOption, 1, 7, "Opcion incorrecta", mainMenu);

            switch (selectedOption) {
                case 1:
                    doDeposit();
                    break;
                case 2:
                    doTransfer();
                    break;
                case 3:
                    viewMovements();
                    break;
                case 4:
                    viewAccountStatus();
                    break;
                case 5:
                    openAccount();
                    break;
                case 6:
                    displayUserInfo();
                    break;
                default: // cuando se elije la opción n°7
                    JOptionPane.showMessageDialog(null, "Sesión cerrada con éxito", null,
                            JOptionPane.INFORMATION_MESSAGE);
                    break;
            }
        } while (selectedOption != 7);
    }

    /**
     * Permite realizar un depósito a una cuenta seleccionada (Pesos o USD)
     */
    public static void doDeposit() {
        String accountType = selectAccountType();

        account = loggedUser.getAccountByType(accountType);

        if (account == null) {
            JOptionPane.showMessageDialog(null, "No posee una cuenta en " + accountType, null,
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        double amountToDeposit = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el monto a depositar", 100));

        transactionReceipt = account.deposit(amountToDeposit);

        if (transactionReceipt != null) {
            JOptionPane.showMessageDialog(null, "Depósito exitoso\n" + transactionReceipt.toString(), null,
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Permite realizar una transferencia a una cuenta
     */
    public static void doTransfer() {
        int destinationAccountNumber = Integer.parseInt(JOptionPane.showInputDialog("CBU cuenta destino"));

        Account destinationAccount = getAccountByAccountNumberFromDB(destinationAccountNumber);

        if (destinationAccount == null) {
            JOptionPane.showMessageDialog(null, "Cuenta destino no encontrada", null, JOptionPane.ERROR_MESSAGE);
            return;
        }

        String accountType = selectAccountType();

        Account originAccount = loggedUser.getAccountByType(accountType);

        if (originAccount == null) {
            JOptionPane.showMessageDialog(null, "No posee una cuenta en " + accountType, null,
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        double amountToTransfer = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el monto a transferir", 100));

        // no se permiten transferencias entre distintas monedas (USD) -> (Pesos)
        if (originAccount.getClass() != destinationAccount.getClass()) {
            JOptionPane.showMessageDialog(null, "No se pueden realizar transferencias a cuentas de distinta moneda",
                    null,
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        Movement movement = originAccount.Transfer(destinationAccount, amountToTransfer);

        if (movement != null) {
            JOptionPane.showMessageDialog(null, movement, null, JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Permite ver los movimientos de la cuenta seleccionada (Pesos o USD)
     */
    public static void viewMovements() {
        String accountType = selectAccountType();

        account = loggedUser.getAccountByType(accountType);

        if (account == null) {
            JOptionPane.showMessageDialog(null, "No posee una cuenta en " + accountType, null,
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (account.getMovements().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay movimientos para mostrar", null,
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(null, account.getMovements(), "Movimientos de la cuenta",
                JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Permite ver el estado de cuenta de la cuenta seleccionada (Pesos o USD)
     */
    public static void viewAccountStatus() {
        String accountType = selectAccountType();

        account = loggedUser.getAccountByType(accountType);

        if (account == null) {
            JOptionPane.showMessageDialog(null, "No posee una cuenta en " + accountType, null,
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(null, account.toString() + "\nTipo de cuenta: " + accountType,
                "Estado de cuenta", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Muestra los datos del usuario registrado
     */
    public static void displayUserInfo() {
        JOptionPane.showMessageDialog(null, loggedUser.toString(), null, JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Muestra un menú de opciones para seleccionar el tipo de cuenta
     * en la que se desea operar.
     *
     * @return el tipo de cuenta seleccionada
     */
    public static String selectAccountType() {
        // Define el menú de opciones
        String menu = "Seleccióne el tipo de cuenta para operar:\n" +
                "1. Cuenta en Pesos\n" +
                "2. Cuenta en Dólares\n";

        // Solicita la opción seleccionada por el usuario
        int selectedOption = Integer.parseInt(JOptionPane.showInputDialog(menu));

        validateInputAcordingRange(selectedOption, 1, 2, "Opcion incorrecta", menu);

        // Retorna el tipo de cuenta basado en la opción seleccionada
        return (selectedOption == 1) ? "PESOS" : "USD";
    }

    /**
     * Valida las opciones ingresadas por el usuario. Se debe determinar un rango A
     * y B.
     * Mediante un bucle se evalúa si el valor ingresado está fuera de esos rangos.
     * Caso de que el valor esté fuera de los rangos especificados, se vuelve a
     * pedir que ingrese el valor
     * nuevamente hasta que el valor ingresado sea correcto (esté dentro de los
     * parámetros).
     *
     * @param option   Valor ingresado por el usuario
     * @param rangeA   Rango inicial
     * @param rangeB   Rango final
     * @param errorMsg Mensaje de error al ingresar un valor fuera de los rangos
     * @param menu     Menú de opciones
     */
    public static void validateInputAcordingRange(int option, int rangeA, int rangeB, String errorMsg, String menu) {
        // si se pone un valor incorrecto de opción, se vuelve a pedir
        while (option < rangeA || option > rangeB) {
            JOptionPane.showMessageDialog(null, errorMsg);
            option = Integer.parseInt(JOptionPane.showInputDialog(menu));
        }
    }
}