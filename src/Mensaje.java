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
    private double tiempoTansmision;
    private double tiempoProcesamiento; // WS
    private int cantidadVecesDevuelto; // Cantidad de veces que fue devuelto desde la C1
    private int computadoraInicio; // Computadora a la cual llega el mensaje inicialmente en el sistema

    private double procesador1; // P1 de C1
    private double procesador2; // P1 de C2
    private double procesador3; // P2 de C2
    private double procesador4; // P1 de C3

    public Mensaje() {

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

    public void setTiempoTansmision(double tiempoTansmision) {
        this.tiempoTansmision = tiempoTansmision;
    }

    public double getTiempoProcesamiento() {
        return tiempoProcesamiento;
    }

    public void setTiempoProcesamiento(double tiempoProcesamiento) {
        this.tiempoProcesamiento = tiempoProcesamiento;
    }

    public int getCantidadVecesDevuelto() {
        return cantidadVecesDevuelto;
    }

    public void setCantidadVecesDevuelto(int cantidadVecesDevuelto) {
        this.cantidadVecesDevuelto = cantidadVecesDevuelto;
    }

    public int getComputadoraInicio() {
        return computadoraInicio;
    }

    public void setComputadoraInicio(int computadoraInicio) {
        this.computadoraInicio = computadoraInicio;
    }

    public double getProcesador1() {
        return procesador1;
    }

    public void setProcesador1(double procesador1) {
        this.procesador1 = procesador1;
    }

    public double getProcesador2() {
        return procesador2;
    }

    public void setProcesador2(double procesador2) {
        this.procesador2 = procesador2;
    }

    public double getProcesador3() {
        return procesador3;
    }

    public void setProcesador3(double procesador3) {
        this.procesador3 = procesador3;
    }

    public double getProcesador4() {
        return procesador4;
    }

    public void setProcesador4(double procesador4) {
        this.procesador4 = procesador4;
    }
}
