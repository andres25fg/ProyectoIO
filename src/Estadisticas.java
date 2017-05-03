/**
 * Clase Estadisticas
 *
 * Jose Alvarado González
 * Andrés González Caldas
 */
public class Estadisticas {
    private double tiempoEnP1_C1; // tiempo de procesamiento en P1 de C1
    private double tiempoEnP1_C2; // tiempo de procesamiento en P1 de C2
    private double tiempoEnP2_C2; // tiempo de procesamiento en P2 de C2
    private double tiempoEnP1_C3; // tiempo de procesamiento en P1 de C3

    private double tiempoProcesamientoRechazados; // tiempo utilizado en procesamiento de mensajes rechazados
    private double tiempoTotalProcesamiento; // tiempo total de procesamiento de todos los procesadores
    private double tiempoEnCola; // tiempo total en cola
    private double tiempoEnSistema; // tiempo total en el sistema
    private double tiempoEnTransmision; // tiempo total usado en transmisión de mensajes

    // Estadísticas de la simulación
    private double porcetanjeP1_C1; // Porcentaje de tiempo en P1 de la Computadora 1
    private double porcetanjeP1_C2; // Porcentaje de tiempo en P1 de la Computadora 2
    private double porcetanjeP2_C2; // Porcentaje de tiempo en P2 de la Computadora 2
    private double porcetanjeP1_C3; // Porcentaje de tiempo en P1 de la Computadora 3
    private double porcentajeTiempoRechazo; // Porcentaje del tiempo usado en mensajes rechazados
    private double porcentajeMensajesRechazados; // Porcentaje de mensajes rechazados
    private double tiempo_W; // WS: tiempo promedio de servicio en el sistema
    private double promedioMensajeDevuelto; // Promedio de mensajes devueltos desde C1
    private double tiempo_WQ; // Wq: tiempo promedio en cola
    private double tiempoTransmision; // Tiempo promedio de transmisión entre computadoras
    private double porcentajeTiempo_WS; // Porcentaje de tiempo usado en servicio

    public Estadisticas() {
    }

    /**
     * Método que genera las estadísticas la simulación al final de la corrida
     * @param clock
     * @param numeroMensajesRechazados
     * @param cantidadMensajes
     * @param cantidadVecesDevuelto
     */
    public void hacerEstadisticas(double clock, int numeroMensajesRechazados, int cantidadMensajes, int cantidadVecesDevuelto) {
        porcetanjeP1_C1 = this.getTiempoEnP1_C1() / clock * 100;
        porcetanjeP1_C2 = this.getTiempoEnP1_C2() / clock * 100;
        porcetanjeP2_C2 = this.getTiempoEnP2_C2() / clock * 100;
        porcetanjeP1_C3 = this.getTiempoEnP1_C3() / clock * 100;

        porcentajeTiempoRechazo = this.getTiempoProcesamientoRechazados() / clock * 100;
        porcentajeMensajesRechazados = (double)numeroMensajesRechazados / (double)cantidadMensajes;
        tiempo_W = this.getTiempoEnSistema() / (double)cantidadMensajes;
        promedioMensajeDevuelto = (double)cantidadVecesDevuelto / (double)cantidadMensajes;

        tiempo_WQ = this.getTiempoEnCola() / (double)cantidadMensajes;
        tiempoTransmision = this.getTiempoEnTransmision() / (double)cantidadMensajes;
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
