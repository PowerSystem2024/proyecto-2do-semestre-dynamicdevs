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

    // obtenemos nombre y apellido del usuario / cliente
    public String getFullName() {
        return (this.firstName + " " + this.lastName).toUpperCase();
    }

    // obtenemos el listado de cuentas del usuario / cliente
    public List<Account> getAccounts() {
        return this.accounts;
    }

    // metodo para agregar una nueva cuenta al usuario, previa validación (si es nula)
    public void addAccount(Account account) {
        // Verificamos si el objeto account es null antes de agregarlo
        if (account == null) {
            System.out.println("Cuenta inválida: no se puede agregar una cuenta nula.");
            return;
        }
        this.accounts.add(account);
    }
}