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

    public Estadisticas() {
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
}
