/**
 * Clase Evento
 *
 * Jose Alvarado González
 * Andrés González Caldas
 */
public class Evento {
    private int tipoEvento; // 0-9
    private Mensaje mensaje;
    private double tiempoEvento; // Tiempo de inicio del evento

    public Evento(Mensaje mensaje, double tiempoEvento, int tipoEvento){
        this.mensaje = mensaje;
        this.tiempoEvento = tiempoEvento;
        this.tipoEvento = tipoEvento;
    }

    public int getTipoEvento() {
        return tipoEvento;
    }

    public Mensaje getMensaje() {
        return mensaje;
    }

    public double getTiempoEvento() {
        return tiempoEvento;
    }
}
