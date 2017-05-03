/**
 * Clase Mensaje
 *
 * Jose Alvarado González
 * Andrés González Caldas
 */
public class Mensaje {
    private double llegadaSistema; // Hora de llegada al sistema
    private double tiempoEnCola; // WQ
    private double tiempoInicioCola; // hora en que se empieza a esperar en cola
    private double tiempoTansmision; // Tiempo que tarda siendo trasmitido un mensaje
    private double tiempoProcesamiento; // WS
    private int computadoraInicio; // Computadora a la cual llega el mensaje inicialmente en el sistema

    public Mensaje() {

    }

    public double getLlegadaSistema() {
        return llegadaSistema;
    }

    public double getTiempoInicioCola() {
        return tiempoInicioCola;
    }

    public void setTiempoInicioCola(double tiempoInicioCola) {
        this.tiempoInicioCola = tiempoInicioCola;
    }

    public void setLlegadaSistema(double llegadaSistema) {
        this.llegadaSistema = llegadaSistema;
    }

    public double getTiempoEnCola() {
        return tiempoEnCola;
    }

    public void sumarTiempoEnCola(double tiempoEnCola) {
        this.tiempoEnCola += tiempoEnCola;
    }

    public double getTiempoTansmision() {
        return tiempoTansmision;
    }

    public void sumarTiempoProcesamiento(double tiempoProcesamiento) {
        this.tiempoProcesamiento += tiempoProcesamiento;
    }

    public void sumarTiempoTansmision(double tiempoTansmision) {
        this.tiempoTansmision += tiempoTansmision;
    }

    public double getTiempoProcesamiento() {
        return tiempoProcesamiento;
    }

    public int getComputadoraInicio() {
        return computadoraInicio;
    }

    public void setComputadoraInicio(int computadoraInicio) {
        this.computadoraInicio = computadoraInicio;
    }

}
