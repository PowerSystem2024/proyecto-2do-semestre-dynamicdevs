package domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que actúa como cliente/usuario de la app
 * Proporciona métodos para obtener el nombre completo, asociarle cuentas
 * bancarias, obtener el listado de cuentas y obtener una cuenta por moneda.
 */
public class Customer {
    private int idGenerator = 0;
    // atributos de la clase
    private final int customerId;
    private final String firstName;
    private final String lastName;
    private final List<Account> accounts;

    // Constructor
    public Customer(String firstName, String lastName) {
        idGenerator++;
        this.customerId = idGenerator;
        this.firstName = firstName;
        this.lastName = lastName;
        this.accounts = new ArrayList<>();
    }

    /**
     * Devuelve el nombre del cliente en formato "Nombre Apellido"
     * 
     * @return Nombre completo del cliente
     */
    public String getFullName() {
        return (this.firstName + " " + this.lastName).toUpperCase();
    }

    /**
     * Devuelve el listado de cuentas del cliente
     * 
     * @return Listado de cuentas asociadas
     */
    public List<Account> getAccounts() {
        return this.accounts;
    }

    /**
     * Agrega una cuenta a la lista de cuentas del cliente
     * 
     * @param account Cuenta a agregar
     */
    public void addAccount(Account account) {
        // Verificamos si el objeto account es null antes de agregarlo
        if (account == null) {
            System.out.println("Cuenta inválida: no se puede agregar una cuenta nula.");
            return;
        }
        this.accounts.add(account);
    }

    /**
     * Obtener una cuenta especificando el tipo de cuenta
     * 
     * @param accountType Tipo de cuenta a buscar (Pesos o USD)
     * @return Cuenta encontrada o null si no se encuentra
     */
    public Account getAccountByType(String accountType) {
        for (Account account : this.getAccounts()) { // Iterando sobre la lista de cuentas con un bucle for
            // equalsIgnoreCase se usa para comparar dos cadenas sin tener en cuenta
            // mayúsculas o minúsculas
            if (accountType.equalsIgnoreCase("PESOS") && account instanceof PesosAccount) {
                return account; // Devuelve la cuenta en PESOS si la encuentra
            } else if (accountType.equalsIgnoreCase("USD") && account instanceof USDAccount) {
                return account; // Devuelve la cuenta en USD si la encuentra
            }
        }
        return null; // si no se encuentra la cuenta solicitada, retornar null
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        // Agrega el ID del cliente
        sb.append("\nID Cliente: ").append(customerId);
        // Agrega el nombre completo
        sb.append("\nNombre completo: ").append(firstName).append(" ").append(lastName).append("\n");
        // Agrega la cantidad de cuentas asociadas
        // El metodo size devuelve el número de elementos en una lista
        // Es un metodo que pertenece a la clase List
        sb.append("Cuentas asociadas: ").append(accounts.size()).append(" cuentas\n");
        return sb.toString();
    }
}