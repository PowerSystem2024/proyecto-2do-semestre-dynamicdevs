package domain;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class Account {
    private static int idGenerator = 0; // generador de IDs
    protected int accountNumber;
    protected double balance;
    protected List<Movement> movements; // lista de movimientos
    protected boolean enabled;

    public Account(){
        this.accountNumber = idGenerator; // se le asigna el ID único
             this.balance = 0.0; // toda cuenta nueva comienza con balance en cero
             this.movements = new ArrayList<>(); // toda cuenta tiene una lista vacía de movimientos
             this.enabled = true; // cuenta habilitada por defecto
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

    public boolean isEnabled() {
        return this.enabled;
    }

     // Setters
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /*
     * Al establecer el valor del nuevo balance se debe
     * validar que sea mayor o igual a cero, ya que no debe
     * ser posible tener un balance de cuenta en negativo
     * */
    public void setBalance(double balance) {
        // todo: validar antes de establecer el balance
        this.balance = balance;
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


    /*
     * Transferir desde una cuenta de la misma moneda
     * a otra cuenta de la misma moneda.
     */
    public Movement transfer(Account destinationAccount, double amount) {
        //El monto a transferir debe ser mayor o igual a 1.
        if (amount < 1) {
            JOptionPane.showMessageDialog(null,"Error: El monto a transferir debe ser mayor o igual a 1.");
        }
        //Verifica que halla balance suficiente para realizar la transferencia
        else if (this.balance < amount) {
            JOptionPane.showMessageDialog(null,"Error: Fondos insuficientes en la cuenta de origen.");
            
        }   
        //verificar si ambas cuentas deben existir en la base de datos    
        else if (destinationAccount == null) {
            JOptionPane.showMessageDialog(null,"Error: La cuenta de destino no existe.");
        }

        // Resta el monto de la cuenta de origen
        this.balance -= amount;
        // Suma el monto a la cuenta de destino
        destinationAccount.setBalance(destinationAccount.getBalance() + amount);
        // Crear movimientos para ambas cuentas
        Movement originMovement = new Movement(this.accountNumber, destinationAccount.getAccountNumber(), amount);
        Movement destinationMovement = new Movement(destinationAccount.getAccountNumber(), this.accountNumber, amount);       
        // Registrar movimientos
        this.recordMovement(originMovement);
        destinationAccount.recordMovement(destinationMovement);
        // Devolver movimiento de la cuenta de origen
        return originMovement;
    }


    /*
     * Permite realizar depósitos a cuentas propias de la misma moneda.
     */
    public Movement deposit(double amount) {
        //El monto a depositar debe ser mayor o igual a 1
        if (amount < 1) {
            JOptionPane.showMessageDialog(null,"Error: El monto a transferir debe ser mayor o igual a 1.");
        }
        else if (this.balance < amount) {
            JOptionPane.showMessageDialog(null,"Error: Fondos insuficientes en la cuenta de origen.");
            
        }
        //Devolver el movimiento que refleja el detalle del depósito.
        this.balance = this.balance + amount;
        Movement movement = new Movement(this.accountNumber, this.accountNumber, amount);
        this.recordMovement(movement);
        return movement;

    //todo: transferToForeignCurrency() (mejora)
    }    

}


