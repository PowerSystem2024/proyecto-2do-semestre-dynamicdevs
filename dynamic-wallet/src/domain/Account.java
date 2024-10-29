package domain;

import java.util.ArrayList;
import java.util.List;

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


}


