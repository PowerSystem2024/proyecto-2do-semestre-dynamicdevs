package domain;

/**
 * Cuenta en dólares
 */
public class USDAccount extends Account {
    // constructor clase hija
    public USDAccount() {
        super(); // llamada al constructor de la clase madre (Account)
    }


 /**
     * Sobreescritura del método toString() para proporcionar una vista final
     * detallada de la información de la cuenta en pesos.
     * 
     * Este método llama al toString() de la clase Account para obtener el número de cuenta
     * y balance, y luego añade el tipo de cuenta específico ("PESOS").
     * 
     * @return String con el formato:
     *         "Número de cuenta: [número de cuenta]
     *         Tipo de cuenta: en PESOS"
     */

    public String toString() {
        // Llama al método toString() de la clase Account para obtener la información base
        return super.toString().split("\n")[0] + "\nTipo de cuenta: en USD";
    }
}
