/**
 * Clase Estadisticas
 *
 * Jose Alvarado González
 * Andrés González Caldas
 */
public class Estadisticas {
    private double tiempoEnP1_C1; // P1 de C1
    private double tiempoEnP1_C2; // P1 de C2
    private double tiempoEnP2_C2; // P2 de C2
    private double tiempoEnP1_C3; // P1 de C3

    private double tiempoProcesamientoRechazados;
    private double tiempoTotalProcesamiento;
    private double tiempoEnCola;
    private double tiempoEnSistema;
    private double tiempoEnTransmision;

    // Estadísticas globales por simulación
    private double porcetanjeP1_C1;
    private double porcetanjeP1_C2;
    private double porcetanjeP2_C2;
    private double porcetanjeP1_C3;
    private double porcentajeTiempoRechazo;
    private double porcentajeMensajesRechazados;
    private double tiempo_W;
    private double promedioMensajeDevuelto;
    private double tiempo_WQ;
    private double tiempoTransmision;
    private double porcentajeTiempo_WS;

    public Estadisticas() {
    }

    public void hacerEstadisticas(double clock, int numeroMensajesRechazados, int cantidadMensajes, int cantidadVecesDevuelto) {
        porcetanjeP1_C1 = this.getTiempoEnP1_C1() / clock * 100;
        porcetanjeP1_C2 = this.getTiempoEnP1_C2() / clock * 100;
        porcetanjeP2_C2 = this.getTiempoEnP2_C2() / clock * 100;
        porcetanjeP1_C3 = this.getTiempoEnP1_C3() / clock * 100;

        porcentajeTiempoRechazo = this.getTiempoProcesamientoRechazados() / clock * 100;
        porcentajeMensajesRechazados = numeroMensajesRechazados / cantidadMensajes * 100;
        tiempo_W = this.getTiempoEnSistema() / cantidadMensajes;
        promedioMensajeDevuelto = cantidadVecesDevuelto / cantidadMensajes;

        tiempo_WQ = this.getTiempoEnCola() / cantidadMensajes;
        tiempoTransmision = this.getTiempoEnTransmision() / cantidadMensajes;
        porcentajeTiempo_WS = this.getTiempoTotalProcesamiento() / clock * 100;
    }

    public void sumarTiempoEnCola(double tiempo) {
        this.tiempoEnCola += tiempo;
    }

    public void sumarTiempoEnSistema(double tiempo) {
        this.tiempoEnSistema += tiempo;
    }

    public void sumarTiempoEnTransmision(double tiempo) {
        this.tiempoEnTransmision += tiempo;
    }

    public void sumarTiempoProcesamientoRechazados(double tiempo) {
        this.tiempoProcesamientoRechazados += tiempo;
    }

    public void sumarTiempoTotalProcesamiento(double tiempo) {
        this.tiempoTotalProcesamiento += tiempo;
    }

    public void sumarTiempoEnP1_C1(double tiempo) {
        this.tiempoEnP1_C1 += tiempo;
    }

    public void sumarTiempoEnP1_C2(double tiempo) {
        this.tiempoEnP1_C2 += tiempo;
    }

    public void sumarTiempoEnP2_C2(double tiempo) {
        this.tiempoEnP2_C2 += tiempo;
    }

    public void sumarTiempoEnP1_C3(double tiempo) {
        this.tiempoEnP1_C3 += tiempo;
    }

    public double getTiempoEnP1_C1() {
        return tiempoEnP1_C1;
    }

    public double getTiempoEnP1_C2() {
        return tiempoEnP1_C2;
    }

    public double getTiempoEnP2_C2() {
        return tiempoEnP2_C2;
    }

    public double getTiempoEnP1_C3() {
        return tiempoEnP1_C3;
    }

    public double getTiempoProcesamientoRechazados() {
        return tiempoProcesamientoRechazados;
    }

    public double getTiempoTotalProcesamiento() {
        return tiempoTotalProcesamiento;
    }

    public double getTiempoEnCola() {
        return tiempoEnCola;
    }

    public double getTiempoEnSistema() {
        return tiempoEnSistema;
    }

    public double getTiempoEnTransmision() {
        return tiempoEnTransmision;
    }

    public double getPorcetanjeP1_C1() {
        return porcetanjeP1_C1;
    }

    public double getPorcetanjeP1_C2() {
        return porcetanjeP1_C2;
    }

    public double getPorcetanjeP2_C2() {
        return porcetanjeP2_C2;
    }

    public double getPorcetanjeP1_C3() {
        return porcetanjeP1_C3;
    }

    public double getPorcentajeTiempoRechazo() {
        return porcentajeTiempoRechazo;
    }

    public double getPorcentajeMensajesRechazados() {
        return porcentajeMensajesRechazados;
    }

    public double getTiempo_W() {
        return tiempo_W;
    }

    public double getPromedioMensajeDevuelto() {
        return promedioMensajeDevuelto;
    }

    public double getTiempo_WQ() {
        return tiempo_WQ;
    }

    public double getTiempoTransmision() {
        return tiempoTransmision;
    }

    public double getPorcentajeTiempo_WS() {
        return porcentajeTiempo_WS;
    }
}
