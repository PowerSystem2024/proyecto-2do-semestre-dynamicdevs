package domain;
import java.time.LocalDateTime;

public class Movement{
    //Cargamos atributos
    private static int idGenerator ;// este es un contador de que es usado por la misma clase
    //para contar las veces que se instancia la misma
    private final int transactionId;
    private final LocalDateTime date;
    /*
        La clase LocalDateTime en Java, que pertenece al paquete java.time,
        se utiliza para representar una fecha y hora sin zona horaria.
        Algunas de las características principales de LocalDateTime incluyen:

        /Inmutabilidad/: Es inmutable, lo que significa que una vez creada,
                        su valor no se puede cambiar. Cualquier modificación crea una nueva instancia.

        /Versatilidad en la creación/: Puedes generar instancias de LocalDateTime usando valores
                        específicos de año, mes, día, etc., o a través de métodos convenientes como now().
     */
    private final String transactionType;
    private final int destinationAccount;
    private final int originAccount;
    private final double amount;


    public Movement(String transactionType, int destinationAccount, int originAccount, double amount){
        Movement.idGenerator++;//incrementamos en 1 el contador, no se utiliza this, ya que el contador es estatico
        this.transactionId = Movement.idGenerator;//asignamos el valor actual del contador del ID de la transacción
        this.transactionType = transactionType;
        this.destinationAccount = destinationAccount;
        this.originAccount = originAccount;
        this.amount = amount;
        this.date = LocalDateTime.now();
        /*
           El metodo estático now()  devuelve el LocalDateTime actual basado en la hora y fecha del sistema.
        */

    }
    //gett
    public String getTransactionType(){
        return transactionType;
    }
    public int getDestinationAccount(){
        return destinationAccount;
    }
    public int getOriginAccount(){
        return originAccount;
    }
    public double getAmount(){
        return amount;
    }
    public LocalDateTime getDate(){
        return date;
    }

//  public String getTicket() {
   //  return "********* DINAMIC-WALLET *********\nTipo de transacción: "+ this.transactionType+" -- N°: "+this.transactionId+"\nFecha: "+this.date+"\nCuenta Origen: "+this.originAccount+"\nCuenta destino: "+this.destinationAccount+"\nMonto: "+this.amount;
   //
//    } //Es mas óptimo utilizar StringBuilder

    public String getTicket(){
        StringBuilder sb = new StringBuilder();

        //Agregamos los string al strinbuilder
        sb.append("********* DINAMIC-WALLET *********\nTipo de transacción: ");
        sb.append("\nTipo de transacción: ").append(transactionType).append(" -- N°: ").append(transactionId).append("\n");
        sb.append("Fecha: ").append(getDate()).append("\n");
        sb.append("Cuenta Origen: ").append(getOriginAccount()).append("\n");
        sb.append("Cuenta Destino: ").append(getDestinationAccount()).append("\n");
        sb.append("Monto: ").append(getAmount()).append("\n");

        return sb.toString();
    }
}