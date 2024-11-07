package domain;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class Account {

    private static int idGenerator = 0; // generador de IDs
    protected int accountNumber;
    protected double balance;
    protected List<Movement> movements; // lista de movimientos

    public Account() {
        this.accountNumber = idGenerator++; // se le asigna el ID único
        this.balance = 0.0; // toda cuenta nueva comienza con balance en cero
        this.movements = new ArrayList<>(); // toda cuenta tiene una lista vacía de movimientos
    }

    // Getters
    public int getAccountNumber() {
        return this.accountNumber;
    }

    public double getBalance() {
        return this.balance;
    }

    /*
     * Devuelve una lista de movimientos asociados a la cuenta
     * que lo solicita.
     */
    public List<Movement> getMovements() {
        return this.movements;
    }

    /*
     * Al establecer el valor del nuevo balance se debe
     * validar que sea mayor o igual a cero, ya que no debe
     * ser posible tener un balance de cuenta en negativo
     */
    public void setBalance(double balance) {
        if (balance >= 0) {
            this.balance = balance;
        }
    }

    /*
     * Permite guardar un registro de cada operación realizada
     *
     * Criterios de aceptación:
     * - Como argumento se pasa el movimiento que se realizó creado con anterioridad
     * - El movimiento a registrar no debe ser nulo
     * - Guardarlo en la lista de la cuenta junto con el resto de movimientos
     * - El movimiento a registrar no debe estar registrado
     * con anterioridad, es decir, debe ser único.
     */
    public void recordMovement(Movement movement) {
        // Verifica que movimiento no sea nulo
        if (movement == null) {
            System.out.println("El movimiento no puede ser nulo");
        } else if (this.movements.contains(movement)) {
            // Verifica si el movimiento ya está en la lista movement
            System.out.println("El movimiento ya está registrado");
        } else {
            // Si no es nulo y no está registrado, lo agrega a la lista
            this.movements.add(movement);
            System.out.println("Movimiento registado correctamente");
        }
    }

    // Sobrescribe el método toString() de la clase Object
    @Override
    public String toString() {
        // Retorna una representación del objeto Account en formato String
        return "Número de cuenta: " + accountNumber;
    }

    public Movement deposit(double amount) {
        // Verificar que el monto sea mayor o igual a 100
        if (amount < 100) {
            JOptionPane.showMessageDialog(null, "El monto a depositar debe ser mayor o igual a 100.", null,
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }
        // Se suma el monto de la cuenta de origen
        this.setBalance(this.getBalance() + amount);
        // Crear movimiento para el deposito
        Movement movement = new Movement("Deposito",this.accountNumber, this.accountNumber, amount);
        this.recordMovement(movement);
        return movement;
    }

}
