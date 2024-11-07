package domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Movement {
    // Cargamos atributos
    private static int idGenerator;// este es un contador de que es usado por la misma clase
    // para contar las veces que se instancia la misma
    private final int transactionId;
    private final LocalDateTime date;
    /*
     * La clase LocalDateTime en Java, que pertenece al paquete java.time,
     * se utiliza para representar una fecha y hora sin zona horaria.
     * Algunas de las características principales de LocalDateTime incluyen:
     * 
     * /Inmutabilidad/: Es inmutable, lo que significa que una vez creada,
     * su valor no se puede cambiar. Cualquier modificación crea una nueva
     * instancia.
     * 
     * /Versatilidad en la creación/: Puedes generar instancias de LocalDateTime
     * usando valores
     * específicos de año, mes, día, etc., o a través de métodos convenientes como
     * now().
     */
    private final String transactionType;
    private final int destinationAccount;
    private final int originAccount;
    private final double amount;

    public Movement(String transactionType, int destinationAccount, int originAccount, double amount) {
        Movement.idGenerator++;// incrementamos en 1 el contador, no se utiliza this, ya que el contador es estatico
        this.transactionId = Movement.idGenerator;// asignamos el valor actual del contador del ID de la transacción
        this.transactionType = transactionType;
        this.destinationAccount = destinationAccount;
        this.originAccount = originAccount;
        this.amount = amount;
        this.date = LocalDateTime.now();
        /*
         * El metodo estático now() devuelve el LocalDateTime actual basado en la hora y
         * fecha del sistema.
         */
    }
    /**
 * Sobreescritura del método toString() para proporcionar una representación en formato String
 * de un objeto Movement. Este método muestra de manera detallada la información de la transacción,
 * incluyendo ID, fecha y hora, tipo de operación, cuentas origen y destino, y el monto.
 *
 * @return Una cadena de texto formateada que representa el movimiento.
 */
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nNúmero de operación: ").append(transactionId).append("\n");
        sb.append("Fecha y hora: ").append(date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy 'a las' HH:mm"))).append("\n");  //importamos la java.time.format.DateTimeFormatter para dar el formato
        sb.append("Tipo de operación: ").append(transactionType).append("\n");
        sb.append("CBU cuenta origen: ").append(destinationAccount).append("\n");
        sb.append("CBU cuenta destino: ").append(originAccount).append("\n");
        sb.append("Monto: $").append(String.format("%.2f", amount)).append("\n"); //el monto se muestra con dos decimales
        return sb.toString();
    }
   
}