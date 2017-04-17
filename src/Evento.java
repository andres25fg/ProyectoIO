/**
 * Clase Evento
 *
 * Jose Alvarado González
 * Andrés González Caldas
 */
public class Evento {
    private TipoEvento tipo;
    private Mensaje mensaje;
    private int tiempoEvento; // Tiempo de inicio del evento

    public Evento(){

    }

    public int getTiempoEvento() {
        return tiempoEvento;
    }
}

/**
 * Enumeration TipoEvento
 *
 * Jose Alvarado González
 * Andrés González Caldas
 */
enum TipoEvento {
    ENTRADA("ENTRADA"),
    SALIDA("SALIDA");

    private String tipo;

    TipoEvento(String type){
        this.tipo=type;
    }

    public String getTipo(){
        return tipo;
    }
}