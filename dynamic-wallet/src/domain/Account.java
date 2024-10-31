package domain;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private static int idGenerator = 0; // generador de IDs
    protected int accountNumber;
    protected double balance;
    protected List<Movement> movements; // lista de movimientos

    public Account(){
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
     * */
    public List<Movement> getMovements() {
        return this.movements;
    }

    /*
     * Al establecer el valor del nuevo balance se debe
     * validar que sea mayor o igual a cero, ya que no debe
     * ser posible tener un balance de cuenta en negativo
     * */
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
     *   con anterioridad, es decir, debe ser único.
     * */
    public void recordMovement(Movement movement) {
        //Verifica que movimiento no sea nulo
        if (movement == null) {
            System.out.println("El movimiento no puede ser nulo");
        }
        // Verifica si el movimiento ya está en la lista movement
        else if (this.movements.contains(movement)) {
            System.out.println("El movimiento ya está registrado");
        }
        // Si no es nulo y no está registrado, lo agrega a la lista
        else { 
            this.movements.add(movement);
            System.out.println("Movimiento registado correctamente");
        }
        
    }
}


